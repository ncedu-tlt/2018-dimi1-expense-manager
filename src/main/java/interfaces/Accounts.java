package interfaces;

        import java.math.BigInteger;

public interface Accounts extends Entity {
    BigInteger getAccountId();
    BigInteger getPersonId();
    String getCurrency();
    Double getBalance();
    String getDescription();
}
