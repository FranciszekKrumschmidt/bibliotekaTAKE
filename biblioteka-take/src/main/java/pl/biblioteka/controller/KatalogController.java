package pl.biblioteka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.biblioteka.service.KatalogService;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/katalog")
public class KatalogController {
    
    private final KatalogService katalogService;

    public KatalogController(KatalogService katalogService) {
        this.katalogService = katalogService;
    }

    @GetMapping
    public String pokazKatalog(Model model) {
        model.addAttribute("kategorie", katalogService.pobierzWszystkieKategorie());
        model.addAttribute("tytuly", katalogService.pobierzWszystkieTytuly());
        model.addAttribute("wolumeny", katalogService.pobierzWszystkieWolumeny());
        return "katalog";
    }

    @PostMapping("/kategorie")
    public String dodajKategorie(@RequestParam String nazwa) {
        katalogService.dodajKategorie(nazwa);
        return "redirect:/katalog";
    }

    @PostMapping("/tytuly")
    public String dodajTytul(@RequestParam String tytul, @RequestParam String autor, 
                             @RequestParam String dataWydania, @RequestParam Long kategoriaId) {
        LocalDateTime date = LocalDate.parse(dataWydania).atStartOfDay();
        katalogService.dodajTytul(tytul, autor, date, kategoriaId);
        return "redirect:/katalog";
    }

    @PostMapping("/wolumeny")
    public String dodajWolumen(@RequestParam Long tytulId) {
        katalogService.dodajWolumen(tytulId);
        return "redirect:/katalog";
    }
}
