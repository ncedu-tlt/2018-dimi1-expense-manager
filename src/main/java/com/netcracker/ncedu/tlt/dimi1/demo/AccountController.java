package com.netcracker.ncedu.tlt.dimi1.demo;

import implementations.DatabaseWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
public class AccountController {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AccountController(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @RequestMapping(value = "/accounts")
    String home(ModelMap model) {
        DatabaseWork dbw = new DatabaseWork(jdbcTemplate);
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("accounts", dbw.getByPersonIdAccounts(user.getUsername()));
        return "menu";
    }

    @RequestMapping(value = "/newAccount", method = RequestMethod.POST)
    String addAccount(@RequestParam("currency") String currency,
                      @RequestParam("description") String description,
                      @RequestParam("card_number") String cardNumber,
                      @RequestParam("balance") BigDecimal balance)
    {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        DatabaseWork dbw = new DatabaseWork(jdbcTemplate);
        if(description.equals("Cash"))
        {
            cardNumber = null;
        }

        if(cardNumber != null)
        {
            if(!dbw.checkAccountCardNumber(user.getUsername(), cardNumber))
            {
                dbw.addAccount(user.getUsername(),cardNumber,currency,balance,description);
                return "redirect:accounts?showModal";
            }
        }
        else
        {
            dbw.addAccount(user.getUsername(),cardNumber,currency,balance,description);
            return "redirect:accounts?showModal";
        }
        return "redirect:accounts?showModalError";
    }

    @RequestMapping(value = "/deleteAccount", method = RequestMethod.POST)
    String deleteAccount(ModelMap model, @RequestParam("accountId") String accountId)
    {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        DatabaseWork dbw = new DatabaseWork(jdbcTemplate);
        if(accountId != "")
        {
            dbw.deleteAccount(user.getUsername(), accountId);
        }
        return "redirect:accounts?showModal";
    }
}