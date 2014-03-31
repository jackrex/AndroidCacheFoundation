package info.jackrex.androidcachefoundation.app.entity;

import java.util.List;

/**
 * Created by Jackrex on 3/13/14.
 */
public class NewsContent {

    private boolean has_next;
    private int total_count;
    private int next_page;
    private List<News> list;

    public boolean isHas_next() {
        return has_next;
    }

    public void setHas_next(boolean has_next) {
        this.has_next = has_next;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public int getNext_page() {
        return next_page;
    }

    public void setNext_page(int next_page) {
        this.next_page = next_page;
    }

    public List<News> getList() {
        return list;
    }

    public void setList(List<News> list) {
        this.list = list;
    }
}
