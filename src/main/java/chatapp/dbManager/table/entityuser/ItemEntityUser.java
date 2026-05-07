package chatapp.dbManager.table.entityuser;

import chatapp.dbManager.table.AuditFields;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "EntityUser")
public class ItemEntityUser extends AuditFields {

    @Id
    @Field("compositeKey")
    private String compositeKey;
    private String entityId;
    private String userId;
    private String entityType;

    public ItemEntityUser(String entityId, String userId, String createdBy){
        super(createdBy,System.currentTimeMillis());
        this.entityId = entityId;
        this.userId = userId;
        this.compositeKey = entityId+"-"+userId;
    }

    public String getCompositeKey() {
        return compositeKey;
    }

    public void setCompositeKey(String compositeKey) {
        this.compositeKey = compositeKey;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }
}
