package notes;

public class IncorrectArgumentException extends Exception {
    public IncorrectArgumentException(String errorMessage) {  
        super("Passed arguments are incorrect, " + errorMessage);  
    }
}