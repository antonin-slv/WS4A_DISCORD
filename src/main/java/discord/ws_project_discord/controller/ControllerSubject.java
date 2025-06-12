package discord.ws_project_discord.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import discord.ws_project_discord.DTO.SubjectDTO;
import discord.ws_project_discord.service.SubjectService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@WebServlet("/subject/*")
public class ControllerSubject extends HttpServlet {

    ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<SubjectDTO> subjectDTOList = SubjectService.getAllSubjects();
        subjectDTOList = subjectDTOList.stream().sorted(Comparator.comparing(SubjectDTO::getId)).toList();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(objectMapper.writeValueAsString(subjectDTOList));
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
        SubjectDTO subjectDTO = objectMapper.readValue(request.getInputStream(), SubjectDTO.class);
        SubjectService.updateSubject(subjectDTO);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pInf = request.getPathInfo();
        String[] tab = (pInf != null && !pInf.isEmpty()) ? pInf.split("/") : new String[0];

        if (tab.length == 2) {
            int subjectId;
            try {
                subjectId = Integer.parseInt(tab[1]);
                SubjectService.deleteSubject(subjectId);
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid subject ID format");
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Subject not found");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid request format");
        }
    }
}
