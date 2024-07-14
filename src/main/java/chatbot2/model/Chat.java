package chatbot2.model;

import com.google.gson.annotations.SerializedName;

public class Chat {
    @SerializedName("id")
    private long id;
    
    @SerializedName("type")
    private String type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}