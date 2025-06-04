package discord.ws_project_discord.metier;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "subject")
public class Subject implements Serializable {
    @Id
    @Column(name = "id_sub", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "subject", nullable = false, length = Integer.MAX_VALUE)
    private String subject;

    //make shure to load all the channels and invites when loading a subject
    @OneToMany(
            mappedBy = "subject",
            fetch = FetchType.EAGER)
    private Set<Channel> channels = new LinkedHashSet<>();

    @OneToMany(
            mappedBy = "subject",
            fetch = FetchType.EAGER)
    private Set<UserInSubject> userInSubjects = new LinkedHashSet<>();

}