package model.exception;

public class WrongDataException extends Exception {

    public WrongDataException() {
    }

    public WrongDataException(String message) {
        super(message);
    }

    public WrongDataException(Exception e) {
        super(e);
    }

    public WrongDataException(String message, Exception e) {
        super(message, e);
    }
}
