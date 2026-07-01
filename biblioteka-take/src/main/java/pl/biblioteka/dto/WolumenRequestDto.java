package pl.biblioteka.dto;

import jakarta.validation.constraints.NotNull;

public class WolumenRequestDto {
    @NotNull(message = "ID tytułu jest wymagane")
    private Long tytulId;

    public Long getTytulId() { return tytulId; }
    public void setTytulId(Long tytulId) { this.tytulId = tytulId; }
}
