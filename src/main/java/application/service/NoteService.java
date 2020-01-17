package application.service;

import application.entity.Note;
import application.entity.User;
import application.repos.NoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRpository) {
        this.noteRepository = noteRpository;
    }


    public Optional<Note> get_one(Long id) {
        return noteRepository.findById(id);
    }

    public Iterable<Note> get_all() {
        return noteRepository.findAll();
    }


    public Note create_one(User user, Note note) {
        note.setUser(user);
        noteRepository.save(note);
        return note;
    }

    public void del_one(Long id) {
        noteRepository.deleteById(id);
    }


}
