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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

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

  @PostMapping(path = "/register",consumes = {MULTIPART_FORM_DATA_VALUE})
  public ResponseEntity<AuthenticationResponse> register(@RequestParam String request,@RequestParam MultipartFile file) throws IOException {
    return ResponseEntity.ok(serviceAuth.register(request,file));
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
  public List<User> NbrUsers(@PathVariable String role){
    return AuhtRepo.findAllUserByRole(role);
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
  // @Scheduled(cron="*/5 * * * * *")
  //@Scheduled(fixedRate = 30000)
  @PutMapping(value = "/WakeUpAccount")
  public void retrieveAndUpdateStatusContrat(){
    Date d = new Date(System.currentTimeMillis());

    List<User> Users=UserRepo.findAll();
    for ( User u: Users)
      if(u.getSleep_time() !=null) {
        long elapsedms = Math.abs(d.getTime() - u.getSleep_time().getTime());
        long diff = TimeUnit.MINUTES.convert(elapsedms, TimeUnit.MILLISECONDS);
        System.out.println("Diference  :" + diff);
        if (diff >=30 ){
          u.setEnabled(false);
          u.setNbr_tentatives(0);
          u.setSleep_time(null);
          UserRepo.save(u);
        }
      }
    //
  }


}
