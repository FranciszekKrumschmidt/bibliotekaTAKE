package pl.biblioteka.dto;

import jakarta.validation.constraints.NotNull;

public class ZwrotRequestDto {
    @NotNull(message = "ID wypożyczenia jest wymagane")
    private Long id;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
}
