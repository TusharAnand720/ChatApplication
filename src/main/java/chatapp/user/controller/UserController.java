package chatapp.user.controller;

import authentication.lib.model.JwtClaims;
import authentication.lib.service.AuthService;
import chatapp.dbManager.table.user.ITableUser;
import chatapp.middleware.APIRequest;
import chatapp.middleware.ServiceResponse;
import chatapp.user.entity.LoginPayload;
import chatapp.user.entity.RegistrationPayload;
import chatapp.user.service.GetProfileHandler;
import chatapp.user.service.LoginHandler;
import chatapp.user.service.RegistrationHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/user")
@RestController
public class UserController {

    @Autowired
    private AuthService authService;

    @Autowired
    private ITableUser tableUser;

    @PostMapping(value = "/api/v1/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@RequestBody RegistrationPayload registrationPayload) {
        try {

            APIRequest apiRequest = new RegistrationHandler(authService, tableUser, registrationPayload);
            return apiRequest.doProcess();

        } catch (Exception e) {
            return ServiceResponse.BadRequest(e.getMessage());
        }
    }

    @PostMapping(value = "/api/v1/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody LoginPayload loginPayload) {
        try {

            APIRequest apiRequest = new LoginHandler(loginPayload, tableUser, authService);
            return apiRequest.doProcess();

        } catch (Exception e) {
            return ServiceResponse.BadRequest(e.getMessage());
        }
    }

    @GetMapping(value = "api/v1/profile", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProfile(HttpServletRequest httpServletRequest) {
        try {

            JwtClaims claims = authService.validateToken(httpServletRequest);

            APIRequest apiRequest = new GetProfileHandler(claims.getSubject(), tableUser);
            return apiRequest.doProcess();

        } catch (Exception e) {
            return ServiceResponse.BadRequest(e.getMessage());
        }
    }

    @GetMapping(value = "api/v1/rooms", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRooms(HttpServletRequest httpServletRequest, @RequestParam String userId) {
        try {

            JwtClaims claims = authService.validateToken(httpServletRequest);

//            APIRequest apiRequest = new GetProfileHandler(claims.getSubject(), tableUser);
//            return apiRequest.doProcess();

            return null;
        } catch (Exception e) {
            return ServiceResponse.BadRequest(e.getMessage());
        }
    }
}
