package discord.ws_project_discord.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link discord.ws_project_discord.metier.User}
 */
@Getter
@Setter
public class UserLightDTO implements Serializable {
    Integer id;
    String login;
}