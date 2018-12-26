package interfaces;

import java.math.BigInteger;
import java.util.Date;

public interface PlanBudget extends Entity {
    BigInteger getPlanBudgetId();
    String getOperationType();
    BigInteger getBudgetTypeId();
    String getDescription();
    BigInteger getAccountId();
    Date getOperationDate();
    Double getChargeValue();
    String getRegularMask();
    Integer getRepeatCount();
    Date getStartDate();
    Date getEndDate();
}
