package fr.simplon.pixelshielrestapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserForm {

    @NotBlank
    @Size(min=1, max=255)
    private String login;

    @NotBlank
    @Size(min=1, max=255)
    private String firstName;

    @NotBlank
    @Size(min=1, max=255)
    private String lastName;

    @NotBlank
    @Size(min=1, max=255)
    private String address;

    @NotBlank
    @Size(min=1, max=255)
    private String phone;

    @NotBlank
    @Size(min=1, max=255)
    private String password;

    @NotBlank
    @Size(min=1, max=255)
    private String confirmPassword;

    private  String formula;

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}