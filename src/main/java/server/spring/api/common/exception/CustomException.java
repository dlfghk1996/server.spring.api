package server.spring.api.common.exception;


import lombok.Data;
import lombok.EqualsAndHashCode;
import server.spring.api.common.enums.ResponseCode;

@Data
@EqualsAndHashCode(callSuper = false)
/** 기본 business Exception */
public class CustomException extends RuntimeException{
    private int errorCode;
    private ResponseCode responseCode;
    private Object extra;
    private String overwriteMessage;

    public CustomException() {
        super();
    }

    public CustomException(ResponseCode responseCode) {
        super(responseCode.getLabel());
        this.errorCode = responseCode.getCode();
        this.responseCode = responseCode;
    }

    public CustomException(ResponseCode responseCode, String message) {
        super(responseCode.getLabel());
        this.errorCode = responseCode.getCode();
        this.extra = message;
        this.responseCode = responseCode;
    }

    public CustomException(String label) {
        super(label);
        this.errorCode = ResponseCode.UNKNOWN.getCode();
    }

    public CustomException(String message, ResponseCode responseCode) {
        super(message);
        this.errorCode = responseCode.getCode();
        this.responseCode = responseCode;
    }
}
