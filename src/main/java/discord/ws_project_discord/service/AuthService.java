package discord.ws_project_discord.service;

import discord.ws_project_discord.ApplicationListener;
import discord.ws_project_discord.DAO.UserDAO;
import discord.ws_project_discord.metier.User;

import javax.crypto.Mac;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AuthService {


    static Map<String,AuthInfo> tokens = new HashMap<>();


    public static void cleanExpiredTokens() {
        // Clean expired tokens
        long currentTime = System.currentTimeMillis();
        tokens.entrySet().removeIf(entry -> entry.getValue().getExpirationDate() < currentTime);
    }


    public static AuthInfo getAuthInfo(String token) {
        // Retrieve the AuthInfo for the given token
        return tokens.get(token);
    }

    public static boolean tokenExists(String token) {
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
            String token = "Bearer" + innerToken;
            AuthInfo authInfo = new AuthInfo();
            authInfo.setUserId(user.getId());
            authInfo.setExpirationDate(System.currentTimeMillis() + 3600000); // 1 hour expiration
            tokens.put(innerToken, authInfo);
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
