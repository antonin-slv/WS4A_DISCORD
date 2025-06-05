package discord.ws_project_discord.mapper;

import discord.ws_project_discord.DTO.ReactionLightDTO;
import discord.ws_project_discord.metier.ReactTo;

public class ReactionMapper {
    public static ReactionLightDTO toDTO(ReactTo reactTo) {
        ReactionLightDTO reactionLightDTO = new ReactionLightDTO();
        if (reactTo == null) {
            return null;
        }
        reactionLightDTO.setEmoji(reactTo.getReaction());
        reactionLightDTO.setUserId(reactTo.getIdUser());
        return reactionLightDTO;
    }
}
