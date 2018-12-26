package interfaces;

import java.math.BigInteger;

public interface Person extends Entity {
    BigInteger geAccountId();
    BigInteger getPersonId();
    String getCurrency();
    Double getBalance();
    String getDescription();
}
