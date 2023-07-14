package my.garden.chat;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;

import my.garden.service.ChatService;

@ServerEndpoint(value = "/chatcontrol", configurator = HttpSessionSetter.class)
public class WebChat {

  // 웹소켓 Session이 필요함. 이 Session은 http의 session과는 다른 애야. Session이 클라이언트의 정보를 담고있어.
  private static Set<Session> clientSet = Collections.synchronizedSet(new HashSet<Session>());
  // 나중에 동기화 처리를 하기 위해서 synchronizedSet을 사용함(for문이 돌고있는 중엔 add와 remove가 허용되지 않도록 하기 위해.)

  public static Map<String, Session> clients = Collections.synchronizedMap(new HashMap<>());

  @OnMessage
  public void onMessage(String message, Session session) throws Exception {
    /*HttpSession hsession = (HttpSession)ec.getUserProperties().get("httpSession");
    String id = (String)hsession.getAttribute("loginId");
    String name = (String)hsession.getAttribute("loginName"); // 지금 접속한 사람이 누구인지 알 수 있음*/
    String[] arr = message.split(" : ");
    String id = arr[0];
    String msg = arr[1];
    Session client = clients.get(id); // 관리자나 클라이언트 모두 client에 담기게 돼
    Session admin = clients.get("admin123@naver.com");

    if (client != admin) { // 만약 client가 관리자가 아니라면(즉, 소비자가 메세지를 보낸다면)
      admin.getBasicRemote().sendText(id + " : " + msg); // client의 메세지는 관리자에게 전달되어야 하고
    } else if (client == admin) { // 만약 client가 관리자라면(즉, 관리자가 메세지를 보낸다면)
      String cid = arr[2];
      Session toclient = clients.get(cid);
      toclient.getBasicRemote().sendText(msg);
    }
  }

  @OnOpen // 메서드의 이름은 아무렇게나 지어도 되지만 어노테이션 OnOpen은 꼭 붙여줘야 함.
  public void onOpen(Session session, EndpointConfig ec) {
    HttpSession hsession = (HttpSession) ec.getUserProperties().get("httpSession");
    String id = (String) hsession.getAttribute("loginId");
    String name = (String) hsession.getAttribute("loginName");
    System.out.println(name + "가 접속함");
    clients.put(id, session);
    clientSet.add(session);
  }

  @OnClose
  public void onClose(Session session) {
    clientSet.remove(session);
  }

  @OnError
  public void onError(Throwable t) {
    t.printStackTrace();
  }
}
