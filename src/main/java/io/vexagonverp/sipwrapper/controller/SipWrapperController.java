package io.vexagonverp.sipwrapper.controller;

import com.ceridwen.circulation.SIP.messages.Login;
import com.ceridwen.circulation.SIP.messages.LoginResponse;
import com.ceridwen.circulation.SIP.messages.Message;
import com.ceridwen.circulation.SIP.transport.SocketConnection;
import io.vexagonverp.sipwrapper.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SipWrapperController {
    private static final Logger log = LoggerFactory.getLogger(SipWrapperController.class);
    private final LoginService loginService;

    @Autowired
    public SipWrapperController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/")
    public ResponseEntity<?> testApi() {
        SocketConnection connection;
        connection = new SocketConnection();
        connection.setHost("count.intelligentrfid.com.au");
        connection.setPort(6002);
        connection.setConnectionTimeout(3000);
        connection.setIdleTimeout(30000);
        connection.setRetryAttempts(2);
        connection.setRetryWait(500);
        connection.setStrictChecksumChecking(true);
        Login loginInfo = new Login();
        loginInfo.setLocationCode("Location Institution ID");
        loginInfo.setLoginPassword("LoginPassword");
        loginInfo.setLoginUserId("LoginUserID");
        Message loginResponse = new LoginResponse();
        try {
            connection.connect();
            connection.setStrictChecksumChecking(true);
            loginResponse = loginService.Login(loginInfo, connection);
            log.info(loginResponse.toString());
        } catch (Exception ex) {
            log.error(ex.getMessage());
        } finally {
            connection.disconnect();
        }

        HttpHeaders responseHeaders = new HttpHeaders();
        return ResponseEntity.status(HttpStatus.OK).headers(responseHeaders)
                .body(loginResponse.toString());
    }
}
