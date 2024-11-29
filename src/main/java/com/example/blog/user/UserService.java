package com.example.blog.user;

import com.example.blog._core.error.ex.Exception401;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepo;

    public User findById(UserRequest.LoginDTO loginDTO) {

        System.out.println(loginDTO.getPassword());

        User user = userRepo.findById(loginDTO.getUsername());

        if (!user.getPassword().equals(loginDTO.getPassword())) {
            throw new Exception401("아이디나 비밀번호가 일치하지 않습니다.");
        }

        return user;
    }
}
