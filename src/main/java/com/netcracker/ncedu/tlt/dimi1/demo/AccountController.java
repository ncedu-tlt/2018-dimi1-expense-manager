package com.netcracker.ncedu.tlt.dimi1.demo;

import implementations.DatabaseWork;
import interfaces.Person;
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
import java.math.BigInteger;
import java.util.ArrayList;

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
                jdbcTemplate.update("INSERT INTO Accounts (account_number, person_id_fk, currency, balance, description)\n" +
                        "VALUES ("+ cardNumber +", '"+ dbw.getPersonByLogin(user.getUsername()).getPersonId() +"'," +
                        " '"+ currency +"', '"+ balance +"'," + " '"+ description +"')");
                return "redirect:accounts?showModal";
            }
        }
        else
        {
            jdbcTemplate.update("INSERT INTO Accounts (account_number, person_id_fk, currency, balance, description)\n" +
                    "VALUES ("+ cardNumber +", '"+ dbw.getPersonByLogin(user.getUsername()).getPersonId() +"'," +
                    " '"+ currency +"', '"+ balance +"'," + " '"+ description +"')");
            return "redirect:accounts?showModal";
        }
        return "redirect:accounts?showModalError";
    }
}
