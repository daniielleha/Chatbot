package Models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class QueenModels implements Parcelable {

    @SerializedName("result")
    private int result;
    @SerializedName("id")
    private String id;
    @SerializedName("response")
    private String response;
    @SerializedName("msg")
    private String msg;

    protected QueenModels(Parcel in) {
        result = in.readInt();
        id = in.readString();
        response = in.readString();
        msg = in.readString();
    }

    public QueenModels() {
    }

    public QueenModels(int result, String id, String response, String msg) {
        this.result = result;
        this.id = id;
        this.response = response;
        this.msg = msg;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(result);
        dest.writeString(id);
        dest.writeString(response);
        dest.writeString(msg);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<QueenModels> CREATOR = new Creator<QueenModels>() {
        @Override
        public QueenModels createFromParcel(Parcel in) {
            return new QueenModels(in);
        }

        @Override
        public QueenModels[] newArray(int size) {
            return new QueenModels[size];
        }
    };

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static Creator<QueenModels> getCREATOR() {
        return CREATOR;
    }
}
