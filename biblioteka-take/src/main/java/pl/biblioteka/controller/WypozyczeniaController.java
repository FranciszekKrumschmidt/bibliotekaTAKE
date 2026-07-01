package pl.biblioteka.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.biblioteka.dto.WypozyczenieRequestDto;
import pl.biblioteka.dto.ZwrotRequestDto;
import pl.biblioteka.model.Wypozyczenia;
import pl.biblioteka.service.WypozyczeniaService;

import java.util.List;

@RestController
@RequestMapping("/api/wypozyczenia")
public class WypozyczeniaController {
    private final WypozyczeniaService wypozyczeniaService;

    public WypozyczeniaController(WypozyczeniaService wypozyczeniaService) {
        this.wypozyczeniaService = wypozyczeniaService;
    }

    @GetMapping
    public ResponseEntity<List<Wypozyczenia>> pobierzWypozyczenia() {
        return ResponseEntity.ok(wypozyczeniaService.pobierzWszystkieWypozyczenia());
    }

    @PostMapping("/wypozycz")
    public ResponseEntity<Wypozyczenia> wypozycz(@Valid @RequestBody WypozyczenieRequestDto dto) {
        Wypozyczenia wypozyczenie = wypozyczeniaService.wypozyczWolumen(dto.getIndeksCzytelnicy(), dto.getIndeksWolumen());
        return new ResponseEntity<>(wypozyczenie, HttpStatus.CREATED);
    }

    @PostMapping("/zwroc")
    public ResponseEntity<Wypozyczenia> zwroc(@Valid @RequestBody ZwrotRequestDto dto) {
        Wypozyczenia zwrocone = wypozyczeniaService.zwrocWolumen(dto.getId(), null);
        return ResponseEntity.ok(zwrocone);
    }
}
