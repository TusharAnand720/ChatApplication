package chatapp.dbManager.table;

public class AuditFields {

    private long createdAt;
    private String createdBy;
    private long updatedAt;
    private String updatedBy ;
    private boolean isActive = true;

    public AuditFields(){

    }

    public AuditFields(String createdBy, long createdAt){
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    public AuditFields(long createdAt, String createdBy, long updatedAt, String updatedBy, boolean isActive, String entityStatus) {
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.isActive = isActive;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
