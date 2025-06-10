package discord.ws_project_discord.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class AuthInfo {

    Integer userId;

    long expirationDate;
}
