package interfaces;

import java.math.BigInteger;
import java.util.Date;

public interface Person extends Entity {
    BigInteger getPersonId();
    String getLogin();
    String getPass();
    String getEmail();
    String getDescription();
    String getPhonenumber();
    Date getRegDate();

}
