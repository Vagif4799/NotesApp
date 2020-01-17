package application.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {

    @Column(name = "u_id")
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private final Long id;
    @Column(name = "email")
    @Email(message = "Email Should be added")
    private String email;
    @Column(name = "password")
    private String password;



    @JsonIgnore
    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Note> notes;


    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "u_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "r_id")
    )
    private Set<Role> roles;




}
