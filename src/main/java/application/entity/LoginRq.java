package application.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class LoginRq {
  private String username;
  private String password;
  private boolean remember;
}
