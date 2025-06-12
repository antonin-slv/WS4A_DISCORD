package discord.ws_project_discord.service;

import discord.ws_project_discord.DAO.ChannelDAO;
import discord.ws_project_discord.DTO.ChannelDTO;

import discord.ws_project_discord.DTO.ChannelLightDTO;
import discord.ws_project_discord.mapper.ChannelMapper;
import discord.ws_project_discord.metier.Channel;

import java.util.List;

public class ChannelService {

    public static List<Integer> getAllChannelIds() {
        return ChannelDAO.findAll()
                .stream()
                .map(Channel::getId)
                .toList();
    }

    public static ChannelDTO getChannel(int id) {
        Channel chan = ChannelDAO.find(id);
        return ChannelMapper.toDTO(chan);
    }

    public static void createChannel(ChannelLightDTO dto) {
        Channel chan = ChannelMapper.toEntity(dto);
        ChannelDAO.create(chan);
    }
}
