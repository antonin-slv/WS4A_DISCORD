package discord.ws_project_discord.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import discord.ws_project_discord.DAO.MessageDAO;
import discord.ws_project_discord.DTO.MessageDTO;
import discord.ws_project_discord.service.MessageService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/message/*")
public class ControllerMessage extends HttpServlet {
    ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pInf = request.getPathInfo();
        String[] tab = (pInf != null && !pInf.isEmpty()) ? pInf.split("/") : new String[0];
        response.setContentType("application/json;charset=UTF-8");

        if (tab.length == 2) {
            try {
                Integer messageId = Integer.parseInt(tab[1]);
                MessageDTO messageDTO = MessageService.findMessageById(messageId);
                response.getWriter().println(objectMapper.writeValueAsString(messageDTO));
                return;
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid message ID format");
                return;
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Message not found");
                return;
            }
        }
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().println("Too many parameters provided or invalid request format");
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
        if (request.getContentType() == null || !request.getContentType().contains("application/json")) {
            response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "Content-Type must be application/json");
            return;
        }
        MessageDTO messageDTO = objectMapper.readValue(request.getInputStream(), MessageDTO.class);
        messageDTO = MessageService.updateMesssage(messageDTO);

        response.getWriter().println(objectMapper.writeValueAsString(messageDTO));
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getContentType() == null || !request.getContentType().contains("application/json")) {
            response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "Content-Type must be application/json");
            return;
        }

        MessageDTO messageDTO = objectMapper.readValue(request.getInputStream(), MessageDTO.class);
        messageDTO.setSendDate(LocalDateTime.now());

        MessageService.sendMessage(messageDTO);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(objectMapper.writeValueAsString(messageDTO));
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "PUT method is not supported for this endpoint");
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pInf = request.getPathInfo();
        String[] tab = (pInf != null && !pInf.isEmpty()) ? pInf.split("/") : new String[0];

        if (tab.length == 2) {
            try {
                int messageId = Integer.parseInt(tab[1]);
                MessageDAO.delete(messageId);
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid message ID format");
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error deleting message: " + e.getMessage());
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid request format");
        }
    }
}
