package discord.ws_project_discord.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SubjectDTO implements java.io.Serializable {

    Integer id;

    String name;
    List<ChannelLightDTO> channels;
    List<UserInSubjectDTO> users;
}
