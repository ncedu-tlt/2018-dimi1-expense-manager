package implementations;

import interfaces.Budget;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Map;

public class BudgetImpl implements Budget {
    private BigInteger budgetId;
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
    public boolean load(BigInteger id) {
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

    /*public void showTable() {
        String qwre = "SELECT * FROM budget";
        BigInteger field1, field3, field5;
        Double field7;
        String field2, field4;
        java.sql.Date field6;
        try {
            Statement stmtBudg =connect.createStatement();
            ResultSet resultBudget = stmtBudg.executeQuery(qwre);
            while (resultBudget.next()){
                field1 = BigInteger.valueOf(resultBudget.getInt("budget_id"));
                field2 = resultBudget.getString("operation_type");
                field3 = BigInteger.valueOf(resultBudget.getInt("budget_type_id_fk"));
                field4 = resultBudget.getString("description");
                field5 = BigInteger.valueOf(resultBudget.getInt("account_id_fk"));
                field6 = resultBudget.getDate("operation_date");
                field7 = resultBudget.getDouble("charge_value");
                System.out.println(String.format("Id: %5d| operation type: %12s| budgetTypeId: %5d| description: %45s| " +
                                "accountId: %5d| operation date: %10tD| charge value: %7.2f",
                        field1, field2, field3, field4, field5, field6, field7));
            }
        } catch (SQLException e) {
            System.out.println("An error occured while displaying information from the database table BUDGET");
            e.printStackTrace();
        }
    }*/

    @Override
    public BigInteger getBudgetId() {
        return budgetId;
    }

    public void createUniqId() {
        DatabaseWork dbObj = new DatabaseWork(jdbcTemplate);
        budgetId = dbObj.getUniqBudgetId();
    }

    public void setBudgetId(BigInteger budgetId) {
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
