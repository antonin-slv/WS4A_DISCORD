package discord.ws_project_discord.mapper;

import discord.ws_project_discord.DTO.ChannelDTO;
import discord.ws_project_discord.DTO.ChannelLightDTO;
import discord.ws_project_discord.metier.Channel;
import discord.ws_project_discord.metier.Subject;

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

    public static ChannelDTO toDTO(Channel chan) {
        if (chan == null) {
            return null;
        }
        ChannelDTO dto = new ChannelDTO();
        dto.setId(chan.getId());
        dto.setName(chan.getChannel());
        //TODO messga
        return dto;
    }

    public static Channel toEntity(ChannelLightDTO channelLightDTO) {
        if (channelLightDTO == null) {
            return null;
        }
        // créé une petite entité qui pourra être persistée
        Channel chan = new Channel();
        chan.setId(channelLightDTO.getId());
        chan.setChannel(channelLightDTO.getName());

        Subject subject = new Subject();
        subject.setId(channelLightDTO.getSubjectId());
        chan.setSubject(subject);
        return chan;
    }

    public static Channel toEntity(ChannelLightDTO channelDTO, Channel channel) {
        if (channelDTO == null || channel == null) {
            return null;
        }
        if (channelDTO.getId() == null) {
            throw new IllegalArgumentException("ChannelDTO must have an ID to merge.");
        } else if (!channelDTO.getId().equals(channel.getId())) {
            throw new IllegalArgumentException("ChannelDTO ID does not match the existing Channel ID.");
        }
        if (channelDTO.getName() != null)
            channel.setChannel(channelDTO.getName());
        if (channelDTO.getSubjectId() != null) {
            Subject subject = new Subject();
            subject.setId(channelDTO.getSubjectId());
            channel.setSubject(subject);
        }
        return channel;
    }
}
