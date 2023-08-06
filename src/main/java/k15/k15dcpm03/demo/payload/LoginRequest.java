package k15.k15dcpm03.demo.payload;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
	
	@NotBlank
    private String username;

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

	@NotBlank
    private String password;

}
