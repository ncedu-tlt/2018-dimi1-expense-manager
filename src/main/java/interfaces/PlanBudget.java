package interfaces;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public interface PlanBudget extends Entity {
    Integer getPlanBudgetId();
    String getOperationType();
    Integer getBudgetTypeId();
    String getDescription();
    Integer getAccountId();
    Date getOperationDate();
    BigDecimal getChargeValue();
    String getRegularMask();
    Integer getRepeatCount();
    Date getStartDate();
    Date getEndDate();
    Boolean getSpliter();
}
