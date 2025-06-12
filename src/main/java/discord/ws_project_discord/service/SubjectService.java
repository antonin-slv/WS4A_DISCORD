package discord.ws_project_discord.service;

import discord.ws_project_discord.DAO.SubjectDAO;
import discord.ws_project_discord.DTO.SubjectDTO;
import discord.ws_project_discord.mapper.SubjectMapper;
import discord.ws_project_discord.metier.Subject;

import java.util.List;

public class SubjectService {
    public static SubjectDTO getSubject(int id) {
        Subject subj = SubjectDAO.find(id);
        return SubjectMapper.toDTO(subj);
    }

    public static List<SubjectDTO> getAllSubjects() {
        List<Subject> subjects = SubjectDAO.findAll();
        return subjects.stream()
                .map(SubjectMapper::toDTO)
                .toList();
    }

    public static SubjectDTO createSubject(SubjectDTO subjectDTO) {
        Subject subject = SubjectMapper.toEntity(subjectDTO);
        SubjectDAO.create(subject);
        return SubjectMapper.toDTO(subject);
    }

    public static void updateSubject(SubjectDTO subjectDTO) {
        Subject subject = SubjectMapper.toEntity(subjectDTO);
        SubjectDAO.update(subject);
    }
}
