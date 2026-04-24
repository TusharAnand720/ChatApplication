package chatapp.validation;

public enum ServiceError {

    ServiceError_user_already_exists_with_email("User already exists with email"),
    ServiceError_invalid_email("Invalid email"),
    ServiceError_invalid_password("Invalid password"),
    ServiceError_invalid_userName("Invalid user name "),
    Service_invalid_userName_or_password("Invalid user name or password"),
    ServiceError_invalid_long_url("Invalid long url"),
    ServiceError_short_url_not_found("Short url not found"),
    ;

    private final String message;

    ServiceError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
