package Models;

public class ChatModel {
    private String message;
    private boolean isSend;

    public ChatModel(String message, boolean isSend){
        this.message = message;
        this.isSend = isSend;
    }

    public ChatModel(){
    }
    public String getMessage(){
        return message;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public boolean isSend(){
        return isSend;
    }
    public void setSend(boolean send){
        this.isSend =isSend;
    }
}
