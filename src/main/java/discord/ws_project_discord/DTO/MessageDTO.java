package discord.ws_project_discord.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class MessageDTO implements java.io.Serializable {
    Integer id;

    Integer authorId;
    String authorName;
    String content;
    LocalDateTime sendDate;

    Integer channelId;
    Integer receiverId;

    Integer respondsToId;
    List<ReactionLightDTO> reactions;
}
