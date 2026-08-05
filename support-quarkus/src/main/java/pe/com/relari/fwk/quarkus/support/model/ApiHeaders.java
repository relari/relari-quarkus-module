package pe.com.relari.fwk.quarkus.support.model;

import jakarta.ws.rs.HeaderParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiHeaders {

    public static final String HEADER_REQUEST_ID = "Request-Id";
    public static final String HEADER_USER_ID = "user-id";
    public static final String HEADER_SESSION_ID = "session-id";
    public static final String HEADER_X_FORWARDED_FOR = "x-forwarded-for";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String HEADER_APP_CODE = "app-code";

    @HeaderParam(HEADER_REQUEST_ID)
    private String requestId;
    @HeaderParam(HEADER_USER_ID)
    private String userId;
    @HeaderParam(HEADER_SESSION_ID)
    private String sessionId;
    @HeaderParam(HEADER_X_FORWARDED_FOR)
    private String xForwardedFor;
    @HeaderParam(HEADER_AUTHORIZATION)
    private String authorization;
    @HeaderParam(HEADER_APP_CODE)
    private String appCode;

}
