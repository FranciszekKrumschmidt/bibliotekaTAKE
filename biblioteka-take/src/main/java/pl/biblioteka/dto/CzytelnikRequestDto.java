package pl.biblioteka.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CzytelnikRequestDto {
    @NotBlank(message = "Nazwa nie może być pusta")
    @Size(min = 2, max = 100, message = "Nazwa musi mieć od 2 do 100 znaków")
    private String nazwa;

    @NotBlank(message = "Email nie może być pusty")
    @Email(message = "Niepoprawny format adresu email")
    private String email;

    public String getNazwa() { return nazwa; }
    public void setNazwa(String nazwa) { this.nazwa = nazwa; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
