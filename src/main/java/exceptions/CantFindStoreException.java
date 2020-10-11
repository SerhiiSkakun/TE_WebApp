package exceptions;

import java.sql.SQLException;

public class CantFindStoreException extends Exception {
    public CantFindStoreException(SQLException cause) {
        super(cause);
    }
}
