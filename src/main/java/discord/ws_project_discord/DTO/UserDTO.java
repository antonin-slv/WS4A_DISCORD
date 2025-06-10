package discord.ws_project_discord.DTO;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link discord.ws_project_discord.metier.User}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {
    Integer id;
    String email;
    String login;
    byte[] pwd;
}