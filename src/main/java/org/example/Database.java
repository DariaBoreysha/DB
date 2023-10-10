package org.example;

public class Database {
    private String username;
    private String password;
    private String url;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public String getUrl() {
        return url;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Application{" +
                "url='" + getUrl() + '\n' +
                "username='" + getUsername() + '\n' +
                ", password='" + getPassword() + '\n' +
                '}';
    }
}
