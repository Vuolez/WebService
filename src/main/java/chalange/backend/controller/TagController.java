package chalange.backend.controller;

import chalange.backend.dto.PostDto;
import chalange.backend.dto.TagDto;
import chalange.backend.dto.UserDto;
import chalange.backend.entity.Post;
import chalange.backend.entity.Tag;
import chalange.backend.entity.User;
import chalange.backend.service.TagService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RequestMapping("/api/tags")
@RestController
@RequiredArgsConstructor
public class TagController {
    private final ModelMapper modelMapper;
    private final TagService tagService;

    @GetMapping
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<List<TagDto>> findAll(){
        return new ResponseEntity<>(tagService.findAll(), HttpStatus.OK);
    }
}
