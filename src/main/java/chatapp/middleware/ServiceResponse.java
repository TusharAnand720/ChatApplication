package chatapp.middleware;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.HashMap;

public class ServiceResponse {

    private boolean success;
    private String message;
    private String responseCode;
    private HashMap<String, Object> resultBody = new HashMap<>();
    private long currentDT;

    public ServiceResponse() {

    }

    public static ResponseEntity<?> Success(HashMap<String, Object> resultBody) {
        HttpStatus status = HttpStatus.OK;
        return new Builder()
                .success(true)
                .message("SUCCESS")
                .currentDT(new Date().getTime())
                .responseCode(status.name())
                .resultBody(resultBody)
                .httpStatus(status)
                .build();
    }

    public static ResponseEntity<?> BadRequest(String message) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new Builder()
                .success(false)
                .message(message)
                .currentDT(new Date().getTime())
                .responseCode(status.name())
                .httpStatus(status)
                .build();
    }

    public static ResponseEntity<?> BadRequest() {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new Builder()
                .success(false)
                .message("FAILED")
                .currentDT(new Date().getTime())
                .responseCode(status.name())
                .httpStatus(status)
                .build();
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public HashMap<String, Object> getResultBody() {
        return resultBody;
    }

    public void setResultBody(HashMap<String, Object> resultBody) {
        this.resultBody = resultBody;
    }

    public long getCurrentDT() {
        return currentDT;
    }

    public void setCurrentDT(long currentDT) {
        this.currentDT = currentDT;
    }

    private static class Builder {

        private boolean success;
        private String message;
        private String responseCode;
        private HashMap<String, Object> resultBody = new HashMap<String, Object>();
        private long currentDT;
        private HttpStatus httpStatus;

        private ResponseEntity<?> build() {
            ServiceResponse serviceResponse = new ServiceResponse();
            serviceResponse.message = this.message;
            serviceResponse.responseCode = this.responseCode;
            serviceResponse.success = this.success;
            serviceResponse.currentDT = this.currentDT;
            serviceResponse.resultBody = this.resultBody;
            return new ResponseEntity<>(serviceResponse, httpStatus);
        }

        public Builder success(boolean success) {
            this.success = success;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder responseCode(String responseCode) {
            this.responseCode = responseCode;
            return this;
        }

        public Builder resultBody(HashMap<String, Object> resultBody) {
            this.resultBody = resultBody;
            return this;
        }

        public Builder currentDT(long currentDT) {
            this.currentDT = currentDT;
            return this;
        }

        public Builder httpStatus(HttpStatus httpStatus) {
            this.httpStatus = httpStatus;
            return this;
        }
    }
}
