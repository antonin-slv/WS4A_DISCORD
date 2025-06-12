package discord.ws_project_discord.metier;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "subject")
public class Subject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sub", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "subject", nullable = false, length = Integer.MAX_VALUE)
    private String subject;

    @NotNull
    @Column(name = "is_public" , nullable = false)
    private Boolean isPublic;

    //make shure to load all the channels and invites when loading a subject
    @OneToMany(
            mappedBy = "subject",
            fetch = FetchType.EAGER)
    private List<Channel> channels;

    @OneToMany(
            mappedBy = "subject",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, // Ajoute cette ligne
            orphanRemoval = true       // Permet la suppression des orphelins
    )
    private List<UserInSubject> userInSubjects;

}