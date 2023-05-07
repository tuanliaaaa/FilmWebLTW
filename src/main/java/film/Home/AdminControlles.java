package film.Home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/Admin")
public class AdminControlles {
    @GetMapping("/Login")
    public String AdminLogin() {
        return "Admin/AdminLogin";
    }
}
