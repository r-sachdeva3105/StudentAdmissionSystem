package com.example.studentSystem;

public class UserData {
    private int id;
    private String email;
    private String name;

    private String userType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}

//@FunctionalInterface
//public interface RunnableWithData<T> {
//    void run(T data);
//}