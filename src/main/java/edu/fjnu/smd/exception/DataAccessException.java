package edu.fjnu.smd.exception;

/**
 * Created by xiaozemaliya on 2017/1/31.
 */
public class DataAccessException extends SMDException {

    /**
     *
     */
    public DataAccessException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     */
    public DataAccessException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public DataAccessException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public DataAccessException(String message, Throwable cause,
                               boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

}