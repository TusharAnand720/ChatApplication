package chatapp.validation;

import chatapp.utility.helper.ServiceHelper;

public class BaseValidator {

    public static <T> void throwExceptionIfNotAvailable(String str , String message){
         if(!ServiceHelper.isAvailable(str)){
            throw  new ServiceException(message);
        }
    }

    public static <T> void throwExceptionIfTrue(boolean isTrue, String message) {
        if (isTrue) {
            throw new ServiceException(message);
        }
    }

}
