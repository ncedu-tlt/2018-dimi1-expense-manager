package implementations;

import interfaces.BudgetType;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

public class BudgetTypeImpl implements BudgetType {
    private BigInteger budgetTypeId;
    private Integer groupId;
    private String name;
    private Boolean isRequired;
    private BigDecimal checkMax;
    private JdbcTemplate jdbcTemplate;

    public BudgetTypeImpl(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate; }

    @Override
    public void create() {
        String insertBudgetType = "INSERT INTO budget_type (budget_type_id, group_id, name, required, check_max) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(insertBudgetType, budgetTypeId, groupId, name, isRequired, checkMax);
    }

    @Override
    public void delete() {
        String checkQuery = "SELECT COUNT(*) AS cnt FROM budget_type WHERE group_id = ?";
        String checkPlanBudg = "SELECT COUNT(*) AS cnt FROM plan_budget WHERE budget_type_id_fk = ?";
        String checkBudg = "SELECT COUNT(*) AS cnt FROM budget WHERE budget_type_id_fk = ?";
        DatabaseWork check = new DatabaseWork(jdbcTemplate);
        if(check.checkExist(checkQuery, budgetTypeId) != 0 || check.checkExist(checkPlanBudg, budgetTypeId) != 0 ||
                check.checkExist(checkBudg, budgetTypeId) != 0){
            System.out.println("This record has a link in the other table(s).\nDelete all related entries first.");
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
        jdbcTemplate.update(updateBudgetType, groupId, name, isRequired, checkMax, budgetTypeId);
    }

    @Override
    public boolean load(BigInteger id) {
        String checkExistBudgetType = "SELECT COUNT(*) AS cnt FROM budget_type WHERE budget_type_id = ?";
        Integer checkResult = jdbcTemplate.queryForObject(checkExistBudgetType, Integer.class, id);
        if(checkResult != 0){
            String dataBudgetType = "SELECT group_id, name, required, check_max FROM budget_type WHERE budget_type_id = ?";
            Map result = jdbcTemplate.queryForMap(dataBudgetType, id);
            this.budgetTypeId = id;
            this.groupId = (Integer)result.get("GROUP_ID");//Заменила тип на Integer
            this.name = (String)result.get("NAME");
            this.isRequired = (Boolean) result.get("REQUIRED");
            this.checkMax = (BigDecimal)result.get("CHECK_MAX");//Заменила тип на BigDecimal
            return true;
        } else {
            System.out.println("Budget type with the specified ID is not in the table BUDGET_TYPE");
        }
        return false;
    }

    public boolean isGroupExsist(int checkGroup){
        String qwr = "SELECT budget_type_id, group_id " +
                "FROM budget_type " +
                "WHERE group_id IS NULL";
        Integer checkExist = jdbcTemplate.queryForObject(qwr, Integer.class);
        if(checkGroup == checkExist){
            return true;
        } else {
            System.out.println("The group with the specified id is missing...");
        }
        return false;
    }

    @Override
    public BigInteger getBudgetTypeId() {
        return budgetTypeId;
    }

    public void createUniqId(){
        DatabaseWork dbObj = new DatabaseWork(jdbcTemplate);
        budgetTypeId = dbObj.getUniqBudgetTypeId();
    }

    public void setBudgetTypeId(BigInteger budgetTypeId) {
        this.budgetTypeId = budgetTypeId;
    }

    @Override
    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Boolean getRequired() {
        return isRequired;
    }

    public void setRequired(Boolean required) {
        isRequired = required;
    }

    @Override
    public BigDecimal getCheckMax() {
        return checkMax;
    }

    public void setCheckMax(BigDecimal checkMax) {
        this.checkMax = checkMax;
    }
}
