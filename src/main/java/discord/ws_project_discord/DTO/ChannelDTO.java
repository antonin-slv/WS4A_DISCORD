package discord.ws_project_discord.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChannelDTO implements java.io.Serializable {
    Integer id;
    String name;
    List<MessageDTO> messages;
}
