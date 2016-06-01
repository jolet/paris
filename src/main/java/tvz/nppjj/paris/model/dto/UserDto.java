package tvz.nppjj.paris.model.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class UserDto {
	
		@NotBlank
	    @Size(min = 6, max = 50, message = "must be at least 6 characters")
		private String username;

		@NotBlank
	    @Size(min = 6, max = 50, message = "must be at least 6 characters")
	    private String email;

	   

	    @NotBlank(message = "Hey! We need your phone number!")
	    private String phoneNumber;
	
	    private String role;
	   
	    
		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public Object getEmail() {
		return email;
		}

		public void setUsername(String username) {
		this.username=username;
		}
		
		
		public String getUsername() {
			return username;
		}

		public void setEmail(String email) {
		this.email=email;
		}

}
