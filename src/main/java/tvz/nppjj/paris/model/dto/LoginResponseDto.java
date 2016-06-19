package tvz.nppjj.paris.model.dto;

public class LoginResponseDto {

    private UserDto userDto;
    private String  token;

    // getters and setters
    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
