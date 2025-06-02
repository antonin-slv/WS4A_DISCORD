package discord.ws_project_discord.metier;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "invite")
public class Invite {
    @Id
    @Column(name = "invite_id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "remaining_use", nullable = false)
    private Integer remainingUse;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_sub", nullable = false)
    private Subject idSub;

}