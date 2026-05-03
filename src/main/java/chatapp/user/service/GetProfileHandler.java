package chatapp.user.service;

import chatapp.dbManager.table.user.ItemUser;
import chatapp.dbManager.table.user.TableUser;
import chatapp.middleware.APIRequest;
import chatapp.middleware.ServiceResponse;
import chatapp.user.entity.UserProfileResponse;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public class GetProfileHandler implements APIRequest {
    private final TableUser tableUser;
    private final String userId;

    public GetProfileHandler(String userId, TableUser tableUser) {
        this.userId = userId;
        this.tableUser = tableUser;
    }

    @Override
    public ResponseEntity<?> doProcess() {
        try{

            ItemUser itemUser = tableUser.getUser(userId);
            UserProfileResponse userProfileResponse = Mapper.mapUserProfile(itemUser);
            HashMap<String,Object> result = new HashMap<>();
            result.put("user", userProfileResponse);
            return ServiceResponse.Success(result);

        }catch (Exception e){
            return ServiceResponse.BadRequest(e.getMessage());
        }
    }
}
