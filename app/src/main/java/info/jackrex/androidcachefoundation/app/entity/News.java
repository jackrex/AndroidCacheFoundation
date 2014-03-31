package info.jackrex.androidcachefoundation.app.entity;

import java.io.Serializable;

/**
 * Created by Jackrex on 3/13/14.
 */
public class News implements Serializable {

    private int id;
    private String title;
    private String publish_time;
    private String summary;
    private String image;
    private String press;
    private String read_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getRead_time() {
        return read_time;
    }

    public void setRead_time(String read_time) {
        this.read_time = read_time;
    }
}
