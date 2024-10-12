package lk.ijse.gdse68.aad.posbackend.excexption;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException() {
        super();
    }

    public CustomerNotFoundException(String message) {
        super(message);
    }

    public CustomerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
