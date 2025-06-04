package discord.ws_project_discord.mapper;

import discord.ws_project_discord.DTO.ChannelLightDTO;
import discord.ws_project_discord.metier.Channel;

public class ChannelMapper {

    public static ChannelLightDTO toLightDTO(Channel chan) {
        if (chan == null) {
            return null;
        }
        ChannelLightDTO dto = new ChannelLightDTO();
        dto.setId(chan.getId());
        dto.setName(chan.getChannel());
        return dto;
    }
}
