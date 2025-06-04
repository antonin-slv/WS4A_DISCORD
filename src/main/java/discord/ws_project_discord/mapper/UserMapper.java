package discord.ws_project_discord.mapper;

import discord.ws_project_discord.DTO.UserDTO;
import discord.ws_project_discord.DTO.UserInSubjectDTO;
import discord.ws_project_discord.metier.User;
import discord.ws_project_discord.metier.UserInSubject;

public class UserMapper {

    public static UserInSubjectDTO toLightDTO(UserInSubject user) {
        UserInSubjectDTO dto = new UserInSubjectDTO();
        if (user == null) {
            return null;
        }
        dto.setIdUser(user.getId().getIdUser());
        dto.setIsAdmin(user.getIsAdmin());
        return dto;
    }

    public static User toEntity(UserDTO dto) {
        if (dto == null) {
            return null;
        }
        User user = new User();
        user.setId(dto.getId());
        user.setEmail(dto.getEmail());
        user.setLogin(dto.getLogin());
        user.setPwd(dto.getPwd());
        return user;
    }

    public static User toEntity(UserInSubjectDTO dto) {
        if (dto == null) {
            return null;
        }
        User user = new User();
        user.setId(dto.getIdUser());
        user.setLogin(dto.getLogin());
        return user;
    }
}
