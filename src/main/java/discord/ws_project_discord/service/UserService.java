package discord.ws_project_discord.service;

import discord.ws_project_discord.DAO.UserDAO;
import discord.ws_project_discord.DTO.UserDTO;
import discord.ws_project_discord.metier.User;
import discord.ws_project_discord.mapper.UserMapper;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

public class UserService {
    public static UserDTO getUserById(int id) {
        User user = UserDAO.find(id);
        if (user == null) {
            throw new NotFoundException("User with ID " + id + " does not exist");
        }
        return UserMapper.toDTO(user);
    }
    public static UserDTO getUserByUsername(String username) {
        User user = UserDAO.findByLogin(username);
        if (user == null) {
            throw new NotFoundException("User with username " + username + " does not exist");
        }
        return UserMapper.toDTO(user);
    }

    public static List<UserDTO> getAllUsers() {
        List<User> users = UserDAO.findAll();
        return users.stream()
                .map(UserMapper::toDTO)
                .toList();
    }

    public static UserDTO createUser(UserDTO userDTO) {
        User user = UserMapper.toEntity(userDTO);
        byte[] password = userDTO.getPwd();
        if (password == null || password.length == 0) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        user.setPwd(UserDAO.hashPassword(password));
        UserDAO.create(user);
        return UserMapper.toDTO(user);
    }

    public static UserDTO updateUser(UserDTO userDTO) {
        if (userDTO.getId() == null || userDTO.getId() <= 0) {
            throw new IllegalArgumentException("User ID must be provided for update");
        }

        User userBDD = UserDAO.find(userDTO.getId());
        if (userBDD == null) {
            throw new NotFoundException("User with ID " + userDTO.getId() + " does not exist");
        }

        if (userDTO.getPwd() != null && userDTO.getPwd().length > 0) {
            userDTO.setPwd(UserDAO.hashPassword(userDTO.getPwd()));
        } else {
            userDTO.setPwd(userBDD.getPwd()); // Keep the existing password if not provided
        }

        if (userDTO.getEmail() == null || userDTO.getEmail().isEmpty()) {
            userDTO.setEmail(userBDD.getEmail()); // Keep the existing email if not provided
        }
        if (userDTO.getLogin() == null || userDTO.getLogin().isEmpty()) {
            userDTO.setLogin(userBDD.getLogin()); // Keep the existing login if not provided
        }
        User newUser = UserMapper.toEntity(userDTO);
        UserDAO.update(newUser);
        return UserMapper.toDTO(newUser);
    }

    public static void deleteUser(int id) {
        UserDAO.delete(id);
    }

    public static boolean userExists(int id) {
        try {
            return UserDAO.find(id) != null;
        } catch (Exception e) {
            return false;
        }
    }
}
