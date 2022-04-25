package io.vexagonverp.sipwrapper.service.impl;

import com.ceridwen.circulation.SIP.messages.Login;
import com.ceridwen.circulation.SIP.messages.Message;
import com.ceridwen.circulation.SIP.transport.SocketConnection;
import io.vexagonverp.sipwrapper.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    private static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Override
    public Message Login(Login loginInfo, SocketConnection connection) {
        Message loginResponse = null;
        try {
            loginResponse = connection.send(loginInfo);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {

        }
        return loginResponse;
    }
}
