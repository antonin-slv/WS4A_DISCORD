package discord.ws_project_discord.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import discord.ws_project_discord.DTO.UserDTO;
import discord.ws_project_discord.DTO.UserDTOStringPWD;
import discord.ws_project_discord.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.NotFoundException;

import java.io.IOException;
import java.util.List;

@WebServlet("/user/*")
public class ControllerUser extends HttpServlet {
    ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pInf = request.getPathInfo();
        String[] tab = (pInf !=null && !pInf.isEmpty()) ? pInf.split("/") : new String[0];
        response.setContentType("application/json;charset=UTF-8");

        if (tab.length == 0 || tab[1].isEmpty()) {
            List<UserDTO> userDTOList = UserService.getAllUsers();
            response.getWriter().println(objectMapper.writeValueAsString(userDTOList));
            return;
        } else if (tab.length == 2) {
            int userId;

            UserDTO userDTO;

            try {
                try {
                    userId = Integer.parseInt(tab[1]);
                    userDTO = UserService.getUserById(userId);
                } catch (NumberFormatException e) {
                    userDTO = UserService.getUserByUsername(tab[1]);
                }
            } catch (NotFoundException e) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().println(e.getMessage());
                return;
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().println(e.getMessage());
                return;
            }
            response.getWriter().println(objectMapper.writeValueAsString(userDTO));
            return;
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
        String pInf = request.getPathInfo();
        String[] tab = pInf != null ? pInf.split("/") : new String[0];

        if (!(tab.length == 0 || tab[1].isEmpty())) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Invalid request format");
            return;
        }

        UserDTO userDTO = objectMapper.readValue(request.getInputStream(), UserDTOStringPWD.class);
        try {
            userDTO = UserService.updateUser(userDTO);
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println(e.getMessage());
            return;
        } catch (NotFoundException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().println(e.getMessage());
            return;
        }

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(objectMapper.writeValueAsString(userDTO));

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserDTO userDTO = objectMapper.readValue(request.getInputStream(), UserDTOStringPWD.class);
        UserDTO userDTO2 = UserService.createUser(userDTO);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(objectMapper.writeValueAsString(userDTO2));
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pInf = request.getPathInfo();
        String[] tab = pInf != null ? pInf.split("/") : new String[0];

        if (!(tab.length == 0 || tab[1].isEmpty())) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Invalid request format");
            return;
        }

        UserDTO userDTO = objectMapper.readValue(request.getInputStream(), UserDTOStringPWD.class);
        if (userDTO.getEmail() == null || userDTO.getLogin() == null || userDTO.getPwd() == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Email, login, and password must not be null");
            return;
        }
        userDTO = UserService.updateUser(userDTO);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(objectMapper.writeValueAsString(userDTO));
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pInf = request.getPathInfo();
        String[] tab = pInf != null ? pInf.split("/") : new String[0];
        if (tab.length == 2) {
            int userId;
            try {
                userId = Integer.parseInt(tab[1]);
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().println("Invalid user ID format");
                return;
            }

            UserService.deleteUser(userId);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Invalid request format");
        }
    }
}