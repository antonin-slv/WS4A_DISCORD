package discord.ws_project_discord.DTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class PrivConvDTO implements Serializable {
    UserDTO other;
    List<MessageDTO> messages;
}
