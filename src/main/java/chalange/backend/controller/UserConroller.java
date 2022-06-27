package chalange.backend.controller;

import chalange.backend.dto.UserDto;
import chalange.backend.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RequestMapping("/api/users")
@RestController
@RequiredArgsConstructor
public class UserConroller {
    private final UserServiceImpl userServiceImpl;

    @GetMapping
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<List<UserDto>> findAll() {
        return new ResponseEntity<>(userServiceImpl.findAll(), HttpStatus.OK);
    }

    @GetMapping("/current")
    @ResponseBody
    public ResponseEntity<String> currentUserName(Principal principal) {
        String content = "Welcome " + principal.getName();

        return new ResponseEntity<>(content, HttpStatus.OK);
    }
}
