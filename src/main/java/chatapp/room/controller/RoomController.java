package chatapp.room.controller;

import authorization.lib.model.JwtClaims;
import authorization.lib.service.AuthService;
import chatapp.dbManager.table.entityuser.TableEntityUser;
import chatapp.dbManager.table.room.TableRoom;
import chatapp.middleware.APIRequest;
import chatapp.middleware.ServiceResponse;
import chatapp.room.entity.RoomPayload;
import chatapp.room.service.CreateRoomRequest;
import chatapp.room.service.DeleteRoomRequest;
import chatapp.room.service.UpdateRoomRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping(value = "/api/v1/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@Autowired HttpServletRequest httpServletRequest, @RequestParam String roomId ,@RequestBody RoomPayload roomPayload) {
        try {

            JwtClaims claims  = authService.validateToken(httpServletRequest);

            APIRequest apiRequest = new UpdateRoomRequest(claims,tableRoom,roomId,roomPayload);
            return apiRequest.doProcess();

        } catch (Exception e) {
            return ServiceResponse.BadRequest(e.getMessage());
        }
    }


    @DeleteMapping(value = "/api/v1/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@Autowired HttpServletRequest httpServletRequest, @RequestParam String roomId) {
        try {

            JwtClaims claims  = authService.validateToken(httpServletRequest);

            APIRequest apiRequest = new DeleteRoomRequest(claims,roomId,tableRoom);
            return apiRequest.doProcess();

        } catch (Exception e) {
            return ServiceResponse.BadRequest(e.getMessage());
        }
    }
}
