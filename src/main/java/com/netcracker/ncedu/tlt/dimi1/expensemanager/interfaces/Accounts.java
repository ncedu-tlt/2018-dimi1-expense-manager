package com.netcracker.ncedu.tlt.dimi1.expensemanager.interfaces;

import java.math.BigDecimal;

public interface Accounts extends Entity {
    Integer getAccountId();
    Integer getPersonId();
    String getCurrency();
    BigDecimal getBalance();
    String getDescription();
}
