package pl.biblioteka.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.biblioteka.dto.KategoriaRequestDto;
import pl.biblioteka.dto.TytulRequestDto;
import pl.biblioteka.dto.WolumenRequestDto;
import pl.biblioteka.model.Kategoria;
import pl.biblioteka.model.Tytul;
import pl.biblioteka.model.Wolumen;
import pl.biblioteka.service.KatalogService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/katalog")
public class KatalogController {
    
    private final KatalogService katalogService;

    public KatalogController(KatalogService katalogService) {
        this.katalogService = katalogService;
    }

    @GetMapping("/kategorie")
    public ResponseEntity<List<Kategoria>> pobierzKategorie() {
        return ResponseEntity.ok(katalogService.pobierzWszystkieKategorie());
    }

    @GetMapping("/tytuly")
    public ResponseEntity<List<Tytul>> pobierzTytuly() {
        return ResponseEntity.ok(katalogService.pobierzWszystkieTytuly());
    }

    @GetMapping("/wolumeny")
    public ResponseEntity<List<Wolumen>> pobierzWolumeny() {
        return ResponseEntity.ok(katalogService.pobierzWszystkieWolumeny());
    }

    @PostMapping("/kategorie")
    public ResponseEntity<Kategoria> dodajKategorie(@Valid @RequestBody KategoriaRequestDto dto) {
        Kategoria kategoria = katalogService.dodajKategorie(dto.getNazwa());
        return new ResponseEntity<>(kategoria, HttpStatus.CREATED);
    }

    @PostMapping("/tytuly")
    public ResponseEntity<Tytul> dodajTytul(@Valid @RequestBody TytulRequestDto dto) {
        LocalDateTime date = LocalDate.parse(dto.getDataWydania()).atStartOfDay();
        Tytul tytul = katalogService.dodajTytul(dto.getTytul(), dto.getAutor(), date, dto.getKategoriaId());
        return new ResponseEntity<>(tytul, HttpStatus.CREATED);
    }

    @PostMapping("/wolumeny")
    public ResponseEntity<Wolumen> dodajWolumen(@Valid @RequestBody WolumenRequestDto dto) {
        Wolumen wolumen = katalogService.dodajWolumen(dto.getTytulId());
        return new ResponseEntity<>(wolumen, HttpStatus.CREATED);
    }
}
