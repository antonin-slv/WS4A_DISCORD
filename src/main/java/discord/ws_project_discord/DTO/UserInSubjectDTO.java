package discord.ws_project_discord.DTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link discord.ws_project_discord.metier.User}
 */
@Getter
@Setter
public class UserInSubjectDTO implements Serializable {
    Integer idUser;
    Integer idSubject;
    Boolean isAdmin;
}