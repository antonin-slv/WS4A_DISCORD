package discord.ws_project_discord.service;

import discord.ws_project_discord.DAO.MessageDAO;
import discord.ws_project_discord.DTO.MessageDTO;
import discord.ws_project_discord.mapper.MessageMapper;
import discord.ws_project_discord.metier.Message;

import java.util.List;
import java.util.stream.Collectors;

public class MessageService {


    public static MessageDTO findMessageById(Integer id) {
        Message msg = MessageDAO.find(id);
        if (msg == null) {
            throw new RuntimeException("Message not found with ID: " + id);
        }
        return MessageMapper.toDTO(msg);
    }

    public static MessageDTO updateMesssage(MessageDTO messageDTO) {
        Message msg = MessageMapper.majMessageWithDTO(MessageDAO.find(messageDTO.getId()), messageDTO);
        try {
            MessageDAO.update(msg);
        } catch (Exception e) {
            throw new RuntimeException("Error updating message: " + e.getMessage());
        }
        return MessageMapper.toDTO(msg);
    }

    public static void sendMessage(MessageDTO messageDTO) {
        Message msg = MessageMapper.toEntity(messageDTO);
        //TODO verifier que tout est ok
        MessageDAO.create(msg);
    }

    public static List<MessageDTO> getMessagesBetweenUsers(int curentUserId, int otherUserId) {
        List<Message> messages = MessageDAO.findDM(curentUserId, otherUserId);
        return messages.stream().map(MessageMapper::toDTO).collect(Collectors.toList());
    }
}
