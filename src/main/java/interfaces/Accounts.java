package interfaces;

        import java.math.BigDecimal;
        import java.math.BigInteger;

public interface Accounts extends Entity {
    BigInteger getAccountId();
    Integer getPersonId();
    String getCurrency();
    BigDecimal getBalance();
    String getDescription();
}
