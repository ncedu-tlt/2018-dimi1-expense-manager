package implementations;

import interfaces.PlanBudget;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Map;

public class PlanBudgetImpl implements PlanBudget {
    private BigInteger planBudgetId;
    private String operationType;
    private Integer budgetTypeId;
    private String description;
    private Integer accountId;
    private Date operationDate;
    private BigDecimal chargeValue;
    private String regularMask;
    private Integer repeatCount;
    private Date startDate;
    private Date endDate;
    private JdbcTemplate jdbcTemplate;

    public PlanBudgetImpl(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate; }

    @Override
    public void create() {
        String insertPlanBudg = "INSERT INTO plan_budget (plan_budget_id, operation_type, budget_type_id_fk, description, " +
                "account_id_fk, operation_date, charge_value, regular_mask, repeat_count, start_date, end_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(insertPlanBudg, planBudgetId, operationType, budgetTypeId, description,
                accountId, operationDate, chargeValue, regularMask, repeatCount, startDate, endDate);
    }

    @Override
    public void delete() {
        String deletPlanBudg = "DELETE FROM plan_budget WHERE plan_budget_id = ?";
        jdbcTemplate.update(deletPlanBudg, planBudgetId);
    }

    @Override
    public void update() {
        String updatePlanBudg = "UPDATE plan_budget SET operation_type = ?, budget_type_id_fk = ?, description = ?, " +
                "account_id_fk = ?, operation_date = ?, charge_value = ?, regular_mask = ?, " +
                "repeat_count = ?, start_date = ?, end_date = ?" +
                "WHERE plan_budget_id = ?";
        jdbcTemplate.update(updatePlanBudg, operationType, budgetTypeId, description, accountId,
                operationDate, chargeValue, regularMask, repeatCount, startDate, endDate, planBudgetId);
    }

    @Override
    public boolean load(BigInteger id) {
        String checkExistPlanBudget = "SELECT COUNT(*) AS cnt FROM plan_budget WHERE plan_budget_id = ?";
        Integer checkResult = jdbcTemplate.queryForObject(checkExistPlanBudget, Integer.class, id);
        if(checkResult != 0){
            String dataPlanBudget = "SELECT operation_type, budget_type_id_fk, description," +
                    "account_id_fk, operation_date, charge_value, regular_mask," +
                    "repeat_count, start_date, end_date " +
                    "FROM plan_budget WHERE plan_budget_id = ?";
            Map result = jdbcTemplate.queryForMap(dataPlanBudget, id);
            this.planBudgetId = id;
            this.operationType = (String) result.get("OPERATION_TYPE");
            this.budgetTypeId = (Integer) result.get("BUDGET_TYPE_ID_FK");//Заменила тип на Integer
            this.description = (String) result.get("DESCRIPTION");
            this.accountId = (Integer) result.get("ACCOUNT_ID_FK");//Заменила тип на Integer
            this.operationDate = (Date) result.get("OPERATION_DATE");
            this.chargeValue = (BigDecimal) result.get("CHARGE_VALUE");//Заменила тип на BigDecimal
            this.regularMask = (String) result.get("REGULAR_MASK");
            this.repeatCount = (Integer) result.get("REPEAT_COUNT");
            this.startDate = (Date) result.get("START_DATE");
            this.endDate = (Date) result.get("END_DATE");
            return true;
        } else {
            System.out.println("Plan budget with the specified ID is not in the table PLAN_BUDGET");
        }
        return false;
    }

    /*public void showTable(){
        String qwre = "SELECT * FROM plan_budget";
        int field1, field3, field5, field9;
        Double field7;
        String field2, field4, field8;
        java.sql.Date field6, field10, field11;
        try {
            Statement stmtPlanBudg =connect.createStatement();
            ResultSet resultPlanBudget = stmtPlanBudg.executeQuery(qwre);
            while (resultPlanBudget.next()){
                field1 = resultPlanBudget.getInt("plan_budget_id");
                field2 = resultPlanBudget.getString("operation_type");
                field3 = resultPlanBudget.getInt("budget_type_id_fk");
                field4 = resultPlanBudget.getString("description");
                field5 = resultPlanBudget.getInt("account_id_fk");
                field6 = resultPlanBudget.getDate("operation_date");
                field7 = resultPlanBudget.getDouble("charge_value");
                field8 = resultPlanBudget.getString("regular_mask");
                field9 = resultPlanBudget.getInt("repeat_count");
                field10 = resultPlanBudget.getDate("start_date");
                field11 = resultPlanBudget.getDate("end_date");
                System.out.println(String.format("Id: %5d| operation type: %12s| budgetTypeId: %5d| description: %35s| " +
                                "accountId: %5d| operation date: %10tD| charge value: %7.2f| regular mask: %20s| " +
                                "repeat count: %4d| start date: %10tD| end date: %10td",
                        field1, field2, field3, field4, field5, field6, field7, field8, field9, field10, field11));
            }
        } catch (SQLException e) {
            System.out.println("An error occured while displaying information from the database table PLAN BUDGET");
            e.printStackTrace();
        }
    }*/

    @Override
    public BigInteger getPlanBudgetId() {
        return planBudgetId;
    }

    public void createUniqId(){
        DatabaseWork dbObj = new DatabaseWork(jdbcTemplate);
        planBudgetId = dbObj.getUniqPlanBudgetId();
    }

    public void setPlanBudgetId(BigInteger planBudgetId) {
        this.planBudgetId = planBudgetId;
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

    @Override
    public String getRegularMask() {
        return regularMask;
    }

    public void setRegularMask(String regularMask) {
        this.regularMask = regularMask;
    }

    @Override
    public Integer getRepeatCount() {
        return repeatCount;
    }

    public void setRepeatCount(Integer repeatCount) {
        this.repeatCount = repeatCount;
    }

    @Override
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
