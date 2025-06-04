package discord.ws_project_discord.Mapper;

import discord.ws_project_discord.DTO.SubjectDTO;
import discord.ws_project_discord.metier.Subject;

public class SubjectMapper {
    //maps stuff
    public static SubjectDTO toDTO(Subject suj) {
        if (suj == null) {
            return null;
        }
        SubjectDTO dto = new SubjectDTO();
        dto.setId(suj.getId());
        dto.setName(suj.getSubject());
        dto.setChannels(
                suj.getChannels()
                        .stream()
                        .map(ChannelMapper::toLightDTO)
                        .toList()
        );

        dto.setUsers(
                suj.getUserInSubjects()
                        .stream()
                        .map(UserMapper::toLightDTO)
                        .toList()
        );
        //dto.setChannels(ChannelMapper.toDTOSet(suj.getChannels()));
        //dto.setUserInSubjects(UserInSubjectMapper.toDTOSet(suj.getUserInSubjects()));
        return dto;
    }
}
