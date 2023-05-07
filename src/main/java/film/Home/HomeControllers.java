package film.Home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller

public class HomeControllers {
        @GetMapping("/Login")
        public String hello() {
            return "Home/Login";
        }
}
