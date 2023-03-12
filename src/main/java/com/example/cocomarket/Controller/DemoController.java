package com.example.cocomarket.Controller;

import com.example.cocomarket.Entity.User;
import com.example.cocomarket.Repository.User_Repository;
import com.example.cocomarket.config.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/demo-controller")
public class DemoController {




  @Autowired
  private User_Repository UserRepo;


  @GetMapping
  public ResponseEntity<String> sayHello() {
    return ResponseEntity.ok("Hello from secured endpoint");
  }

  @GetMapping("/profile")
  @PreAuthorize("hasAuthority('USER')")
  public String profile(){
    return "You are on PROFILE page";
  }

  @GetMapping("/Dashboard")
  @PreAuthorize("hasAuthority('ADMIN')")
  public String Dashboard(){
    return "You are on Dashboard page";
  }

  @Autowired
  private JwtService jwtUtils;

  @GetMapping("/user")
  public String getUserDetails(HttpServletRequest request) {
    String token = getTokenFromRequest(request);
    String username = jwtUtils.getUsernameFromToken(token);
    // fetch user details using the username
    User user = UserRepo.FoundAcountBYMail(username);
    return user.getEmail();
  }
  private String getTokenFromRequest(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }

}
