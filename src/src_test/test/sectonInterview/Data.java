package test.sectonInterview;

import java.util.Objects;

public class Data {

    private String title;
    private String body;
    private Integer userId;
    private Integer id;

    @Override
    public int hashCode() {
        return Objects.hash(title, body, userId, id);
    }

    public Data() {
    }

    public Data(String title, String body, Integer userId, Integer id) {
        this.title = title;
        this.body = body;
        this.userId = userId;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return Objects.equals(title, data.title) && Objects.equals(body, data.body) && Objects.equals(userId, data.userId) && Objects.equals(id, data.id);
    }
}
