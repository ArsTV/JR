package t3008Chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by DELL on 9/19/2017.
 */
public class Server {

    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void main(String[] args){
        ConsoleHelper.writeMessage("Type socket port.");
        try (ServerSocket serverSocket = new ServerSocket(ConsoleHelper.readInt());){
            ConsoleHelper.writeMessage("Server started.");
            for(;;){
                new Handler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Server error.");

        }
    }


    public static void sendBroadcastMessage(Message message){
        for (Connection connection: connectionMap.values()) {
            try {
                connection.send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Can't send the message");
            }
        }
    }


    private static class Handler extends Thread{
        private Socket socket;

        Handler(Socket socket){
            this.socket = socket;
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            for (; ; ) {
                connection.send(new Message(MessageType.NAME_REQUEST));

                Message message = connection.receive();

                if (message.getType().equals(MessageType.USER_NAME)) {
                    if (!message.getData().isEmpty() && !connectionMap.containsKey(message.getData())) {
                        connectionMap.put(message.getData(), connection);
                        connection.send(new Message(MessageType.NAME_ACCEPTED));
                        return message.getData();
                    }
                }
            }
        }
    }

}
