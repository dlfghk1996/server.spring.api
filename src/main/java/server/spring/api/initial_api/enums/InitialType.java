package server.spring.api.initial_api.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import server.spring.api.common.enums.BaseEnum;

public enum InitialType implements BaseEnum {
  TEST("TEST", "기본 API 구현과 초기 환경세팅");

  @JsonValue private final String code;
  private final String label;

  InitialType(String code, String label) {
    this.code = code;
    this.label = label;
  }

  public String getCode() {
    return this.code;
  }

  @Override
  public String getName() {
    return this.name();
  }

  @Override
  public String getLabel() {
    return this.label;
  }
}
