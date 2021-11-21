package test.omprakash.section24PojoSerialize;

public class UserProperties {

    //1
    private String username;
    private String password;
    private boolean is_fb;

    //3
    public UserProperties(String username, String password, boolean is_fb){
        this.username=username;
        this.password=password;
        this.is_fb=is_fb;
    }

    public UserProperties() {

    }

    //2
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIs_fb() {
        return is_fb;
    }

    public void setIs_fb(boolean is_fb) {
        this.is_fb = is_fb;
    }
}
