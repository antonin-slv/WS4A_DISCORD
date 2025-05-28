// © 2024 Maxime MORGE <maxime.morge@univ-lyon1.fr>
package discord.ws_project_discord;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;

import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebListener
public class ApplicationListener implements ServletContextListener {
	@Getter
    @Setter
    private static EntityManagerFactory emf;


    public static MessageDigest getMessageDigest() {
        try {
            return MessageDigest.getInstance("SHA-256");
        } catch (Exception e) {
            throw new RuntimeException("Failed to create MessageDigest", e);
        }
    }

    public void contextDestroyed(ServletContextEvent arg0)  {  
        if(emf!=null && emf.isOpen()) {
            emf.close();
        } 
    }
    public void contextInitialized(ServletContextEvent arg0)  {
        emf = Persistence.createEntityManagerFactory("default");
    }

    public static List<Object[]> executePlainSQL(String sql) {
        try (EntityManager em = emf.createEntityManager()) {
            List<Object[]> results = new ArrayList<>();
            em.unwrap(Session.class).doWork(connection -> {
                try (Statement stmt = connection.createStatement();
                     ResultSet rs = stmt.executeQuery(sql))
                {
                    int columnCount = rs.getMetaData().getColumnCount();
                    while (rs.next()) {
                        Object[] row = new Object[columnCount];
                        for (int i = 1; i <= columnCount; i++) {
                            row[i-1] = rs.getObject(i);
                        }
                        results.add(row);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException("SQL execution failed", e);
                }
            });
            return results;
        } catch (Exception e) {
            throw new RuntimeException("Failed to execute SQL", e);
        }
    }
}