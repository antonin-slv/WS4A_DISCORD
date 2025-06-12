package discord.ws_project_discord.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import discord.ws_project_discord.DAO.ReactionDAO;
import discord.ws_project_discord.DTO.ReactionDTO;
import discord.ws_project_discord.mapper.ReactionMapper;
import discord.ws_project_discord.metier.ReactTo;
import discord.ws_project_discord.metier.ReactToId;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/reaction/*")
public class ControlleurReaction extends HttpServlet {

    ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        String meth = req.getMethod().toUpperCase();

        switch (meth) {
            case "POST" -> doPost(req, res);
            case "DELETE" -> doDelete(req, res);
            case "PUT" -> doPatch(req, res);
            default -> res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Method not allowed");
        }
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ReactionDTO reactToDTO = objectMapper.readValue(request.getInputStream(), ReactionDTO.class);
        ReactTo reactTo = ReactionMapper.toEntity(reactToDTO);
        try {
            ReactionDAO.create(reactTo);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_CONFLICT, "Failed to create the reaction as it already exists");
            return;
        }

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(objectMapper.writeValueAsString(reactToDTO));
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    public void doPatch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ReactionDTO reactToDTO = objectMapper.readValue(request.getInputStream(), ReactionDTO.class);
        ReactTo reactTo = ReactionMapper.toEntity(reactToDTO);
        try {
            ReactionDAO.update(reactTo);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Reaction not found");
            return;
        }

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(objectMapper.writeValueAsString(reactToDTO));
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pInf = request.getPathInfo();
        if (pInf == null || pInf.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No reaction specified");
            return;
        }
        String[] tab = pInf.split("/");
        if (tab.length != 4) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid reaction format");
            return;
        } else  {
            int userId;
            int msgId;

            try {
                userId = Integer.parseInt(tab[2]);
                msgId = Integer.parseInt(tab[3]);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid user or message ID");
                return;
            }

            ReactToId rID = new ReactToId();
            rID.setIdUser(userId);
            rID.setIdMsg(msgId);
            try {
                ReactionDAO.delete(rID);
            } catch (Exception ignored) {
            }
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }
}
