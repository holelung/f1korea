package kr.co.project.f1korea.dto;

import kr.co.project.f1korea.domain.User;

public class UserRequest {
    private String name;
    private String email;
    private String password;

    public User toEntity(){
        User user= new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
