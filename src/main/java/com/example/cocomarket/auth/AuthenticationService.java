package com.example.cocomarket.auth;


import com.example.cocomarket.Entity.Autority;
import com.example.cocomarket.Entity.User;
import com.example.cocomarket.Repository.AuthorityRepository;
import com.example.cocomarket.Repository.User_Repository;
import com.example.cocomarket.config.JwtService;
import com.example.cocomarket.token.Token;
import com.example.cocomarket.token.TokenRepository;
import com.example.cocomarket.token.TokenType;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final User_Repository repository;
    private final AuthorityRepository Auhtropo;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(User user) {

      Set<Autority> auths=  user.getAutority();


   /* var user = User.builder()
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .autority(naa)//autority(na)
        .build();*/
    user.setPassword( passwordEncoder.encode(user.getPassword()));
    var savedUser = repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    saveUserToken(savedUser, jwtToken);
      auths.stream()
              .forEach(obj -> {
                  obj.setUserAuth(user);
                  Auhtropo.save(obj);
              });
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  public Authentication authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );

    System.out.println("Hello 1 !!");
    var user = repository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("I Cant found this User- "));
    System.out.println("Hello 2!!");
   if( ! passwordEncoder.matches(request.getPassword() , user.getPassword()) ){
      System.out.println("Rahi lpassword Ghalta");
      user.setNbr_tentatives(user.getNbr_tentatives()+1);
      if(user.getNbr_tentatives() ==5)
        user.setEnabled(Boolean.TRUE);

      return null;
    }else {
    List<GrantedAuthority> authorities = getAuthorities(user.getAuthFromBase());
    var jwtToken = jwtService.generateToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
     AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
     return new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword(),authorities);}
  }

  private List<GrantedAuthority> getAuthorities(Set<Autority> autoritys) {
    List<GrantedAuthority> list = new ArrayList<>();
    for (Autority auth : autoritys){
      list.add(new SimpleGrantedAuthority(auth.getName()));

    }
    return list;
  }
  private void saveUserToken(User user, String jwtToken) {
    var token = Token.builder()
        .user(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }
}
