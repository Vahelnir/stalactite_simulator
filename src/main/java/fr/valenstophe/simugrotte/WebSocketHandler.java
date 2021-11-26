package fr.valenstophe.simugrotte;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@WebSocket
public class WebSocketHandler {

    // Store sessions if you want to, for example, broadcast a message to all users
    private static final Queue<Session> sessions = new ConcurrentLinkedQueue<>();

    @OnWebSocketConnect
    public void connected(Session session) {
        sessions.add(session);
    }

    @OnWebSocketClose
    public void closed(Session session, int statusCode, String reason) {
        sessions.remove(session);
    }

    @OnWebSocketMessage
    public void message(Session session, String message) throws IOException {
        if (message.equals("step")) {
            ObjectMapper mapper = new ObjectMapper();
            PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
                .build();
            Simulator simulator = Main.getSimulator();
            simulator.tick();
            sessions.stream()
                .forEach(sess -> {
                    try {
                        sess.getRemote().sendString(mapper.writeValueAsString(simulator.getEntities()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        }
        //        session.getRemote().sendString(message); // and send it back
    }
}
