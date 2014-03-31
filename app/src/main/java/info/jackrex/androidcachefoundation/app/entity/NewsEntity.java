package info.jackrex.androidcachefoundation.app.entity;

import java.io.Serializable;

/**
 * Created by Jackrex on 3/13/14.
 */
public class NewsEntity implements Serializable {


    private NewsContent content;
    private String message;
    private int code;
    private boolean error;


    public NewsContent getContent() {
        return content;
    }

    public void setContent(NewsContent content) {
        this.content = content;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
