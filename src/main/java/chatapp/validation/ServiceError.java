package chatapp.validation;

public enum ServiceError {

    user_already_exists_with_email("User already exists with email"),
    invalid_email("Invalid email"),
    invalid_password("Invalid password"),
    invalid_firstName("Invalid First Name "),
    invalid_userName_or_password("Invalid user name or password"),
    invalid_room_name("Invalid Room Name")
    ;

    private final String message;

    ServiceError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
