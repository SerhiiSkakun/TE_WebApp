package exceptions;

import java.sql.SQLException;

public class CantFindCountryException extends Exception {
    public CantFindCountryException(SQLException cause) {
        super(cause);
    }
}
