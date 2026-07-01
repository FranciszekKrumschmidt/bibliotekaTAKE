package pl.biblioteka.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class KategoriaRequestDto {
    @NotBlank(message = "Nazwa kategorii nie może być pusta")
    @Size(min = 2, max = 50, message = "Nazwa kategorii musi mieć od 2 do 50 znaków")
    private String nazwa;

    public String getNazwa() { return nazwa; }
    public void setNazwa(String nazwa) { this.nazwa = nazwa; }
}
