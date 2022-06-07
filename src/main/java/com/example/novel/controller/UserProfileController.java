package com.example.novel.controller;

import com.example.novel.model.UserProfile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserProfileController {
     private Map<String, UserProfile> userMap;

     @PostConstruct
     public void init() {
         userMap = new HashMap<String, UserProfile>();
         userMap.put("1", new UserProfile("1", "홍길동", "111-1111", "서울시 강남구 대치1동"));
         userMap.put("2", new UserProfile("2", "홍길자", "111-1112", "서울시 강남구 대치2동"));
         userMap.put("3", new UserProfile("3", "홍길순", "111-1113", "서울시 강남구 대치3동"));
     }

     @GetMapping("/user/{id}")
     public UserProfile getUserProfile(@PathVariable("id") String id) {
         return userMap.get(id);
     }
}
