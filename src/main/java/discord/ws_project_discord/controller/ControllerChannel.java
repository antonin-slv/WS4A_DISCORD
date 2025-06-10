package discord.ws_project_discord.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import discord.ws_project_discord.DTO.ChannelDTO;
import discord.ws_project_discord.service.ChannelService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/channel/*")
public class ControllerChannel extends HttpServlet {

    ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] tab = request.getPathInfo().split("/");
        if (tab.length == 0) {
            List<Integer> channelIds = ChannelService.getAllChannelIds();
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(objectMapper.writeValueAsString(channelIds));
            return;
        }
        if (tab.length == 2) {
            ChannelDTO channelDTO = ChannelService.getChannel(Integer.parseInt(tab[1]));
            if (channelDTO == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().println("Channel not found");
                return;
            }
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(objectMapper.writeValueAsString(channelDTO));
            return;
        }
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().println("Too many parameters provided");
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
