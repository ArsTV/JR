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
        
        @Override
        public void run() {
            if(socket !=null && socket.getRemoteSocketAddress() != null){
                ConsoleHelper.writeMessage("New connection established with socket address: " + socket.getRemoteSocketAddress());
            }
            String userName = null;

            try {
                Connection connection = new Connection(socket);

                userName = serverHandshake(connection);

                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                sendListOfUsers(connection, userName);
                serverMainLoop(connection, userName);


            } catch (IOException e ) {
                ConsoleHelper.writeMessage("IOException has occurred: " + e);
            } catch (ClassNotFoundException e){
                ConsoleHelper.writeMessage("Class not found: " + e);
            } finally {
                if (userName != null){
                    connectionMap.remove(userName);
                    sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
                    ConsoleHelper.writeMessage("Connection was closed");
                }
            }
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
        
        //send to the client list other clients who was connected
        private void sendListOfUsers(Connection connection, String userName) throws IOException{
            for(Map.Entry<String, Connection> entry: connectionMap.entrySet()){
                String name = entry.getKey();
                if(!name.equals(userName)){
                    connection.send(new Message(MessageType.USER_ADDED, name));
                }
            }
        }
        
        //server processing of messages
        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException{
            for(;;){
                Message message = connection.receive();
                if(message !=null && message.getType() == MessageType.TEXT){
                    sendBroadcastMessage(new Message(MessageType.TEXT, userName + ": " + message.getData()));
                } else {
                    ConsoleHelper.writeMessage("Text message error!");
                }
            }
        }
    }

}
