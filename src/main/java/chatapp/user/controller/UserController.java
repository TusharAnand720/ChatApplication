package chatapp.user.controller;

import authorization.lib.model.AuthToken;
import authorization.lib.model.JwtClaims;
import authorization.lib.service.AuthService;

import chatapp.dbManager.entity.User;
import chatapp.dbManager.mongoDbManager.MongoConfig;
import chatapp.dbManager.service.UserService;
import chatapp.user.entity.RegistrationPayload;
import chatapp.utility.response.ServiceResponse;
import com.mongodb.client.MongoClient;
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
    private UserService userService;

    //
    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@RequestBody RegistrationPayload registrationPayload) {
        try {


//            System.out.println("Registering user");
//            AuthToken authToken = authService.issueToken("USER-TUSHAR");
//            System.out.println("Token issued : " + authToken.getRawToken());
//            HashMap<String, Object> response = new HashMap<>();
//            response.put("authToken", authToken);
//            return ServiceResponse.Success(response);
            
            User user = new User();
            user.setUserId("User-Tushar");
            user.setFirstName(registrationPayload.getFirstName());
            user.setLastName(registrationPayload.getLastName());
            user.setEmail(registrationPayload.getEmail());
            user.setPassword(registrationPayload.getPassword());

            userService.createUser(user);

            HashMap<String, Object> result = new HashMap<>();
            result.put("user", user);
            return ServiceResponse.Success(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ServiceResponse.Failed(e.getMessage());
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
            e.printStackTrace();
            return ServiceResponse.Failed(e.getMessage());
        }
    }
}
