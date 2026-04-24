package chatapp.user.service;

import authorization.lib.model.AuthToken;
import authorization.lib.service.AuthService;
import chatapp.dbManager.table.user.ItemUser;
import chatapp.dbManager.table.user.TableUser;
import chatapp.middleware.APIRequest;
import chatapp.middleware.ServiceResponse;
import chatapp.user.entity.RegistrationPayload;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public class RegistrationHandler implements APIRequest {

    private AuthService authService;
    private RegistrationPayload registrationPayload;
    private TableUser tableUser;

    public RegistrationHandler(AuthService authService, TableUser tableUser,RegistrationPayload registrationPayload) {
        this.authService = authService;
        this.registrationPayload = registrationPayload;
        this.tableUser = tableUser;
    }

    @Override
    public ResponseEntity<?> doProcess() {

        try {
            ItemUser itemUser = tableUser.createItem("ED");
            itemUser.setEmail(registrationPayload.getEmail());
            itemUser.setPassword(registrationPayload.getPassword());
            itemUser.setFirstName(registrationPayload.getFirstName());
            itemUser.setLastName(registrationPayload.getLastName());
            tableUser.saveItem(itemUser, "ED");

            AuthToken authToken = authService.issueToken(itemUser.getUserId());

            HashMap<String, Object> result = new HashMap<>();
            result.put("user",itemUser);
            result.put("authToken" , authToken);

           return ServiceResponse.Success(result);

        }catch (Exception e){
            return ServiceResponse.BadRequest(e.getMessage()) ;
        }
    }
}
