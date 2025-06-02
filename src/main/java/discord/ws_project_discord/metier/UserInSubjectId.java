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
public class UserInSubjectId implements Serializable {
    private static final long serialVersionUID = 8827723708742475944L;
    @NotNull
    @Column(name = "id_user", nullable = false)
    private Integer idUser;

    @NotNull
    @Column(name = "id_sub", nullable = false)
    private Integer idSub;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserInSubjectId entity = (UserInSubjectId) o;
        return Objects.equals(this.idUser, entity.idUser) &&
                Objects.equals(this.idSub, entity.idSub);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idSub);
    }

}