package discord.ws_project_discord.mapper;

import discord.ws_project_discord.DTO.ReactionDTO;
import discord.ws_project_discord.metier.ReactTo;
import discord.ws_project_discord.metier.ReactToId;

public class ReactionMapper {
    public static ReactionDTO toDTO(ReactTo reactTo) {
        ReactionDTO reactionLightDTO = new ReactionDTO();
        if (reactTo == null) {
            return null;
        }
        reactionLightDTO.setEmoji(reactTo.getReaction());
        reactionLightDTO.setMessageId(reactTo.getId().getIdMsg());
        reactionLightDTO.setUserId(reactTo.getId().getIdUser());
        return reactionLightDTO;
    }

    public static ReactTo toEntity(ReactionDTO reactionDTO) {
        ReactTo reactTo = new ReactTo();
        if (reactionDTO == null) {
            return null;
        }
        reactTo.setReaction(reactionDTO.getEmoji());
        ReactToId rID = new ReactToId();
        rID.setIdUser(reactionDTO.getUserId());
        rID.setIdMsg(reactionDTO.getMessageId());
        reactTo.setId(rID);
        return reactTo;
    }
}
