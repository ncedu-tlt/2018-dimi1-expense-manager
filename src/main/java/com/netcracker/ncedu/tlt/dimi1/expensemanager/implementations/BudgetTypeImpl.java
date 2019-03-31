package com.netcracker.ncedu.tlt.dimi1.expensemanager.implementations;

import com.netcracker.ncedu.tlt.dimi1.expensemanager.tools.DatabaseWork;
import com.netcracker.ncedu.tlt.dimi1.expensemanager.interfaces.BudgetType;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
public class BudgetTypeImpl implements BudgetType {
    private Integer budgetTypeId;
    private Integer groupId;
    private String name;
    private Boolean required;
    private BigDecimal checkMax;
    private JdbcTemplate jdbcTemplate;
    Logger log = LoggerFactory.getLogger(BudgetTypeImpl.class);
    public BudgetTypeImpl(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate; }

    @Override
    public void create() {
        String insertBudgetType = "INSERT INTO budget_type (budget_type_id, group_id, name, required, check_max) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(insertBudgetType, budgetTypeId, groupId, name, required, checkMax);
    }

    @Override
    public void delete() {
        String checkQuery = "SELECT COUNT(*) AS cnt FROM budget_type WHERE group_id = ?";
        String checkPlanBudg = "SELECT COUNT(*) AS cnt FROM plan_budget WHERE budget_type_id_fk = ?";
        String checkBudg = "SELECT COUNT(*) AS cnt FROM budget WHERE budget_type_id_fk = ?";
        DatabaseWork check = new DatabaseWork(jdbcTemplate);
        if(check.checkExist(checkQuery, budgetTypeId) != 0 || check.checkExist(checkPlanBudg, budgetTypeId) != 0 ||
                check.checkExist(checkBudg, budgetTypeId) != 0){
            log.info("This record has a link in the other table(s). Delete all related entries first.");
            return;
        } else {
            String deletBudgetType = "DELETE FROM budget_type WHERE budget_type_id = ?";
            jdbcTemplate.update(deletBudgetType, budgetTypeId);
        }
    }

    @Override
    public void update() {
        String updateBudgetType = "UPDATE budget_type SET group_id = ?, name = ?, " +
                "required = ?, check_max = ? " +
                "WHERE budget_type_id = ?";
        jdbcTemplate.update(updateBudgetType, groupId, name, required, checkMax, budgetTypeId);
    }

    @Override
    public boolean load(Integer id) {
        String checkExistBudgetType = "SELECT COUNT(*) AS cnt FROM budget_type WHERE budget_type_id = ?";
        Integer checkResult = jdbcTemplate.queryForObject(checkExistBudgetType, Integer.class, id);
        if(checkResult != 0){
            String dataBudgetType = "SELECT group_id, name, required, check_max FROM budget_type WHERE budget_type_id = ?";
            Map result = jdbcTemplate.queryForMap(dataBudgetType, id);
            this.budgetTypeId = id;
            this.groupId = (Integer)result.get("GROUP_ID");//Заменила тип на Integer
            this.name = (String)result.get("NAME");
            this.required = (Boolean) result.get("REQUIRED");
            this.checkMax = (BigDecimal)result.get("CHECK_MAX");//Заменила тип на BigDecimal
            return true;
        } else {
            log.info("Budget type with the specified ID is not in the table BUDGET_TYPE");
        }
        return false;
    }

    public boolean isGroupExsist(int checkGroup){
        String qwr = "SELECT budget_type_id " +
                "FROM budget_type " +
                "WHERE group_id IS NULL";
        List<Integer> checkExist = jdbcTemplate.queryForList(qwr, Integer.class);
        if(checkExist.contains(checkGroup)){
            return true;
        } else {
            log.info("The group with the specified id is missing...");
        }
        return false;
    }

    public void createUniqId(){
        DatabaseWork dbObj = new DatabaseWork(jdbcTemplate);
        budgetTypeId = dbObj.getUniqBudgetTypeId();
    }

}
