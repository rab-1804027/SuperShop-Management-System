package Model;

import java.time.LocalDateTime;

public class User {
    private int id;
    private String name;
    private String email;
    private String username;
    private String password;
    private String role;
    private LocalDateTime registrationTime;
    private ApprovalStatus approvalStatus;

    private User(){}
    private static final User singleObject = new User();

    public User getSingleObject() {
        return singleObject;
    }

    public User(String name, String email, String username, String password) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = "null";
        this.registrationTime = LocalDateTime.now();
        this.approvalStatus = ApprovalStatus.PENDING;
    }

    public User(int id, String name, String email, String username, String password, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public enum ApprovalStatus {
        PENDING, APPROVED, REJECTED;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setRegistrationTime(LocalDateTime registrationTime) {
        this.registrationTime = registrationTime;
    }

    public LocalDateTime getRegistrationTime() {
        return registrationTime;
    }

    public void setApprovalStatus(ApprovalStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public ApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

}
