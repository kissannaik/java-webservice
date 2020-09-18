package com.kissan.ws.soap.config;

import org.apache.wss4j.common.ext.WSPasswordCallback;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UTPasswordCallback implements CallbackHandler {
    private Map<String, String> userDetails = new HashMap<>();

    public UTPasswordCallback(){
        userDetails.put("kissan", "kissan");
        userDetails.put("tom", "jerry");
    }

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (Callback callback:callbacks) {
            WSPasswordCallback wsPasswordCallback = (WSPasswordCallback)callback;
            String password = userDetails.get(wsPasswordCallback.getIdentifier());
            if(password != null){
                wsPasswordCallback.setPassword(password);
                return;
            }
        }
    }
}
