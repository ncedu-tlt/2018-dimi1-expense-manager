package interfaces;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface BudgetType extends Entity {
    BigInteger getBudgetTypeId();
    Integer getGroupId();
    String getName();
    Boolean getRequired();
    BigDecimal getCheckMax();
}
