package discord.ws_project_discord.service;

import discord.ws_project_discord.DAO.ChannelDAO;
import discord.ws_project_discord.DTO.ChannelDTO;

import java.nio.channels.Channel;
import java.util.List;

public class ChannelService {

    public static ChannelDTO getChannel(int id) {
        Channel chan = (Channel) ChannelDAO.find(id);
        return; //TODO;
    }
}
