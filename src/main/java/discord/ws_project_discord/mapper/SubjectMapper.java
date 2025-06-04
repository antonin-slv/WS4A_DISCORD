package discord.ws_project_discord.mapper;

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

    public static Subject toEntity(SubjectDTO dto) {
        if (dto == null) {
            return null;
        }
        Subject suj = new Subject();
        suj.setId(dto.getId());
        suj.setSubject(dto.getName());
        suj.setChannels(
                dto.getChannels()
                        .stream()
                        .map(ChannelMapper::toEntity)
                        .toList()
        );
        suj.setUserInSubjects(
                dto.getUsers()
                        .stream()
                        .map(UserInSubjectMapper::toEntity)
                        .toList()
        );
        return suj;
    }
}
