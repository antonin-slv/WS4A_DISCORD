package discord.ws_project_discord.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class MessageDTO implements java.io.Serializable {
    Integer id;
    Integer respondsToId;
    String content;
    String authorName;
    Integer authorId;
    LocalDate sendDate;

    List<ReactionDTO> reactions;

}
