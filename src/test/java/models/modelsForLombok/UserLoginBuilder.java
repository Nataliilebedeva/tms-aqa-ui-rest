package models.modelsForLombok;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class UserLoginBuilder {
    String login;
    String password;
}
