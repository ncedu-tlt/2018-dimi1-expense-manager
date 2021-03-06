package com.netcracker.ncedu.tlt.dimi1.expensemanager.interfaces;

import java.math.BigDecimal;
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
