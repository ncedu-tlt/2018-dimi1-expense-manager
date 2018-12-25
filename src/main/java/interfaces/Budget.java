package interfaces;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

public interface Budget extends Entity {
    BigInteger getBudgetId();
    String getOperationType();
    BigInteger getBudgetTypeId();
    String getDescription();
    BigInteger getAccountId();
    Date getOperationDate();
    Double getChargeValue();
    Budget getBudget();
}
