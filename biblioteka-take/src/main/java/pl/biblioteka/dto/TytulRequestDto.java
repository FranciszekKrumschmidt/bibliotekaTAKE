package pl.biblioteka.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class TytulRequestDto {
    @NotBlank(message = "Tytuł nie może być pusty")
    private String tytul;

    @NotBlank(message = "Autor nie może być pusty")
    private String autor;

    @NotBlank(message = "Data wydania nie może być pusta")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Oczekiwano formatu YYYY-MM-DD")
    private String dataWydania;

    @NotNull(message = "ID kategorii jest wymagane")
    private Long kategoriaId;

    public String getTytul() { return tytul; }
    public void setTytul(String tytul) { this.tytul = tytul; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getDataWydania() { return dataWydania; }
    public void setDataWydania(String dataWydania) { this.dataWydania = dataWydania; }

    public Long getKategoriaId() { return kategoriaId; }
    public void setKategoriaId(Long kategoriaId) { this.kategoriaId = kategoriaId; }
}
