package interfaces;

import java.math.BigDecimal;

public interface BudgetType extends Entity {
    Integer getBudgetTypeId();
    Integer getGroupId();
    String getName();
    Boolean getRequired();
    BigDecimal getCheckMax();
}
