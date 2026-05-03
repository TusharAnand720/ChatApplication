package chatapp.user.service;

import authorization.lib.model.AuthToken;
import authorization.lib.service.AuthService;
import chatapp.dbManager.table.user.ItemUser;
import chatapp.dbManager.table.user.TableUser;
import chatapp.middleware.APIRequest;
import chatapp.middleware.ServiceResponse;
import chatapp.user.entity.RegistrationPayload;
import chatapp.user.entity.UserProfileResponse;
import chatapp.validation.BaseValidator;
import chatapp.validation.ServiceError;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public class RegistrationHandler implements APIRequest {

    private final AuthService authService;
    private final RegistrationPayload registrationPayload;
    private final TableUser tableUser;

    public RegistrationHandler(AuthService authService, TableUser tableUser,RegistrationPayload registrationPayload) {
        this.authService = authService;
        this.registrationPayload = registrationPayload;
        this.tableUser = tableUser;
    }

    @Override
    public ResponseEntity<?> doProcess() {
        try {

            validateRequest(registrationPayload);

            userExists(registrationPayload.getEmail());

            ItemUser itemUser = tableUser.createItem("ED");
            itemUser.setEmail(registrationPayload.getEmail());
            itemUser.setPassword(registrationPayload.getPassword());
            itemUser.setFirstName(registrationPayload.getFirstName());
            itemUser.setLastName(registrationPayload.getLastName());
            tableUser.saveItem(itemUser, "ED");

            UserProfileResponse userProfileResponse = Mapper.mapUserProfile(itemUser);

            AuthToken authToken = authService.issueToken(itemUser.getUserId());

            HashMap<String, Object> result = new HashMap<>();
            result.put("user", userProfileResponse);
            result.put("authToken", authToken);

            return ServiceResponse.Success(result);
        }catch (Exception e){
            return ServiceResponse.BadRequest(e.getMessage()) ;
        }
    }

    private void userExists(String email){
        ItemUser user = tableUser.getUserByEmail(email);
        BaseValidator.throwExceptionIfTrue(user!=null, ServiceError.ServiceError_user_already_exists_with_email.getMessage());
    }
    private void validateRequest(RegistrationPayload registrationPayload)throws Exception{
        BaseValidator.throwExceptionIfNotAvailable(registrationPayload.getEmail(), ServiceError.ServiceError_invalid_email.getMessage());
        BaseValidator.throwExceptionIfNotAvailable(registrationPayload.getPassword(), ServiceError.ServiceError_invalid_password.getMessage());
        BaseValidator.throwExceptionIfNotAvailable(registrationPayload.getFirstName(), ServiceError.ServiceError_invalid_firstName.getMessage());

    }
}
