package discord.ws_project_discord.metier;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class ReactToId implements Serializable {
    private static final long serialVersionUID = 7375731252670976198L;
    @NotNull
    @Column(name = "id_user", nullable = false)
    private Integer idUser;

    @NotNull
    @Column(name = "id_msg", nullable = false)
    private Integer idMsg;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ReactToId entity = (ReactToId) o;
        return Objects.equals(this.idUser, entity.idUser) &&
                Objects.equals(this.idMsg, entity.idMsg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idMsg);
    }

}