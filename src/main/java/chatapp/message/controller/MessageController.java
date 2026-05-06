package chatapp.message.controller;

import chatapp.middleware.ServiceResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/message")
@RestController
public class MessageController {

    @GetMapping(value = "/history", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMessageHistory(@Autowired HttpServletRequest httpServletRequest, @RequestParam String roomId) {

        try {

            return null;
        } catch (Exception e) {
            return ServiceResponse.BadRequest(e.getMessage());
        }

    }
}
