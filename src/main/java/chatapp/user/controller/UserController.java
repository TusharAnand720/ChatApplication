package chatapp.user.controller;

import authorization.lib.model.JwtClaims;
import authorization.lib.service.AuthService;

import chatapp.dbManager.table.user.TableUser;
import chatapp.middleware.APIRequest;
import chatapp.user.entity.RegistrationPayload;
import chatapp.middleware.ServiceResponse;
import chatapp.user.service.RegistrationHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RequestMapping(value = "/user")
@RestController
public class UserController {

    @Autowired
    private AuthService authService;

    @Autowired
    private TableUser tableUser;

    //
    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@RequestBody RegistrationPayload registrationPayload) {
        try {

            APIRequest apiRequest = new RegistrationHandler(authService,tableUser,registrationPayload);
            return apiRequest.doProcess();

        } catch (Exception e) {
            return ServiceResponse.BadRequest(e.getMessage());
        }
    }

    //
    @PostMapping(value = "/validate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> validate(HttpServletRequest servletRequest, @RequestBody RegistrationPayload registrationPayload) {
        try {
            System.out.println("Registering user");
            JwtClaims jwtClaims = authService.validateToken(servletRequest);
            System.out.println("jwtClaims : " + jwtClaims.getIssuer() + " " + jwtClaims.getSubject());
            HashMap<String, Object> response = new HashMap<>();
            response.put("claims", jwtClaims);
            return ServiceResponse.Success(response);
        } catch (Exception e) {
            return ServiceResponse.BadRequest(e.getMessage());
        }
    }
}
