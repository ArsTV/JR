package t3008Chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by DELL on 9/19/2017.
 */
public class Server {

    public static void main(String[] args){
        ConsoleHelper.writeMessage("Type socket port.");
        try (ServerSocket serverSocket = new ServerSocket(ConsoleHelper.readInt());){
            ConsoleHelper.writeMessage("Server started.");
            for(;;){
                new Handler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Srver error.");

        }
    }

    private static class Handler extends Thread{
        private Socket socket;

        Handler(Socket socket){
            this.socket = socket;
        }

    }

}
