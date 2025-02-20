package com.example.admin.user;


import com.example.admin.user.model.User;
import com.example.admin.user.model.UserDTO;
import com.example.admin.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/test")
    public BaseResponse<String> test() {
        return BaseResponse.success("test");
    }

    @PostMapping("/signup")
    public BaseResponse<String> signup(@RequestBody UserDTO.signupDTO dto) {

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setPhone(dto.getPhoneNumber());
        user.setNickname(dto.getNickname());
        user.setBirthDate(dto.getBirthDate());
        user.setGender(dto.getGender());

        userService.save(user);

        return BaseResponse.success("signup success");
    }

    @PostMapping("/login")
    public BaseResponse<User> login(@RequestBody UserDTO.loginDTO dto) {

        User user = userService.findByEmail(dto.getEmail());

        if (user == null) {
            return BaseResponse.error(12001,"login fail");
        }
        return BaseResponse.success(user);
    }

    @PostMapping("/update")
    public BaseResponse<String> update(@RequestBody UserDTO.updateDTO dto) {
        User user = userService.findByEmail(dto.getEmail());
        if(user != null) {
            user.setName(dto.getName());
            user.setPassword(dto.getPassword());
            user.setPhone(dto.getPhoneNumber());
            userService.save(user);
            return BaseResponse.success("update success");
        } else {
            return BaseResponse.error(12002,"not found");
        }
    }
}
