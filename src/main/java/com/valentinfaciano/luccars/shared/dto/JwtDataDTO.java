package com.valentinfaciano.luccars.shared.dto;

import io.jsonwebtoken.Claims;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtDataDTO {
  private String token;
  private Claims claims;
}
