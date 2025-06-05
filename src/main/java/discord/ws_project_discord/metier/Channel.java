package discord.ws_project_discord.metier;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "channel")
public class Channel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_chan", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "channel", nullable = false, length = Integer.MAX_VALUE)
    private String channel;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @OneToMany(mappedBy = "channel",fetch = FetchType.LAZY)
    private List<Message> messages;
}