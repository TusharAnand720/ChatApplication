package chatapp.dbManager.table.message;

import chatapp.dbManager.table.AuditFields;
import chatapp.utility.helper.ServiceHelper;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Message")
public class ItemMessage extends AuditFields {

    @Id
    @Field("messageId")
    private String messageId;
    private String roomId;
    private String userId;
    private String content;

    public ItemMessage(String roomId , String userId , String createdAt){
        super(createdAt,System.currentTimeMillis());
        this.roomId = roomId;
        this.userId = userId;
        this.messageId = ServiceHelper.createId("MSG");
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
