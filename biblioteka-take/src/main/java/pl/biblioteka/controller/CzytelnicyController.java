package pl.biblioteka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.biblioteka.service.CzytelnicyService;

@Controller
@RequestMapping("/czytelnicy")
public class CzytelnicyController {
    
    private final CzytelnicyService czytelnicyService;

    public CzytelnicyController(CzytelnicyService czytelnicyService) {
        this.czytelnicyService = czytelnicyService;
    }

    @GetMapping
    public String pobierzWszystkich(Model model) {
        model.addAttribute("czytelnicy", czytelnicyService.pobierzWszystkichCzytelnikow());
        return "czytelnicy";
    }

    @PostMapping
    public String dodajCzytelnika(@RequestParam String nazwa, @RequestParam String email) {
        czytelnicyService.rejestrujCzytelnika(nazwa, email);
        return "redirect:/czytelnicy";
    }
}
