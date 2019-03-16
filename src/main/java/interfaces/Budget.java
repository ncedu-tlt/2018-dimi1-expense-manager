package interfaces;

import java.math.BigDecimal;
import java.util.Date;

public interface Budget extends Entity {
    Integer getBudgetId();
    String getOperationType();
    Integer getBudgetTypeId();
    String getDescription();
    Integer getAccountId();
    Date getOperationDate();
    BigDecimal getChargeValue();
}
