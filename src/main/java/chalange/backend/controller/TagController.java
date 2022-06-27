package chalange.backend.controller;

import chalange.backend.dto.TagDto;
import chalange.backend.service.TagServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/tags")
@RestController
@RequiredArgsConstructor
public class TagController {
    private final TagServiceImpl tagServiceImpl;

    @GetMapping
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<List<TagDto>> findAll() {
        return new ResponseEntity<>(tagServiceImpl.findAll(), HttpStatus.OK);
    }
}
