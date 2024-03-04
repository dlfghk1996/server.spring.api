package server.spring.api.common.enums;


import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static jakarta.servlet.http.HttpServletResponse.SC_NO_CONTENT;
import static jakarta.servlet.http.HttpServletResponse.SC_OK;

public enum ResponseCode implements BaseEnum {

    /**
     * [ 1000 ~ 1999 ] : SUCCESS ***
     */
    OK(0, "success", SC_OK),
    SUCCESS(1000, "success", SC_OK),
    /**
     * [ 2000 ~ 2999 ] : INVALID_REQUEST ***
     */
    INVALID_REQUEST(2000, "잘 못 된 요청입니다.", 2000),
    RESULT_NOT_FOUND(4004, "결과가 없습니다.", SC_NO_CONTENT),

    /**
     * [ 9000 - 9009 ] : ERROR ***
     */
    ERROR(9000, "ERROR", SC_OK),

    ERROR_SQL(9001, "SQL ERROR", SC_OK),

    ERROR_NO_MATCH_HEADER(9003, "잘못된 접근입니다.", SC_OK),

    ERROR_NO_MATCH_REQUEST_PARAM(9004, "일치하는 정보가 없습니다.", SC_OK),

    ERROR_NO_ACCESS_DENIED(9005, "접근 권한이 없습니다.", SC_OK),

    UNKNOWN(9999, "알 수 없는 오류입니다.", SC_INTERNAL_SERVER_ERROR);

    private final int code;
    private final String label;
    private final int httpStatusCode;

    ResponseCode(int code, String label, int httpStatusCode) {
        this.code = code;
        this.label = label;
        this.httpStatusCode = httpStatusCode;
    }

    @Override
    public String getName() {
        return this.name();
    }

    public int getCode() {
        return this.code;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    public int getHttpStatusCode() {
        return this.httpStatusCode;
    }
}
