package com.myapi.service;

import com.myapi.entity.UserSessionPk;
import com.myapi.repository.UserRepository;
import com.myapi.repository.UserSessionRepository;
import com.myapi.util.EncodeUtil;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
@Scope("prototype")
@Getter
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserSessionRepository userSessionRepository;
    EncodeUtil encoder=new EncodeUtil();
    public Map<String,String> register(String email, String fullname,String password){
        Map<String,String> result=new HashMap<>();
        if(userRepository.existsByEmail(email)){
            result.put("status","0");
            result.put("message","email existed");
            return result;
        }else{
            try {
                userRepository.insertNewUser(email,fullname,encoder.getSHA256(password));
            }catch (Exception e) {
                result.put("status","0");
                result.put("message","server errors");
                return result;
            }
            result.put("status","1");
            result.put("message","register successfully");
            return result;
        }
    }
    public Map<String,String> login(String email, String password){
        Map<String,String> result=new HashMap<>();
        if(!userRepository.existsByEmail(email)){
            result.put("status","0");
            result.put("message","user not exist");
            return result;
        }else if(!userRepository.existsByEmailAndPassword(email,encoder.getSHA256(password))){
            result.put("status","0");
            result.put("message","wrong password");
            return result;
        }
        else{
            String sessionid=generateSessionId();
            userSessionRepository.createSession(email,sessionid);
            result.put("status","1");
            result.put("message","login successfully");
            result.put("sessionid",email+"_"+sessionid);
            return result;
        }
    }
    public Map<String,String> forgotPassword(String email){
        Map<String,String> result=new HashMap<>();
        if(!userRepository.existsByEmail(email)){
            result.put("status","0");
            result.put("message","user not exist");
            return result;
        }
        else{

            result.put("status","1");
            result.put("message","new password was sent to your email");
            return result;
        }
    }
    public String generateSessionId(){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
