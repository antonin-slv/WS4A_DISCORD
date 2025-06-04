package discord.ws_project_discord.mapper;

import discord.ws_project_discord.DTO.UserInSubjectDTO;
import discord.ws_project_discord.metier.UserInSubjectId;

public class UserInSubjectIdMapper {
    public static UserInSubjectId toEntity(UserInSubjectDTO dto) {
        if (dto.getIdUser() == null || dto.getIdSubject() == null) {
            return null;
        }
        UserInSubjectId userInSubjectId = new UserInSubjectId();
        userInSubjectId.setIdUser(dto.getIdUser());
        userInSubjectId.setIdSub(dto.getIdSubject());
        return userInSubjectId;
    }
}
