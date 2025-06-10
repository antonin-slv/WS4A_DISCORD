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

    public static UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }
        byte[] password;
        try {
            password = user.getPwd();
        } catch (IllegalArgumentException e) {
            password = null; // Handle the case where password is not set or is invalid
        }
        return new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getLogin(),
                password
        );
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
