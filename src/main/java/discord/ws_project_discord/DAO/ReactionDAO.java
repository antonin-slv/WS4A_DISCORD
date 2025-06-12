package discord.ws_project_discord.DAO;

import discord.ws_project_discord.metier.ReactTo;
import discord.ws_project_discord.metier.ReactToId;

public class ReactionDAO {

    private static final GenericDBDAO<ReactTo, ReactToId> genDAO =
            new GenericDBDAO<>(ReactTo.class);

    public static ReactTo find(ReactToId id) {
        return genDAO.find(id);
    }

    public  static void create(ReactTo reactTo) {
        genDAO.create(reactTo);
    }

    public static void update(ReactTo reactTo) throws Exception {
        genDAO.update(reactTo);
    }

    public static void delete(ReactToId id) {
        genDAO.delete(id);
    }
}
