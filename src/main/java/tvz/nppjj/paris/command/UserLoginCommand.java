package tvz.nppjj.paris.command;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class UserLoginCommand {

    @NotBlank
    @Size(min = 3, max = 50, message = "must be at least 3 characters")
    private String username;

    @NotBlank
    @Size(min = 6, max = 50, message = "must be at least 6 characters")
    private String password;

    public UserLoginCommand() {
        super();
    }

    public UserLoginCommand(String username, String password) {
        this.username = username;
        this.password = password;
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

}
