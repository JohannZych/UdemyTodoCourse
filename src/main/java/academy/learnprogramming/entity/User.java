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
public class User extends AbstractEntity{

    @NotNull(message = "Full name must be set")
//    @Pattern(regexp = "", message = "Full name must be in alphabets")
    private String fullName; //123456

    @NotNull(message = "Email must be set")
    @Email(message = "Email must be in the form user@domain.com")
    private String email;


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
