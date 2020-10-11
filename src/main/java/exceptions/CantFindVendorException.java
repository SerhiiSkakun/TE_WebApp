package exceptions;

import java.sql.SQLException;

public class CantFindVendorException extends Throwable {
    public CantFindVendorException(SQLException cause) {
        super(cause);
    }
}
