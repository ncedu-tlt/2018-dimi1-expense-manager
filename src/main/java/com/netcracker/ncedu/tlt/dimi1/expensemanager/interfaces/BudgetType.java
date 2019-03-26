package com.netcracker.ncedu.tlt.dimi1.expensemanager.interfaces;

import java.math.BigDecimal;

public interface BudgetType extends Entity {
    Integer getBudgetTypeId();
    Integer getGroupId();
    String getName();
    Boolean getRequired();
    BigDecimal getCheckMax();
}
