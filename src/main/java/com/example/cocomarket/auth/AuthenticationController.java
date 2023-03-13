package com.example.cocomarket.auth;

import com.example.cocomarket.Entity.User;
import com.example.cocomarket.Repository.AuthorityRepository;
import com.example.cocomarket.Repository.User_Repository;
import com.example.cocomarket.config.EmailSenderService;
import com.example.cocomarket.config.JwtService;
import com.example.cocomarket.token.TokenRepository;
//import lombok.RequiredArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
  @Autowired
  private PasswordEncoder passwordEncoder;
  private static String CodeRecived;
  @Autowired
  private User_Repository UserRepo;
  @Autowired
  private EmailSenderService service;

  private final AuthenticationService serviceAuth;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody User request
  ) {
    return ResponseEntity.ok(serviceAuth.register(request));
  }
  @PostMapping("/authenticate")
  public Authentication authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return serviceAuth.authenticate(request);
  }

  @GetMapping("/SendMailForgetPswd/{mail}")
  //@PreAuthorize("hasAuthority('ADMIN')")
  public String SendMail(@PathVariable String mail){
    this.CodeRecived=getRandomNumberString();
    //  System.out.println("Email lbch nab3ethlou :"+ UserRepo.findByEmail(mail));
//   if (UserRepo.findByEmail(mail) != null){
    service.sendSimpleEmail(mail,"This Is the code :"+ this.CodeRecived,"Security Alert ");
    return "Email receved";
    //}
    // else
    //   return "-______-  ' Email of this account Not Existe '   -_______-";
  }

  @GetMapping("/Verifier/{mail}/{code}/{newPsw}")
  //@PreAuthorize("hasAuthority('ADMIN')")
  public String SendMail(@PathVariable String mail,@PathVariable String code,@PathVariable String newPsw){
    System.out.println("Code Envoyer est ="+this.CodeRecived);
    System.out.println("Code saiser est ="+ code);

    if (this.CodeRecived.compareTo(code) == 0 ){
      User u= UserRepo.findByEmail(mail).get();
      System.out.println("UserName ="+ u.getLast_name()  );

      u.setPassword( passwordEncoder.encode(newPsw));

      UserRepo.save(u);
      this.CodeRecived="No Code";
      return "Code Correct Password has been Update Succeful";
    }
    else
      return "-______-  ' Failed to Update Psw of this account '   -_______-";
  }

  public static String getRandomNumberString() {
    // It will generate 6 digit random Number.
    // from 0 to 999999
    Random rnd = new Random();
    int number = rnd.nextInt(999999);

    // this will convert any number sequence into 6 character.
    return String.format("%06d", number);
  }
    //---------------------------------------
    @Autowired
  private AuthorityRepository AuhtRepo;
  @Autowired
  private TokenRepository tokenRepo;
  @Autowired
  private JwtService jwtService;
  @GetMapping("/GetNbrUserByRole/{role}")
  public int NbrUsers(@PathVariable String role){

    return AuhtRepo.findAllValidTokenByUser(role);
  }

  /*@GetMapping("/GetTokenValide/{}")
  public List<Token> GetTokenV(){
    List<Token> TokenMriglin=tokenRepo.findAllValidToken();
    Date d = new Date(System.currentTimeMillis());
    for (Token T : TokenMriglin){
      if(! T.user.getDate().before(d)){

      }
     //System.out.println("Experation :"+ jwtService.isTokenValid(T.token,T.user));
    }
    return tokenRepo.findAllValidToken();
  }*/


}
