package com.springjpa.resource;

import com.springjpa.domain.Request;
import com.springjpa.domain.Users;
import com.springjpa.domain.dto.UserLoginDto;
import com.springjpa.model.PageModel;
import com.springjpa.model.PageRequestModel;
import com.springjpa.service.RequestService;
import com.springjpa.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/users")
public class UserResource {
    private final UserService userService;
    private final RequestService requestService;

    @PostMapping
    public ResponseEntity<Users> save(@RequestBody @Valid Users user){
        Users created = userService.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> update(@PathVariable(name = "id") Long id,  @RequestBody @Valid Users user){
        user.setId(id);
        Users updated = userService.update(user);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getById(@PathVariable(name = "id") Long id){
        Users user = userService.getById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<Users> login(@Valid @RequestBody UserLoginDto user){
        Users userLogged = userService.login(user.getEmail(), user.getPass());
        return ResponseEntity.ok(userLogged);
    }

    @GetMapping
    public ResponseEntity<PageModel<Users>> listAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                                    @RequestParam(value = "size", defaultValue = "10") int size){
        PageRequestModel pr = new PageRequestModel();
        pr.setPage(page);
        pr.setSize(size);

        PageModel<Users> pm = userService.listAllOnLazyLoading(pr);
        return ResponseEntity.ok(pm);
    }

    @GetMapping("/{id}/requests")
    public ResponseEntity<List<Request>> listAllByOwnerId(@PathVariable(name = "id")Long id){
        List<Request> requests = requestService.listAllByOwnerId(id);
        return ResponseEntity.ok(requests);
    }
}
