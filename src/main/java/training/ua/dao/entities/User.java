package training.ua.dao.entities;

import java.sql.Timestamp;

public class User {

    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Boolean isBlocked;
    private Timestamp dateOfBlocking;
    private Long roleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    public Timestamp getDateOfBlocking() {
        return dateOfBlocking;
    }

    public void setDateOfBlocking(Timestamp dateOfBlocking) {
        this.dateOfBlocking = dateOfBlocking;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id != null ? id.equals(user.id) : user.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User [" +
                "id = " + id +
                ", roleId = " + roleId +
                ", email = " + email +
                ", password = " + password +
                ", firstName = " + firstName +
                ", lastName = " + lastName +
                ", isBlocked = " + isBlocked +
                ", dateOfBlocking = " + dateOfBlocking +
                ']';
    }
}
