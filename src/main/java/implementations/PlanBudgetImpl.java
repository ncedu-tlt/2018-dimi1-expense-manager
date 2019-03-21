package implementations;

import interfaces.PlanBudget;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Data
public class PlanBudgetImpl implements PlanBudget {
    private Integer planBudgetId;
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
    private Boolean spliter;
    private JdbcTemplate jdbcTemplate;
    Logger log = LoggerFactory.getLogger(PlanBudgetImpl.class);

    public PlanBudgetImpl(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate; }

    @Override
    public void create() {
        String insertPlanBudg = "INSERT INTO plan_budget (plan_budget_id, operation_type, budget_type_id_fk, description, " +
                "account_id_fk, operation_date, charge_value, regular_mask, repeat_count, start_date, end_date, spliter) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(insertPlanBudg, planBudgetId, operationType, budgetTypeId, description,
                accountId, operationDate, chargeValue, regularMask, repeatCount, startDate, endDate, spliter);
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
                "repeat_count = ?, start_date = ?, end_date = ?, spliter = ? " +
                "WHERE plan_budget_id = ?";
        jdbcTemplate.update(updatePlanBudg, operationType, budgetTypeId, description, accountId,
                operationDate, chargeValue, regularMask, repeatCount, startDate, endDate, spliter, planBudgetId);
    }

    @Override
    public boolean load(Integer id) {
        String checkExistPlanBudget = "SELECT COUNT(*) AS cnt FROM plan_budget WHERE plan_budget_id = ?";
        Integer checkResult = jdbcTemplate.queryForObject(checkExistPlanBudget, Integer.class, id);
        if(checkResult != 0){
            String dataPlanBudget = "SELECT operation_type, budget_type_id_fk, description," +
                    "account_id_fk, operation_date, charge_value, regular_mask," +
                    "repeat_count, start_date, end_date, spliter " +
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
            this.spliter = (Boolean) result.get("SPLITER");
            return true;
        } else {
            log.info("Plan budget with the specified ID is not in the table PLAN_BUDGET");
        }
        return false;
    }

    public void createUniqId(){
        DatabaseWork dbObj = new DatabaseWork(jdbcTemplate);
        planBudgetId = dbObj.getUniqPlanBudgetId();
    }

}
