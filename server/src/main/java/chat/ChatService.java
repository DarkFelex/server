package chat;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by nmikutskiy on 24.09.16.
 */
public class ChatService {
    private Set<ChatWebSocket> webSockets;

    public ChatService() {
        this.webSockets = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }

    public void sendMessage(String data) {
        for (ChatWebSocket user : webSockets) {
            try {
                user.sendString(data);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void add(ChatWebSocket webSocket) {
        webSockets.add(webSocket);
        System.out.println("New user in the chat. There are " + String.valueOf(webSockets.size()) + " active users.");

    }

    public void remove(ChatWebSocket webSocket) {
        webSockets.remove(webSocket);
        System.out.println("User left the chat. There are " + String.valueOf(webSockets.size()) + " active users.");
    }

}