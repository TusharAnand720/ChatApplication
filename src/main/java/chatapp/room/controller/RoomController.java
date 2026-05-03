package chatapp.room.controller;

import chatapp.middleware.APIRequest;
import chatapp.middleware.ServiceResponse;
import chatapp.user.entity.RegistrationPayload;
import chatapp.user.service.RegistrationHandler;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "room")
@RestController
public class RoomController {

//    @PostMapping(value = "api/v1/create", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> create(@RequestBody RegistrationPayload registrationPayload) {
//        try {
//
//
//
//        } catch (Exception e) {
//            return ServiceResponse.BadRequest(e.getMessage());
//        }
//    }
}
