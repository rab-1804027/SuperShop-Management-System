package Dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserInfoDto {
    private String name;
    private String username;
    private String email;
    private String registrationTime;

    public UserInfoDto(String name, String username, String email, LocalDateTime registrationTime) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.registrationTime = formateTime(registrationTime);
    }

    private String formateTime(LocalDateTime time)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy HH:mm:ss");
        return time.format(formatter);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(LocalDateTime registrationTime) {
        this.registrationTime = formateTime(registrationTime);
    }

}
