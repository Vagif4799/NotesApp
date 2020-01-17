package application.controller;

import application.entity.Note;
import application.service.NoteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }


    @GetMapping
    public Iterable<Note> handle_get_all() {
        return noteService.get_all();
    }


    @GetMapping("/{id}")
    public Optional<Note> handle_get(@PathVariable("id") Long id) {
        return noteService.get_one(id);
    }


}
