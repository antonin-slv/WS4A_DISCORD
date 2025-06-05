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
}
