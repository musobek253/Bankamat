package bankamat.uz.bankamat.payload;

import bankamat.uz.bankamat.entity.Role;
import lombok.Data;

@Data
public class UserDto {
    private String fullName;
    private Role role;
}
