package chatapp.room.controller;

import authorization.lib.model.JwtClaims;
import authorization.lib.service.AuthService;
import chatapp.dbManager.table.entityuser.TableEntityUser;
import chatapp.dbManager.table.room.TableRoom;
import chatapp.middleware.APIRequest;
import chatapp.middleware.ServiceResponse;
import chatapp.room.entity.RoomPayload;
import chatapp.room.service.CreateRoomRequest;
import chatapp.user.entity.RegistrationPayload;
import chatapp.user.service.RegistrationHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/room")
@RestController
public class RoomController {

    @Autowired
    private AuthService authService;

    @Autowired
    private TableRoom tableRoom;

    @Autowired
    private TableEntityUser tableEntityUser;

    @PostMapping(value = "/api/v1/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@Autowired HttpServletRequest httpServletRequest, @RequestBody RoomPayload roomPayload) {
        try {

            JwtClaims claims  = authService.validateToken(httpServletRequest);

            APIRequest apiRequest = new CreateRoomRequest(claims,roomPayload,tableRoom,tableEntityUser);
            return apiRequest.doProcess();

        } catch (Exception e) {
            return ServiceResponse.BadRequest(e.getMessage());
        }
    }
}
