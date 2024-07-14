package chatbot2.model;

import com.google.gson.annotations.SerializedName;

public class ApiResponse<T> {
    @SerializedName("ok")
    private boolean ok;
    
    @SerializedName("result")
    private T result;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}