package discord.ws_project_discord.auth;

import discord.ws_project_discord.ApplicationListener;
import discord.ws_project_discord.DAO.UserDAO;
import discord.ws_project_discord.metier.User;
import discord.ws_project_discord.service.AuthInfo;

import javax.crypto.Mac;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AuthService {


    static Map<String, AuthInfo> tokens = new HashMap<>();

    static long lastCleanTime = System.currentTimeMillis();

    public static void cleanExpiredTokens() {
        // Clean expired tokens if needed
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastCleanTime > 10000) { // Clean every 10 sec
            tokens.entrySet().removeIf(entry -> entry.getValue().getExpirationDate() < currentTime);
            lastCleanTime = currentTime;
        }
    }

    public static boolean tokenExists(String token) {
        cleanExpiredTokens();
        // Check if the token exists
        return tokens.containsKey(token);
    }

    public static String connect(String login, String password) {
        //retourne un token si les identifiants sont valides
        if (isValidCredentials(login, password)) {
            //  create a token for the user
            User user = UserDAO.findByLogin(login);
            if (user == null) {
                user = UserDAO.findByEmail(login);
            }//le user est forcément toujours trouvé ici,
            // create a token (for simplicity, we just return the user ID as a string)

            String innerToken = String.valueOf(user.getId());
            String token = "Bearer " + innerToken;
            AuthInfo authInfo = new AuthInfo();
            authInfo.setUserId(user.getId());
            authInfo.setExpirationDate(System.currentTimeMillis() + 3_600_000); // 1 hour expiration
            tokens.put(token, authInfo);
            return token;

        } else {
            throw new IllegalArgumentException("Invalid credentials");
        }
    }

    public static boolean isValidCredentials(String login, String password) {

        // Check if the user exists with the given login and hashed password
        User user = UserDAO.findByLogin(login);
        if (user == null) {
            if (login.contains("@")) {
                // If login is an email, try to find by email
                user = UserDAO.findByEmail(login);
            }
            if (user == null) {
                return false; // User not found by email
            }
        }
        byte[] hashedPassword = UserDAO.hashPassword(password.getBytes());
        // Compare the hashed password with the stored password
        return Arrays.equals(user.getPwd(), hashedPassword) ||
               Arrays.equals(user.getPwd(), password.getBytes()); // Si le mdp est déjà crypté
    }
}
