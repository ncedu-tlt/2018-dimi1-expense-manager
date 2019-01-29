package interfaces;

import java.math.BigInteger;

public interface BudgetType extends Entity {
    BigInteger getBudgetTypeId();
    BigInteger getGroupId();
    String getName();
    Boolean getRequired();
    Double getCheckMax();
}
