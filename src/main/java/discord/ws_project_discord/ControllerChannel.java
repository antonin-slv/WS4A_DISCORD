package discord.ws_project_discord;

import com.fasterxml.jackson.databind.ObjectMapper;
import discord.ws_project_discord.DTO.ChannelLightDTO;
import discord.ws_project_discord.DTO.SubjectDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/channel/*")
public class ControllerChannel extends HttpServlet {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // TODO
        //        response.setContentType("application/json;charset=UTF-8");
        //        response.getWriter().println(objectMapper.writeValueAsString(subjectDTO));
    }
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        if (req.getMethod().equalsIgnoreCase("PATCH")) {
            doPatch(req, res);
        } else {
            super.service(req, res);
        }
    }
    public void doPatch(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //TODO
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //TODO
    }
    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //TODO
    }
    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //TODO
    }
}