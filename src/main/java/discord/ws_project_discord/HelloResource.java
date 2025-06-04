package discord.ws_project_discord;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import discord.ws_project_discord.DAO.ChannelDAO;
import discord.ws_project_discord.DAO.SubjectDAO;
import discord.ws_project_discord.DTO.ChannelLightDTO;
import discord.ws_project_discord.mapper.ChannelMapper;
import discord.ws_project_discord.mapper.SubjectMapper;
import discord.ws_project_discord.metier.Channel;
import discord.ws_project_discord.metier.Subject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.Produces;

import java.io.IOException;

@WebServlet("/hello-world")
public class HelloResource extends HttpServlet {

    @Produces("text/json")
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request.getMethod());
        System.out.println(request.getPathInfo());

        //test de la création d'un channel depuis un DTO
        ChannelLightDTO dto = new ChannelLightDTO();
        dto.setName("test channel");
        dto.setSubjectId(2);
        Channel chan = ChannelMapper.toEntity(dto);
        ChannelDAO.create(chan);
        //on souhaite mettre à jour le channel qu'on vient de créer depuis un DTO :
        dto.setId(chan.getId()); //on met l'id du channel dans le DTO pour pouvoir le mettre à jour
        dto.setName("test channel updated"); //on change le nom du channel
        Channel chan2 = ChannelDAO.find(chan.getId()); //pour vérifier que le channel a bien été créé
        ChannelMapper.toEntity(dto, chan2); //on met à jour le channel avec les infos du DTO
        try {
            // en théorie : seul le nom est mis à jour. pas le sujet.
            ChannelDAO.update(chan2);

            ChannelDAO.delete(chan2.getId()); //on supprime le channel qu'on vient de créer
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Subject sujet = SubjectDAO.find(2);
        ObjectMapper objectMapper = new ObjectMapper();
        //auto mapper to convert the subject to a DTO

        try {
            response.setContentType("application/json;charset=UTF-8");
            String json = objectMapper.writeValueAsString(SubjectMapper.toDTO(sujet));
            response.getWriter().println(json);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}