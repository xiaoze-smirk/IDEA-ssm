package edu.fjnu.smd.exception;

/**
 * Created by xiaozemaliya on 2017/1/31.
 */
public class SMDException extends RuntimeException {

    /**
     *
     */
    public SMDException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     */
    public SMDException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public SMDException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public SMDException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public SMDException(String message, Throwable cause,
                        boolean enableSuppression, boolean writableStackTrace) {

    }

}
