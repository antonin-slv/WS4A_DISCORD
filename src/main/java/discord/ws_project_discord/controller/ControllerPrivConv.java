package discord.ws_project_discord.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import discord.ws_project_discord.DTO.PrivConvDTO;
import discord.ws_project_discord.service.MessageService;
import discord.ws_project_discord.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/privConv/*")
public class ControllerPrivConv extends HttpServlet {
    ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] tab = request.getPathInfo().split("/");
        if (tab.length != 3) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Invalid request format. Expected format: /privConv/{userId}");
            return;
        }
        int curentUserId = Integer.parseInt(tab[1]);
        int otherUserId = Integer.parseInt(tab[2]);
        PrivConvDTO privConvDTO = new PrivConvDTO();
        privConvDTO.setCurentUser(UserService.getUserById(curentUserId));
        privConvDTO.setOtherUser(UserService.getUserById(otherUserId));
        privConvDTO.setMessages(
                MessageService.getMessagesBetweenUsers(curentUserId, otherUserId)
        );

        response.getWriter().println(objectMapper.writeValueAsString(privConvDTO));
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        if (req.getMethod().equalsIgnoreCase("PATCH")) {
            doPatch(req, res);
        } else {
            super.service(req, res);
        }
    }

    public void doPatch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //TODO
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //TODO
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //TODO
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //TODO
    }
}
