package bankamat.uz.bankamat.Service;

import bankamat.uz.bankamat.repository.UserRepository;
import bankamat.uz.bankamat.entity.User;
import bankamat.uz.bankamat.payload.UserDto;
import bankamat.uz.bankamat.payload.types.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getList(){
        return userRepository.findAll();
    }

    public ApiResponse getId(Integer id){
        Optional<User> byId = userRepository.findById(id);
        return byId.map(user -> new ApiResponse("mana ol userini", true, user)).orElseGet(() -> new ApiResponse("This id not found", false));
    }

    public ApiResponse addUser(UserDto userDto){
        Optional<User> byRole = userRepository.findByRole(userDto.getRole().getRoleName());
        if (byRole.isPresent())
            return new ApiResponse("bu rolni bu odamga bergansan",false);
        User user = new User();
        user.setFullName(userDto.getFullName());
        user.setRole(userDto.getRole());
        userRepository.save(user);
        return new ApiResponse("Added success",true,user);
    }

    public ApiResponse editUser(Integer id,UserDto userDto) {
        Optional<User> byId = userRepository.findById(id);
        if (!byId.isPresent())
            return new ApiResponse("This id not found",false);
        Optional<User> byRole = userRepository.findByRole(userDto.getRole().getRoleName());
        if (byRole.isPresent())
            return new ApiResponse("bu rolni bu odamga bergansan", false);
        User user = byId.get();
        user.setFullName(userDto.getFullName());
        user.setRole(userDto.getRole());
        userRepository.save(user);
        return new ApiResponse("Edited success", true, user);
    }

    public ApiResponse delete(Integer id){
        Optional<User> byId = userRepository.findById(id);
        if (!byId.isPresent())
            return new ApiResponse("This id not found",false);
        userRepository.deleteById(id);
        return new ApiResponse("Delete success",true);
    }

}
