package com.example.admin.user;


import com.example.admin.user.model.User;
import com.example.admin.user.model.UserDTO;
import com.example.core.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public BaseResponse<String> signup(@RequestBody User user) {

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
            user.setPhone(dto.getPhone());
            userService.save(user);
            return BaseResponse.success("update success");
        } else {
            return BaseResponse.error(12002,"not found");
        }


    }
}
