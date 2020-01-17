package application.controller;

import application.entity.LoginRq;
import application.entity.Note;
import application.entity.User;
import application.service.NoteService;
import application.service.SecurityService;
import application.service.UserService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Data
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final NoteService noteService;
    private final SecurityService securityService;



    @GetMapping
    public Iterable<User> handle_get_all() {
        return userService.get_all();
    }

    @PostMapping
    public void handle_post(@RequestBody User user) {
        userService.create_one(user);
    }

    @GetMapping("/{id}")
    public Optional<User> handle_get(@PathVariable("id") Long id) {
        return userService.get_one(id);
    }

    @DeleteMapping("/{id}")
    public void handle_delete_one(@PathVariable("id") Long id) {
        userService.del_one(id);
    }

    @PutMapping("/{id}")
    public User handle_put(@PathVariable("id") Long id, @RequestBody User user) {
        return userService.update_user(user, id);
    }


    @PostMapping("/{id}/notes")
    public void add_note(@PathVariable("id") Long user_id, @RequestBody Note note) {
        userService.get_one(user_id).ifPresent(u->noteService.create_one(u, note));
    }


    @DeleteMapping("/{id}/notes")
    public void deletePost(@PathVariable("id") Long id, @RequestBody Note note) {
        noteService.del_one(id);
    }


    @PostMapping("/login")
    public boolean handle_login(@RequestParam("email") String email, @RequestParam("password") String password) {
       boolean loginResponse = securityService.login(email, password);
           return loginResponse;
    }


}
