package discord.ws_project_discord.DAO;

import discord.ws_project_discord.metier.Subject;

import java.util.List;

public class SubjectDAO {
    private static final GenericDBDAO<Subject,Integer> genDAO = new GenericDBDAO<>(Subject.class);

    public static Subject find(Integer id) {
        return genDAO.find(id);
    }
    public static List<Subject> findAll() {
        return genDAO.findAll();
    }

    public static void create(Subject subject) {
        genDAO.create(subject);
    }

    public static void delete(Integer id) {
        genDAO.delete(id);
    }

    public static void update(Subject subject) {genDAO.update(subject);}
}
