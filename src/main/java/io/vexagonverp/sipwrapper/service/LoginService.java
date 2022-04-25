package io.vexagonverp.sipwrapper.service;

import com.ceridwen.circulation.SIP.messages.Login;
import com.ceridwen.circulation.SIP.messages.Message;
import com.ceridwen.circulation.SIP.transport.SocketConnection;

public interface LoginService {
    Message Login(Login login, SocketConnection connection);
}
