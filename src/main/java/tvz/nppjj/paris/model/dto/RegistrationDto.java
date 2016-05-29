package tvz.nppjj.paris.model.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class RegistrationDto {

    @NotBlank
    @Size(min = 6, max = 50, message = "must be at least 6 characters")
    private String email;

    @NotBlank
    @Size(min = 6, max = 100, message = "Password must be 6 - 100 characters")
    private String password;

    @NotBlank(message = "Hey! We need your phone number!")
    private String phoneNumber;

    // getters and setters
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
