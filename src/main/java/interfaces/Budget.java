package interfaces;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public interface Budget extends Entity {
    BigInteger getBudgetId();
    String getOperationType();
    Integer getBudgetTypeId();
    String getDescription();
    Integer getAccountId();
    Date getOperationDate();
    BigDecimal getChargeValue();
}
