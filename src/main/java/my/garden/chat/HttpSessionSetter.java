
package my.garden.chat;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

public class HttpSessionSetter extends ServerEndpointConfig.Configurator {

  @Override
  public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
    HttpSession hsession = (HttpSession) request.getHttpSession();
    sec.getUserProperties().put("httpSession", hsession);
  }
}