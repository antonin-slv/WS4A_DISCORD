package discord.ws_project_discord.filter;

import discord.ws_project_discord.auth.AuthService;
import discord.ws_project_discord.auth.ControllerConnexion;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ValidTokenService {
    public static boolean isFiltered(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Here you can implement your token validation logic
        // For example, check if a token is present in the request headers

        String pathInfo = request.getRequestURL().toString();
        if ( pathInfo.contains("/AuthServer/verify") || pathInfo.contains("/AuthServer/connect")) {
            return false; // Do not filter requests to the AuthServer verify endpoint
        }

        String authToken = request.getHeader("Authorization");

        if (authToken == null) {
            // If the token is not present, return an unauthorized response
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Missing authorization token");
            return true; // Indicating that the request should be filtered out
        } if(!isValidToken(authToken, request, response)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid token");
            return true; // Indicating that the request should be filtered out
        }
        return false;
    }

    private static boolean isValidToken(String token, ServletRequest request, HttpServletResponse httpResponse) {
        // calls the Auth endpoint to verify the token
        // This is a placeholder for actual token validation logic
        // You can implement your logic here, such as checking against a database or an external service

        //on créé une servelt request qui sera envoyée à /AuthServer/verify
        try {
            HttpServletRequest httpRequest = (HttpServletRequest) request;

            // Simulate a call to the AuthService to verify the token
            return AuthService.tokenExists(token);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }
}
