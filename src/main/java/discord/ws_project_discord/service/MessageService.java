package discord.ws_project_discord.service;

import discord.ws_project_discord.DAO.MessageDAO;
import discord.ws_project_discord.DTO.MessageDTO;
import discord.ws_project_discord.mapper.MessageMapper;
import discord.ws_project_discord.metier.Message;

public class MessageService {

    public static void sendMessage(MessageDTO messageDTO) {
        Message msg = MessageMapper.toEntity(messageDTO);
        //TODO verifier que tout est ok
        MessageDAO.create(msg);
    }
}
