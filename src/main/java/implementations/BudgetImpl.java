package implementations;

import interfaces.Budget;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class BudgetImpl implements Budget {
    private Integer budgetId;
    private String operationType;
    private Integer budgetTypeId;
    private String description;
    private Integer accountId;
    private Date operationDate;
    private BigDecimal chargeValue;
    private JdbcTemplate jdbcTemplate;

    public BudgetImpl(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate; }

    @Override
    public void create() {
        String insertBudget = "INSERT INTO budget (budget_id, operation_type, budget_type_id_fk, description, " +
                "account_id_fk, operation_date, charge_value) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(insertBudget, budgetId, operationType, budgetTypeId, description,
                accountId, operationDate, chargeValue);
    }

    @Override
    public void delete() {
        String deletBudget = "DELETE FROM budget WHERE budget_id = ?";
        jdbcTemplate.update(deletBudget, budgetId);
    }

    @Override
    public void update() {
        String updateBudg = "UPDATE budget SET operation_type = ?, budget_type_id_fk = ?, description = ?, " +
                "account_id_fk = ?, operation_date = ?, charge_value = ? " +
                "WHERE budget_id = ?";
        jdbcTemplate.update(updateBudg, operationType, budgetTypeId, description, accountId,
                operationDate, chargeValue, budgetId);
    }

    @Override
    public boolean load(Integer id) {
        String checkExistBudget = "SELECT COUNT(*) AS cnt FROM budget WHERE budget_id = ?";
        Integer checkResult = jdbcTemplate.queryForObject(checkExistBudget, Integer.class, id);
        if(checkResult != 0){
            String dataBudget = "SELECT operation_type, budget_type_id_fk, description, " +
                    "account_id_fk, operation_date, charge_value " +
                    "FROM budget WHERE budget_id = ?";
            Map result = jdbcTemplate.queryForMap(dataBudget, id);
            this.budgetId = id;
            this.operationType = (String) result.get("OPERATION_TYPE");
            this.budgetTypeId = (Integer) result.get("BUDGET_TYPE_ID_FK");//Заменила тип на Integer
            this.description = (String) result.get("DESCRIPTION");
            this.accountId = (Integer) result.get("ACCOUNT_ID_FK");//Заменила тип на Integer
            this.operationDate = (Date) result.get("OPERATION_DATE");
            this.chargeValue = (BigDecimal) result.get("CHARGE_VALUE");//Заменила тип на BigDecimal
            return true;
        } else {
            System.out.println("Budget with the specified ID is not in the table BUDGET");
        }
        return false;
    }

    @Override
    public Integer getBudgetId() {
        return budgetId;
    }

    public void createUniqId() {
        DatabaseWork dbObj = new DatabaseWork(jdbcTemplate);
        budgetId = dbObj.getUniqBudgetId();
    }

    public void setBudgetId(Integer budgetId) {
        this.budgetId = budgetId;
    }

    @Override
    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    @Override
    public Integer getBudgetTypeId() {
        return budgetTypeId;
    }

    public void setBudgetTypeId(Integer budgetTypeId) {
        this.budgetTypeId = budgetTypeId;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    @Override
    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    @Override
    public BigDecimal getChargeValue() {
        return chargeValue;
    }

    public void setChargeValue(BigDecimal chargeValue) {
        this.chargeValue = chargeValue;
    }
}
