package discord.ws_project_discord.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTOStringPWD extends UserDTO {
    public UserDTOStringPWD(Integer id, String email, String login, String pwd) {
        super(id, email, login, pwd != null ? pwd.getBytes() : null);
    }

    public String getPwdAsString() {
        byte[] pwdBytes = super.getPwd();
        return pwdBytes != null ? new String(pwdBytes) : null;
    }

    public void setPwd(String pwd) {
        super.setPwd(pwd != null ? pwd.getBytes() : null);
    }
}
