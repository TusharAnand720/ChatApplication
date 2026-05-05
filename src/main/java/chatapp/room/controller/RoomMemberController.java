package chatapp.room.controller;

import authentication.lib.model.JwtClaims;
import authentication.lib.service.AuthService;
import chatapp.dbManager.table.entityuser.TableEntityUser;
import chatapp.dbManager.table.room.TableRoom;
import chatapp.dbManager.table.user.TableUser;
import chatapp.middleware.APIRequest;
import chatapp.middleware.ServiceResponse;
import chatapp.room.service.GetRoomMembersRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/room-member")
@RestController
public class RoomMemberController {

    @Autowired
    private AuthService authService;

    @Autowired
    private TableEntityUser tableEntityUser;

    @Autowired
    private TableRoom tableRoom;

    @Autowired
    private TableUser tableUser;

    @GetMapping(value = "/api/v1/get",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRoomMembers(@Autowired HttpServletRequest httpServletRequest, @RequestParam String roomId,
                                            @RequestParam int pageNumber ,@RequestParam int pageSize){
        try{

            JwtClaims claims = authService.validateToken(httpServletRequest);
            APIRequest apiRequest = new GetRoomMembersRequest(roomId,pageNumber,pageSize,tableEntityUser,tableRoom,tableUser);
            return apiRequest.doProcess();
        }catch (Exception e){
            return ServiceResponse.BadRequest(e.getMessage());
        }
    }

}
