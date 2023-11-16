package kr.co.project.f1korea.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.co.project.f1korea.dto.UserRequest;
import kr.co.project.f1korea.dto.UserResponse;
import kr.co.project.f1korea.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/api/join")
    public ResponseEntity<UserResponse> join(@RequestBody UserRequest request){
        UserResponse response = userService.join(request);
        return ResponseEntity.ok().body(response);
    }
    @PostMapping("/api/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserRequest request, HttpServletRequest httpServletRequest){
        UserResponse response = userService.login(request);
        HttpSession session = httpServletRequest.getSession(true);
        //로그인 실패한 경우
        if(!response.isSuccess()){
            return ResponseEntity.ok().body(response);
        }
        //로그인 성공
        session.setAttribute("userId",response.getUser().getEmail());
        return ResponseEntity.ok().body(response);
    }
}
