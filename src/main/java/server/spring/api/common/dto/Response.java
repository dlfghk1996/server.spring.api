package server.spring.api.common.dto;


import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import server.spring.api.common.enums.ResponseCode;

/**
 * API 호출 응답 클래스
 *
 * @param <T>
 */
@Data
@NoArgsConstructor
public class Response<T> {
//  @Schema(description = "데이터")
  T data;

 // @Schema(description = "상태코드")
  int status;

 // @Schema(description = "메시지")
  String message;

  public Response(T data, int status, String message) {
    this.data = data;
    this.status = status;
    this.message = message;
  }

  public Response(T data, ResponseCode responseCode) {
    this.data = data;
    this.status = responseCode.getCode();
    this.message = responseCode.getLabel();
  }

  public Response(T data, int status) {
    this.data = data;
    this.status = status;
    this.message = "";
  }

  public Response(T data, int status, Exception e) {
    this.data = data;
    this.status = status;
    this.message = e.getClass() + " " + e.getMessage();
  }

  public Response(T data) {
    this.data = data;
    this.status = ResponseCode.OK.getCode();
    this.message = ResponseCode.OK.getLabel();
  }

  public Response(List<T> all, ResponseCode ok) {
    this.data = data;
    this.status = ResponseCode.OK.getCode();
    this.message = ResponseCode.OK.getLabel();
  }


//
//  @Data
//  @EqualsAndHashCode(callSuper = false)
//  public static class PagedResponse<T> extends TotalPagingModel {
//
//    List<T> data;
//    int status;
//    String message;
//
//    public PagedResponse(Page page) {
//      super(page);
//      this.data = page.getContent();
//      this.status = ResponseCode.OK.getCode();
//      this.message = ResponseCode.OK.getLabel();
//    }
//
//    public PagedResponse(Page page, ResponseCode responseCode) {
//      super(page);
//      this.data = page.getContent();
//      this.status = responseCode.getCode();
//      this.message = responseCode.getLabel();
//    }
//
//    public PagedResponse(Page page, int status, String error) {
//      super(page);
//      this.data = page.getContent();
//      this.status = status;
//      this.message = error;
//    }
//
//    public PagedResponse(List<T> data, Page page, int status, String error) {
//      super(page);
//      this.data = data;
//      this.status = ResponseCode.OK.getCode();
//      this.message = ResponseCode.OK.getLabel();
//    }
//  }
}
