package alwiya.carRent.controller;

import alwiya.carRent.model.User;
import alwiya.carRent.service.UserService;
import alwiya.carRent.utils.SearchUserRequest;
import alwiya.carRent.utils.dto.Res;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> create (@RequestBody User request) {
        return Res.renderJson(
                userService.create(request),
                HttpStatus.CREATED,
                "Succesfully created a user"
        );
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(required = false) String name
    ) {
        SearchUserRequest request = new SearchUserRequest();
        request.setName(name);

        return Res.renderJson(
                userService.getAll(request),
                HttpStatus.OK,
                "Successfully get users"
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id) {
        return Res.renderJson(
                userService.getOne(id),
                HttpStatus.OK,
                "Successfully get a user with id " + id
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody User request) {
        return Res.renderJson(
                userService.update(id, request),
                HttpStatus.OK,
                "Successfully updated user"
        );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        userService.delete(id);
    }

}
