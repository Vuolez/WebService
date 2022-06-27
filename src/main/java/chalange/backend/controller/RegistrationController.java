package chalange.backend.controller;

import chalange.backend.dto.UserDto;
import chalange.backend.entity.User;
import chalange.backend.model.RoleType;
import chalange.backend.service.RoleService;
import chalange.backend.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserServiceImpl userServiceImpl;
    private final RoleService roleService;

    @GetMapping
    public String showRegistrationForm(WebRequest request, Model model) {
        model.addAttribute("user", new UserDto());
        return "user_registration";
    }

    @PostMapping
    public RedirectView  addNewUser(@ModelAttribute UserDto userDto, Model model) { // todo: use UserDto
        userDto.setRoles(List.of(roleService.findRoleByRoleType(RoleType.USER)));
        userServiceImpl.save(userDto);


        return new RedirectView("/registration");
    }
}
