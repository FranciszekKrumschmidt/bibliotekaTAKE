package pl.biblioteka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.biblioteka.service.WypozyczeniaService;

@Controller
@RequestMapping("/wypozyczenia")
public class WypozyczeniaController {
    private final WypozyczeniaService wypozyczeniaService;

    public WypozyczeniaController(WypozyczeniaService wypozyczeniaService) {
        this.wypozyczeniaService = wypozyczeniaService;
    }

    @GetMapping
    public String pokazWypozyczenia(Model model) {
        model.addAttribute("wypozyczenia", wypozyczeniaService.pobierzWszystkieWypozyczenia());
        return "wypozyczenia";
    }

    @PostMapping("/wypozycz")
    public String wypozycz(@RequestParam Long indeksCzytelnicy, @RequestParam Long indeksWolumen) {
        wypozyczeniaService.wypozyczWolumen(indeksCzytelnicy, indeksWolumen);
        return "redirect:/wypozyczenia";
    }

    @PostMapping("/zwroc")
    public String zwroc(@RequestParam Long id) {
        wypozyczeniaService.zwrocWolumen(id, null);
        return "redirect:/wypozyczenia";
    }
}
