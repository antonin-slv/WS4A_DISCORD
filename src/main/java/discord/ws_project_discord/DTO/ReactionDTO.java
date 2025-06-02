package discord.ws_project_discord.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReactionDTO implements java.io.Serializable {
    String emoji;
    Integer userId;
}
