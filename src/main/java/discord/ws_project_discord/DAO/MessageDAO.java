package discord.ws_project_discord.DAO;

import discord.ws_project_discord.metier.Message;
import jakarta.persistence.EntityManager;
import java.util.List;

public class MessageDAO {
    private static final GenericDBDAO<Message, Integer> genDAO = new GenericDBDAO<>(Message.class);

    public static Message find(Integer id) {
        return genDAO.find(id);
    }

    public static List<Message> findByChannelId(Integer channelID) {
        try(EntityManager em = GenericDBDAO.getEM()) {
            String sql = "SELECT m FROM Message m WHERE m.channel.id = :channelID ORDER BY m.sendDate DESC";
            return em.createQuery(sql, Message.class)
                    .setParameter("channelID", channelID)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving messages for channel ID: " + channelID, e);
        }
    }

    public static List<Message> findDM(Integer idUser1, Integer idUser2) {

        if (idUser1 == null || idUser2 == null) {
            throw new IllegalArgumentException("Users cannot be null");
        }
        try (EntityManager em = GenericDBDAO.getEM()) {
            String sql =
                    """
                        SELECT m FROM Message m
                        WHERE (m.sender.id = :user1 AND m.idReceiver = :user2)
                        OR (m.sender.id = :user2 AND m.idReceiver = :user1)
                    """;
            return em.createQuery(sql, Message.class)
                    .setParameter("user1", idUser1)
                    .setParameter("user2", idUser2)
                    .getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving direct messages between users: " + idUser1 + " and " + idUser2, e);
        }
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
