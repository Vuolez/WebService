package chalange.backend.controller;

import chalange.backend.dto.UserDto;
import chalange.backend.entity.User;
import chalange.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @GetMapping
    public String showRegistrationForm(WebRequest request, Model model) {
        model.addAttribute("user", new UserDto());
        return "user_registration";
    }

    @PostMapping
    public RedirectView  addNewUser(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
        userService.save(user);

        return new RedirectView("showRegistrationForm");
    }
}
