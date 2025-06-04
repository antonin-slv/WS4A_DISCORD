package discord.ws_project_discord.mapper;

import discord.ws_project_discord.DTO.UserInSubjectDTO;
import discord.ws_project_discord.metier.Subject;
import discord.ws_project_discord.metier.User;
import discord.ws_project_discord.metier.UserInSubject;

public class UserInSubjectMapper {
    public static UserInSubject toEntity(UserInSubjectDTO dto) {
        if (dto == null) {
            return null;
        }
        UserInSubject userInSubject = new UserInSubject();
        userInSubject.setId(UserInSubjectIdMapper.toEntity(dto));
        userInSubject.setIsAdmin(dto.getIsAdmin());

        User user = new User();
        user.setId(dto.getIdUser());
        user.setLogin(dto.getLogin());
        userInSubject.setUser(user);

        Subject subject = new Subject();
        subject.setId(dto.getIdSubject());
        userInSubject.setSubject(subject);
        return userInSubject;

    }
}
