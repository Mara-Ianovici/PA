package exceptions;

public class EmptyItemListException extends Exception{
    public EmptyItemListException(String errorMessage) {
        super(errorMessage);
    }
}
