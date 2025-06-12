package discord.ws_project_discord.DAO;

import discord.ws_project_discord.metier.Subject;
import discord.ws_project_discord.metier.UserInSubject;
import discord.ws_project_discord.metier.UserInSubjectId;

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
        List<UserInSubject> users = subject.getUserInSubjects();
        subject.setUserInSubjects(null); // Avoid circular reference during creation
        genDAO.create(subject);
        if (users != null) {
            for (UserInSubject userInSubject : users) {
                UserInSubjectId userInSubjectId = new UserInSubjectId();
                userInSubjectId.setIdUser(userInSubject.getUser().getId());
                userInSubjectId.setIdSub(subject.getId());
                userInSubject.setId(userInSubjectId);
            }
        }
        subject.setUserInSubjects(users); // Restore the user list after creation
        genDAO.update(subject);
    }

    public static void delete(Integer id) {
        genDAO.delete(id);
    }

    public static void update(Subject subject) {genDAO.update(subject);}
}
