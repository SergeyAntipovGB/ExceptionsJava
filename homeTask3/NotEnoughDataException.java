package homeTask3;

public class NotEnoughDataException extends RuntimeException {
    private final int exceptionCode;

    public int getExceptionCode() {
        return exceptionCode;
    }

    public NotEnoughDataException(String message, int exceptionCode) {
        super(message);
        this.exceptionCode = exceptionCode;
    }
}
