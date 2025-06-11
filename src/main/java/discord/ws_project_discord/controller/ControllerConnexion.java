package discord.ws_project_discord.controller;

import discord.ws_project_discord.service.AuthService;
import discord.ws_project_discord.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Base64;

@WebServlet("/connect")
public class ControllerConnexion extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        if(request.getMethod().equals("OPTIONS")){
            response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
            response.setHeader("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        String authorization = request.getHeader("Authorization");
        String token = connect(authorization);
        if (token.isEmpty()) {
            response.setHeader("WWW-Authenticate", "Basic realm=\"Veuillez saisir votre login/mot de passe\"");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("text/plain;charset=UTF-8");
            response.getWriter().write("Invalid credentials");
            return;
        }

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"token\":\"" + token + "\"}");

    }

    private String connect(String authorization) {
        if (authorization == null || !authorization.startsWith("Basic")) return "";
        try {
            String token = authorization.substring("Basic".length()).trim();// Décoder le token
            byte[] base64 = Base64.getDecoder().decode(token);
            String credentials = new String(base64);
            String[] lm = credentials.split(":");// Extraire login et mot de passe
            if (lm.length != 2) return "";
            String login = lm[0].trim();
            String pwd = lm[1].trim();
            return AuthService.connect(login, pwd); // Vérifier dans la base de données
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            return "";
        }
    }
}
