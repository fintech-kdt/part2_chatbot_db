package chatbot2.model;

import com.google.gson.annotations.SerializedName;

public class Message {
    @SerializedName("message_id")
    private long messageId;
    
    @SerializedName("text")
    private String text;
    
    @SerializedName("chat")
    private Chat chat;

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }
}