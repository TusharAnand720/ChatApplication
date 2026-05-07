package chatapp.message.controller;

import authentication.lib.model.JwtClaims;
import authentication.lib.service.AuthService;
import chatapp.dbManager.table.entityuser.ITableEntityUser;
import chatapp.dbManager.table.message.ITableMessage;
import chatapp.dbManager.table.room.ITableRoom;
import chatapp.message.service.GetChatHistory;
import chatapp.middleware.APIRequest;
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

    @Autowired
    private ITableMessage tableMessage;

    @Autowired
    private ITableRoom tableRoom;

    @Autowired
    private ITableEntityUser tableEntityUser;

    @Autowired
    private AuthService authService;

    @GetMapping(value = "api/v1/history", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMessageHistory(@Autowired HttpServletRequest httpServletRequest,
                                               @RequestParam String roomId,
                                               @RequestParam int pageNumber,
                                               @RequestParam int pageSize) {

        try {
            JwtClaims jwtClaims = authService.validateToken(httpServletRequest);
            APIRequest apiRequest = new GetChatHistory(roomId, jwtClaims.getSubject(),
                    pageNumber, pageSize, tableRoom, tableMessage, tableEntityUser);
            return apiRequest.doProcess();
        } catch (Exception e) {
            return ServiceResponse.BadRequest(e.getMessage());
        }

    }
}
