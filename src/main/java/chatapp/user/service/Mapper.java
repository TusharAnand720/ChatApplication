package chatapp.user.service;

import chatapp.dbManager.table.user.ItemUser;
import chatapp.user.entity.UserProfileResponse;
import org.springframework.beans.BeanUtils;

public class Mapper {

    public static UserProfileResponse mapUserProfile(ItemUser itemUser){
        UserProfileResponse userProfileResponse = new UserProfileResponse();
        BeanUtils.copyProperties(itemUser,userProfileResponse);
        return userProfileResponse;
    }
}
