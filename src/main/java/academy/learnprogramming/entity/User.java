package academy.learnprogramming.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "TodoUser")
@NamedQuery(name = User.FIND_ALL_USERS, query = "SELECT u FROM User u order by u.fullName")
@NamedQuery(name = User.FIND_USER_BY_EMAIL, query = "SELECT u FROM User u where u.email = :email")
@NamedQuery(name = User.FIND_USER_BY_PASSWORD, query = "SELECT u FROM User u where u.password= :password")
public class User extends AbstractEntity{

    public static final String FIND_USER_BY_EMAIL = "User.findByEmail";
    public static final String FIND_ALL_USERS = "User.findAllUsers";
    public static final String FIND_USER_BY_PASSWORD = "User.findByPassword";

    @NotNull(message="Full name must be set")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Full name should only contain alphabets")
    private String fullName;

    @NotNull(message="Email must be set")
    @Email(message = "Email must be in the form user@domain.com")
    private String email;
    @NotNull(message="Password must be set")
    @Size(min = 8, message = "Password should not be less than 8 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password must be strong")
    private String password;


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
