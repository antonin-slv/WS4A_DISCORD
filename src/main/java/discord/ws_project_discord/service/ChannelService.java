package discord.ws_project_discord.service;

import discord.ws_project_discord.DAO.ChannelDAO;
import discord.ws_project_discord.DTO.ChannelDTO;

import discord.ws_project_discord.mapper.ChannelMapper;
import discord.ws_project_discord.metier.Channel;

public class ChannelService {

    public static ChannelDTO getChannel(int id) {
        Channel chan = ChannelDAO.find(id);
        return ChannelMapper.toDTO(chan);
    }
}
