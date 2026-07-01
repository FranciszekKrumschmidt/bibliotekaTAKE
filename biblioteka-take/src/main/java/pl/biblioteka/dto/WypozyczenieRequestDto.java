package pl.biblioteka.dto;

import jakarta.validation.constraints.NotNull;

public class WypozyczenieRequestDto {
    @NotNull(message = "Indeks czytelnika jest wymagany")
    private Long indeksCzytelnicy;

    @NotNull(message = "Indeks wolumenu jest wymagany")
    private Long indeksWolumen;

    public Long getIndeksCzytelnicy() { return indeksCzytelnicy; }
    public void setIndeksCzytelnicy(Long indeksCzytelnicy) { this.indeksCzytelnicy = indeksCzytelnicy; }

    public Long getIndeksWolumen() { return indeksWolumen; }
    public void setIndeksWolumen(Long indeksWolumen) { this.indeksWolumen = indeksWolumen; }
}
