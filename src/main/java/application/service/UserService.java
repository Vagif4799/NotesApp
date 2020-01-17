package application.service;

import application.entity.Note;
import application.entity.User;
import application.repos.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private BCryptPasswordEncoder encoder;


    public User create_one(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }


    public Optional<User> get_one(Long id) {
        return userRepository.findById(id);
    }

    public Iterable<User> get_all() {
        return userRepository.findAll();
    }

    public void del_one(Long id) {
        userRepository.deleteById(id);
    }


    public User update_user(User user, Long id) {
        userRepository.save(user);
        return get_one(id).get();
    }



    public List<Note> get_notes(Long id) {
        return userRepository.findById(id).map(User::getNotes)
                .map(list->list.stream()
                .sorted(Comparator.comparing(Note::getCreatedAt).reversed())
                        .collect(Collectors.toList())
                ).get();
    }



}
