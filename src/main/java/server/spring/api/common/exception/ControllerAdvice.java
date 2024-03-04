package server.spring.api.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import server.spring.api.common.dto.Response;
import server.spring.api.common.enums.ResponseCode;

@Slf4j
@RestControllerAdvice(basePackages = "server.spring.setting")
public class ControllerAdvice {

  @ExceptionHandler(CustomException.class)
  public Response<Object> handleCustom(CustomException ce) {
    Response<Object> response = new Response<>(null, ce.getResponseCode());
    if (ce.getExtra() != null) {
      response.setData(ce.getExtra());
    }

    if (ce.getResponseCode() == ResponseCode.ERROR) {
      log.error("err: ", ce);
    } else {
      log.info("err: {}", ce.getResponseCode().getLabel());
    }

    if (ce.getOverwriteMessage() != null) {
      response.setMessage(ce.getOverwriteMessage());
    }

    return response;
  }
}
