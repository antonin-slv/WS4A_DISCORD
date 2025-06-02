package discord.ws_project_discord.DAO;

import discord.ws_project_discord.metier.Channel;
import discord.ws_project_discord.metier.Message;
import discord.ws_project_discord.metier.User;

public class MessageDAO {
    private static final GenericDBDAO<Message, Integer> genDAO = new GenericDBDAO<>(Message.class);

    public static Message find(Integer id) {
        return genDAO.find(id);
    }
    public static void create(Message message) {
        genDAO.create(message);
    }
    public static void delete(Integer id) {
        genDAO.delete(id);
    }
    public static void update(Message message) throws Exception {
        genDAO.update(message);
    }



}
