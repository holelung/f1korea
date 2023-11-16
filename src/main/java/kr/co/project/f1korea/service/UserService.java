package kr.co.project.f1korea.service;

import kr.co.project.f1korea.domain.User;
import kr.co.project.f1korea.dto.UserRequest;
import kr.co.project.f1korea.dto.UserResponse;
import kr.co.project.f1korea.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public UserResponse join(UserRequest request){
        User checkUser = userRepository.findByEmail(request.getEmail());

        UserResponse response=new UserResponse();
        if(checkUser!=null){ //이미 가입된 이메일일 경우
            response.setSuccess(false);
            response.setMessage("이미 가입된 이메일입니다.");
            return response;
        }
        //가입된 이메일이 없는 경우
        userRepository.save(request.toEntity());
        response.setSuccess(true);
        response.setMessage("회원가입이 완료되었습니다.");
        return response;
    }

    public UserResponse login(UserRequest request){
        UserResponse response = new UserResponse();
        User checkUser = userRepository.findByEmailAndPassword(request.getEmail(), request.getPassword());
        if(checkUser==null){
            response.setSuccess(false);
            response.setMessage("아이디 또는 패스워드가 틀렸습니다.");
            return response;
        }
        response.setSuccess(true);
        response.setMessage("로그인 성공");
        response.setUser(checkUser);
        return response;
    }

}
