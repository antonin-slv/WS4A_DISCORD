package discord.ws_project_discord.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserListDTO implements java.io.Serializable{
    List<UserLightDTO> users;
}
