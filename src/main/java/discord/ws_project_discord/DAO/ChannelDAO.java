package discord.ws_project_discord.DAO;

import discord.ws_project_discord.metier.Channel;

import java.util.List;

public class ChannelDAO{

    private static final GenericDBDAO<Channel, Integer> genDAO = new GenericDBDAO<>(Channel.class);

    public static Channel find(Integer id) {
        return genDAO.find(id);
    }
    public static List<Channel> findAll() {
        return genDAO.findAll();
    }
    public static void create(Channel channel) {
        genDAO.create(channel);
    }
    public static void delete(Integer id) {
        genDAO.delete(id);
    }
    public static void update(Channel channel) throws Exception {
        genDAO.update(channel);
    }

}
