package training.ua.dao;

public class DaoException extends Exception {

    DaoException(){}

    DaoException(String message){
        super(message);
    }

    public DaoException(String message, Throwable cause){
        super(message, cause);
    }
}
