package discord.ws_project_discord.metier;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "react_to")
public class ReactTo {
    @EmbeddedId
    private ReactToId id;

    @MapsId("idUser")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_user", nullable = false, insertable = false, updatable = false)
    private User user;

    @Column(name = "id_user")
    private Integer idUser;

    @MapsId("idMsg")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_msg", nullable = false, insertable = false, updatable = false)
    private Message msg;

    @Column(name = "reaction", length = Integer.MAX_VALUE)
    private String reaction;

}