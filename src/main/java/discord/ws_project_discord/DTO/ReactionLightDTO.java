package discord.ws_project_discord.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReactionLightDTO implements java.io.Serializable {
    String emoji;
    Integer userId;
}
