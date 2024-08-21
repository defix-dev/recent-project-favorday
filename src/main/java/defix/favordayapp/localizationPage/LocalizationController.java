package defix.favordayapp.localizationPage;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/localization")
public class LocalizationController {
    @PostMapping("/configuration/set_language")
    public ResponseEntity<Void> setLanguage(@RequestBody Map<String, String> language, HttpSession session) {
        session.setAttribute("lang", language.get("lang"));
        return ResponseEntity.ok().build();
    }
}
