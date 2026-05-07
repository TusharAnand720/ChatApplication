package chatapp.middleware;

import org.springframework.http.ResponseEntity;

public interface APIRequest {

    ResponseEntity<?> doProcess();
}
