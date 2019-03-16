package interfaces;

import java.util.Date;

public interface Person extends Entity {
    Integer getPersonId();
    String getLogin();
    String getPass();
    String getEmail();
    String getDescription();
    String getPhonenumber();
    Date getRegDate();

}
