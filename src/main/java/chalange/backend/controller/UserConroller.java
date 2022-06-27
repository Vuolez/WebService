package chalange.backend.controller;

import chalange.backend.dto.UserDto;
import chalange.backend.entity.User;
import chalange.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequestMapping("/api/users")
@RestController
@RequiredArgsConstructor
public class UserConroller {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<List<UserDto>> findAll(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/current")
    @ResponseBody
    public ResponseEntity<String> currentUserName(Principal principal) {
        String content = "Welcome " + principal.getName();

        return new ResponseEntity<>(content, HttpStatus.OK);
    }
}
