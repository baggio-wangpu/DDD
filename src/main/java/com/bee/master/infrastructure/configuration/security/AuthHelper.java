package com.bee.master.infrastructure.configuration.security;

import com.bee.master.infrastructure.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@AllArgsConstructor
public class AuthHelper {

  private final HttpServletRequest httpServletRequest;

  public String getUserId() {
    Object attribute = httpServletRequest.getSession().getAttribute(Constants.HTTP_SESSION_USER_ID_KEY);
    if (null == attribute) {
      return "";
    }
    return attribute.toString();
  }
}
