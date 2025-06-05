package discord.ws_project_discord.mapper;

import discord.ws_project_discord.DTO.MessageDTO;
import discord.ws_project_discord.metier.Message;

public class MessageMapper {

    public static MessageDTO toDTO(Message msg) {
        if (msg == null) {
            return null;
        }
        MessageDTO dto = new MessageDTO();
        dto.setId(msg.getId());
        dto.setAuthorName(msg.getSender().getLogin());
        dto.setContent(msg.getContent());
        dto.setSendDate(msg.getSendDate());

        dto.setChannelId(msg.getIdChannel());
        dto.setReceiverId(msg.getIdReceiver());

        dto.setRespondsToId(msg.getRespondsToId());
        dto.setReactions(
                msg.getReactTo()
                        .stream()
                        .map(ReactionMapper::toDTO)
                        .toList()
        );
        return dto;
    }

    public static Message toEntity(MessageDTO messageDTO) {
        if (messageDTO == null) {
            return null;
        }
        Message msg = new Message();
        msg.setId(messageDTO.getId());
        msg.setContent(messageDTO.getContent());
        msg.setSendDate(messageDTO.getSendDate());
        msg.setIdSender(messageDTO.getAuthorId());

        msg.setIdChannel(messageDTO.getChannelId());
        msg.setIdReceiver(messageDTO.getReceiverId());

        msg.setRespondsToId(messageDTO.getRespondsToId());
        return msg;
    }
}
