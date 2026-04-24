package chatapp.validation;

public enum ServiceError {

    ServiceError_user_already_exists_with_email("User already exists with email"),
    ServiceError_invalid_email("Invalid email"),
    ServiceError_invalid_password("Invalid password"),
    ServiceError_invalid_firstName("Invalid First Name "),
    Service_invalid_userName_or_password("Invalid user name or password"),
    ;

    private final String message;

    ServiceError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
