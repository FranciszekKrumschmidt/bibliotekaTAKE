package pl.biblioteka.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.biblioteka.dto.CzytelnikRequestDto;
import pl.biblioteka.model.Czytelnicy;
import pl.biblioteka.service.CzytelnicyService;

import java.util.List;

@RestController
@RequestMapping("/api/czytelnicy")
public class CzytelnicyController {
    
    private final CzytelnicyService czytelnicyService;

    public CzytelnicyController(CzytelnicyService czytelnicyService) {
        this.czytelnicyService = czytelnicyService;
    }

    @GetMapping
    public ResponseEntity<List<Czytelnicy>> pobierzWszystkich() {
        return ResponseEntity.ok(czytelnicyService.pobierzWszystkichCzytelnikow());
    }

    @PostMapping
    public ResponseEntity<Czytelnicy> dodajCzytelnika(@Valid @RequestBody CzytelnikRequestDto dto) {
        Czytelnicy nowyCzytelnik = czytelnicyService.rejestrujCzytelnika(dto.getNazwa(), dto.getEmail());
        return new ResponseEntity<>(nowyCzytelnik, HttpStatus.CREATED);
    }
}
