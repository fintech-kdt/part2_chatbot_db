package chatbot2.model;

import com.google.gson.annotations.SerializedName;

public class Update {
    @SerializedName("update_id")
    private long updateId;
    
    @SerializedName("message")
    private Message message;

    public long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(long updateId) {
        this.updateId = updateId;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}