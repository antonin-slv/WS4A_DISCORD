package discord.ws_project_discord.DAO;

import discord.ws_project_discord.metier.User;

import java.util.List;

public class UserDAO {

    private static final GenericDBDAO<User, Integer> genDAO = new GenericDBDAO<>(User.class);

    public static User find(Integer id) {
        return genDAO.find(id);
    }

    public static List<User> findAll() {
        return genDAO.findAll();
    }
    public static User findByLogin(String login) {
        return genDAO.findByField("login", login).getFirst();
    }
    public static User findByEmail(String email) {
        return genDAO.findByField("email", email).getFirst();
    }

    public static void create(User user) {
        genDAO.create(user);
    }

    public static void delete(Integer id) {
        genDAO.delete(id);
    }

    public static void update(User user) {
        genDAO.update(user);
    }

}
