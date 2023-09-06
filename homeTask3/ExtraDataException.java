package homeTask3;

public class ExtraDataException extends RuntimeException {
    private final int exceptionCode;

    public int getExceptionCode() {
        return exceptionCode;
    }

    public ExtraDataException(String message) {
        super(message);
        this.exceptionCode = 0;
    }

    public ExtraDataException(String message, int exceptionCode) {
        super(message);
        this.exceptionCode = exceptionCode;
    }
}
