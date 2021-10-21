package com.devsuperior.bds04.components;

import com.devsuperior.bds04.entities.User;
import com.devsuperior.bds04.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenEnhancer implements TokenEnhancer {

  @Autowired
  private UserRepository userRepository;

  @Override
  public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
    User user = userRepository.findByEmail(oAuth2Authentication.getName());
    Map<String, Object> addInfoToken = new HashMap<>();
    addInfoToken.put("userEmail", user.getEmail());
    addInfoToken.put("userId", user.getId());

    DefaultOAuth2AccessToken inforTokenUser = (DefaultOAuth2AccessToken) oAuth2AccessToken;
    inforTokenUser.setAdditionalInformation(addInfoToken);
    return oAuth2AccessToken;
  }
}