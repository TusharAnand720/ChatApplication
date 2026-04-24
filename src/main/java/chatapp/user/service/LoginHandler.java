package chatapp.user.service;

import authorization.lib.model.AuthToken;
import authorization.lib.service.AuthService;
import chatapp.dbManager.table.user.ItemUser;
import chatapp.dbManager.table.user.TableUser;
import chatapp.middleware.APIRequest;
import chatapp.middleware.ServiceResponse;
import chatapp.user.entity.LoginPayload;
import chatapp.validation.BaseValidator;
import chatapp.validation.ServiceError;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public class LoginHandler implements APIRequest {

    private final LoginPayload loginPayload;
    private final TableUser tableUser;
    private final AuthService authService;

    public LoginHandler(LoginPayload loginPayload, TableUser tableUser,AuthService authService) {
        this.loginPayload = loginPayload;
        this.tableUser = tableUser;
        this.authService = authService;
    }

    @Override
    public ResponseEntity<?> doProcess() {
        try{

            validateRequest(loginPayload);
            ItemUser itemUser = userExists(loginPayload.getEmail());

            AuthToken authToken = authService.issueToken(itemUser.getUserId());

            HashMap<String,Object> result = new HashMap<>();
            result.put("authToken",authToken);
            result.put("user",itemUser);
            return ServiceResponse.Success(result);

        }catch (Exception e){
            return ServiceResponse.BadRequest(e.getMessage());
        }
    }

    private ItemUser userExists(String email) throws Exception{
        ItemUser user = tableUser.getUserByEmail(email);
        BaseValidator.throwExceptionIfTrue(user==null,ServiceError.Service_invalid_userName_or_password.getMessage());
        return user;
    }

    private void validateRequest(LoginPayload loginPayload){
        BaseValidator.throwExceptionIfNotAvailable(loginPayload.getEmail(), ServiceError.ServiceError_invalid_email.getMessage());
        BaseValidator.throwExceptionIfNotAvailable(loginPayload.getPassword(), ServiceError.ServiceError_invalid_password.getMessage());
    }
}
