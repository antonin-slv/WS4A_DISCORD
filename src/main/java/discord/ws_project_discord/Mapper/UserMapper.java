package discord.ws_project_discord.Mapper;

import discord.ws_project_discord.DTO.UserLightDTO;
import discord.ws_project_discord.metier.User;
import discord.ws_project_discord.metier.UserInSubject;

public class UserMapper {

    public static UserLightDTO toLightDTO(UserInSubject user) {
        UserLightDTO dto = new UserLightDTO();
        if (user == null) {
            return null;
        }
        dto.setId(user.getId().getIdUser());
        dto.setIsAdmin(user.getIsAdmin());
        return dto;
    }
}
