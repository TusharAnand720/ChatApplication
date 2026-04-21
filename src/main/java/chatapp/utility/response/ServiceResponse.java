package chatapp.utility.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public class ServiceResponse {

    private boolean success;
    private String message;
    private int statusCode;
    private HashMap<String, Object> response = new HashMap<>();
    private long currentDT;

    private ServiceResponse setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    private ServiceResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    //

    private ServiceResponse setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    private ServiceResponse setResponse(HashMap<String, Object> response) {
        this.response = response;
        return this;
    }

    private ServiceResponse setCurrentDT(long currentDT) {
        this.currentDT = currentDT;
        return this;
    }

    // =================================

    private static ServiceResponse success() {
        return new ServiceResponse().setSuccess(true).setMessage("success").setStatusCode(HttpStatus.OK.value());
    }

    private static ServiceResponse success(HttpStatus status) {
        return new ServiceResponse().setSuccess(true).setMessage("success").setStatusCode(status.value());
    }

    private static ServiceResponse failed() {
        return new ServiceResponse().setSuccess(false).setMessage("failed").setStatusCode(HttpStatus.BAD_REQUEST.value());
    }

    private static ServiceResponse failed(HttpStatus status) {
        return new ServiceResponse().setSuccess(false).setMessage("failed").setStatusCode(status.value());
    }

    private static ServiceResponse failed(String message) {
        return new ServiceResponse().setSuccess(false).setMessage(message).setStatusCode(HttpStatus.BAD_REQUEST.value());
    }

    private static ServiceResponse failed(String message, HttpStatus status) {
        return new ServiceResponse().setSuccess(false).setMessage(message).setStatusCode(status.value());
    }

    // =================================

    public static ResponseEntity<?> Success(HashMap<String, Object> response) {
        return success().setResponse(response).build();
    }

    public static ResponseEntity<?> BadRequest() {
        return failed().setResponse(null).build();
    }

    public static ResponseEntity<?> BadRequest(String message) {
        return failed(message).setResponse(null).build();
    }

    public static ResponseEntity<?> Failed(HttpStatus status) {
        return failed(status).setResponse(null).build();
    }

    public static ResponseEntity<?> Failed(String message) {
        return failed(message).setResponse(null).build();
    }

    public static ResponseEntity<?> Failed(String message, HttpStatus status) {
        return failed(message, status).setResponse(null).build();
    }

    // =================================

    public ResponseEntity<?> build() {
        return new ResponseEntity<>(this, HttpStatus.OK);
    }
}
