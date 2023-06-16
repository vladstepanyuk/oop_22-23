package factory.exception;

public class SupplierException extends FactoryException{
    public SupplierException() {
    }

    public SupplierException(String message) {
        super(message);
    }

    public SupplierException(String message, Throwable cause) {
        super(message, cause);
    }
}
