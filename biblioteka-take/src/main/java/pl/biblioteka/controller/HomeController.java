package pl.biblioteka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.biblioteka.service.CzytelnicyService;
import pl.biblioteka.service.KatalogService;
import pl.biblioteka.service.WypozyczeniaService;

@Controller
public class HomeController {
    private final CzytelnicyService czytelnicyService;
    private final KatalogService katalogService;
    private final WypozyczeniaService wypozyczeniaService;

    public HomeController(CzytelnicyService czytelnicyService, KatalogService katalogService, WypozyczeniaService wypozyczeniaService) {
        this.czytelnicyService = czytelnicyService;
        this.katalogService = katalogService;
        this.wypozyczeniaService = wypozyczeniaService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/czytelnicy")
    public String czytelnicy(Model model) {
        model.addAttribute("czytelnicy", czytelnicyService.pobierzWszystkichCzytelnikow());
        return "czytelnicy";
    }

    @PostMapping("/czytelnicy")
    public String dodajCzytelnika(@RequestParam String nazwa, @RequestParam String email, RedirectAttributes redirectAttributes) {
        try {
            
            validateLength(nazwa, email);
            czytelnicyService.rejestrujCzytelnika(nazwa, email);
            redirectAttributes.addFlashAttribute("success", "Pomyślnie dodano czytelnika!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Błąd podczas rejestracji: " + e.getMessage());
        }
        return "redirect:/czytelnicy";
    }

    @GetMapping("/katalog")
    public String katalog(Model model) {
        model.addAttribute("kategorie", katalogService.pobierzWszystkieKategorie());
        model.addAttribute("tytuly", katalogService.pobierzWszystkieTytuly());
        model.addAttribute("wolumeny", katalogService.pobierzWszystkieWolumeny());
        return "katalog";
    }

    @PostMapping("/katalog/kategorie")
    public String dodajKategorie(@RequestParam String nazwa, RedirectAttributes redirectAttributes) {
        try {
            validateLength(nazwa);
            katalogService.dodajKategorie(nazwa);
            redirectAttributes.addFlashAttribute("success", "Pomyślnie dodano kategorię!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Błąd podczas dodawania kategorii: " + e.getMessage());
        }
        return "redirect:/katalog";
    }

    @PostMapping("/katalog/tytuly")
    public String dodajTytul(@RequestParam String tytul, @RequestParam String autor, @RequestParam String dataWydania, @RequestParam Long kategoriaId, RedirectAttributes redirectAttributes) {
        try {
            validateLength(tytul, autor);
            java.time.LocalDateTime date = java.time.LocalDate.parse(dataWydania).atStartOfDay();
            katalogService.dodajTytul(tytul, autor, date, kategoriaId);
            redirectAttributes.addFlashAttribute("success", "Pomyślnie dodano tytuł!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Błąd podczas dodawania tytułu: " + e.getMessage());
        }
        return "redirect:/katalog";
    }

    @PostMapping("/katalog/wolumeny")
    public String dodajWolumen(@RequestParam Long tytulId, RedirectAttributes redirectAttributes) {
        try {
            katalogService.dodajWolumen(tytulId);
            redirectAttributes.addFlashAttribute("success", "Pomyślnie dodano wolumen!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Błąd podczas dodawania wolumenu: " + e.getMessage());
        }
        return "redirect:/katalog";
    }

    @GetMapping("/wypozyczenia")
    public String wypozyczenia(Model model) {
        model.addAttribute("wypozyczenia", wypozyczeniaService.pobierzWszystkieWypozyczenia());
        model.addAttribute("czytelnicy", czytelnicyService.pobierzWszystkichCzytelnikow());
        model.addAttribute("wolumeny", katalogService.pobierzWszystkieWolumeny());
        return "wypozyczenia";
    }

    @PostMapping("/wypozyczenia/wypozycz")
    public String wypozycz(@RequestParam Long indeksCzytelnicy, @RequestParam Long indeksWolumen, RedirectAttributes redirectAttributes) {
        try {
            wypozyczeniaService.wypozyczWolumen(indeksCzytelnicy, indeksWolumen);
            redirectAttributes.addFlashAttribute("success", "Pomyślnie wypożyczono książkę!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Błąd podczas wypożyczania: " + e.getMessage());
        }
        return "redirect:/wypozyczenia";
    }

    @PostMapping("/wypozyczenia/zwroc")
    public String zwroc(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        try {
            wypozyczeniaService.zwrocWolumen(id, null);
            redirectAttributes.addFlashAttribute("success", "Pomyślnie zwrócono książkę!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Błąd podczas zwrotu: " + e.getMessage());
        }
        return "redirect:/wypozyczenia";
    }

    // --- Deletion Endpoints ---

    @PostMapping("/czytelnicy/usun")
    public String usunCzytelnika(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        try {
            czytelnicyService.usunCzytelnika(id);
            redirectAttributes.addFlashAttribute("success", "Pomyślnie usunięto czytelnika.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Nie można usunąć czytelnika (prawdopodobnie posiada historię wypożyczeń).");
        }
        return "redirect:/czytelnicy";
    }

    @PostMapping("/katalog/kategorie/usun")
    public String usunKategorie(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        try {
            katalogService.usunKategorie(id);
            redirectAttributes.addFlashAttribute("success", "Pomyślnie usunięto kategorię.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Nie można usunąć kategorii (prawdopodobnie przypisane są do niej tytuły).");
        }
        return "redirect:/katalog";
    }

    @PostMapping("/katalog/tytuly/usun")
    public String usunTytul(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        try {
            katalogService.usunTytul(id);
            redirectAttributes.addFlashAttribute("success", "Pomyślnie usunięto tytuł.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Nie można usunąć tytułu (prawdopodobnie posiada przypisane wolumeny).");
        }
        return "redirect:/katalog";
    }

    @PostMapping("/katalog/wolumeny/usun")
    public String usunWolumen(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        try {
            katalogService.usunWolumen(id);
            redirectAttributes.addFlashAttribute("success", "Pomyślnie usunięto wolumen.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Nie można usunąć wolumenu (prawdopodobnie jest wypożyczony lub ma historię).");
        }
        return "redirect:/katalog";
    }

    @PostMapping("/wypozyczenia/usun")
    public String usunWypozyczenie(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        try {
            wypozyczeniaService.usunWypozyczenie(id);
            redirectAttributes.addFlashAttribute("success", "Pomyślnie usunięto wpis wypożyczenia.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Błąd podczas usuwania wypożyczenia: " + e.getMessage());
        }
        return "redirect:/wypozyczenia";
    }

    // --- Editing Endpoints ---

    @GetMapping("/czytelnicy/edytuj")
    public String edytujCzytelnikaForm(@RequestParam Long id, Model model) {
        model.addAttribute("czytelnik", czytelnicyService.getCzytelnik(id));
        return "czytelnicy-edytuj";
    }

    @PostMapping("/czytelnicy/edytuj")
    public String edytujCzytelnika(@RequestParam Long id, @RequestParam String nazwa, @RequestParam String email, RedirectAttributes redirectAttributes) {
        try {
           
            validateLength(nazwa,email);
            czytelnicyService.edytujCzytelnika(id, nazwa, email);
            redirectAttributes.addFlashAttribute("success", "Pomyślnie zaktualizowano czytelnika.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Błąd podczas aktualizacji: " + e.getMessage());
        }
        return "redirect:/czytelnicy";
    }

    @GetMapping("/katalog/kategorie/edytuj")
    public String edytujKategorieForm(@RequestParam Long id, Model model) {
        model.addAttribute("kategoria", katalogService.getKategoria(id));
        return "katalog-kategoria-edytuj";
    }

    @PostMapping("/katalog/kategorie/edytuj")
    public String edytujKategorie(@RequestParam Long id, @RequestParam String nazwa, RedirectAttributes redirectAttributes) {
        try {
            validateLength(nazwa);
            katalogService.edytujKategorie(id, nazwa);
            redirectAttributes.addFlashAttribute("success", "Pomyślnie zaktualizowano kategorię.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Błąd podczas edycji kategorii: " + e.getMessage());
        }
        return "redirect:/katalog";
    }

    @GetMapping("/katalog/tytuly/edytuj")
    public String edytujTytulForm(@RequestParam Long id, Model model) {
        model.addAttribute("tytul", katalogService.getTytul(id));
        model.addAttribute("kategorie", katalogService.pobierzWszystkieKategorie());
        return "katalog-tytul-edytuj";
    }

    @PostMapping("/katalog/tytuly/edytuj")
    public String edytujTytul(@RequestParam Long id, @RequestParam String tytul, @RequestParam String autor, @RequestParam String dataWydania, @RequestParam Long kategoriaId, RedirectAttributes redirectAttributes) {
        try {
            validateLength(tytul, autor);
            java.time.LocalDateTime date = java.time.LocalDate.parse(dataWydania).atStartOfDay();
            katalogService.edytujTytul(id, tytul, autor, date, kategoriaId);
            redirectAttributes.addFlashAttribute("success", "Pomyślnie zaktualizowano tytuł.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Błąd podczas edycji tytułu: " + e.getMessage());
        }
        return "redirect:/katalog";
    }

    private void validateLength(String... values) {
        for (String val : values) {
            if (val != null && val.length() > 50) {
                throw new IllegalArgumentException("Wprowadzona wartość przekracza limit 50 znaków.");
            }
        }
    }
}

