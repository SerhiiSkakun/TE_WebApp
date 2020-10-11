package exceptions;

import java.sql.SQLException;

public class CantFindWatchException extends Exception {
    public CantFindWatchException(SQLException cause) {
        super(cause);
    }
}
