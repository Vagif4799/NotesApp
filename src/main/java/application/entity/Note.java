package application.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "n_id")
    private final Long id;

    @Column
    @NotNull
    @Size(min=1, message = "Please, add Caption.")
    private  String name;


    @Column
    private String body;


    @Column
    private Date createdAt;

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }



    @ManyToOne
    @JoinTable(name = "r_note_user",
            joinColumns = @JoinColumn(name = "note_id", referencedColumnName = "n_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "u_id")
    )
    private User user;



}
