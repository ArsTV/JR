package t3008Chat.client;

import java.io.IOException;

import t3008Chat.Connection;
import t3008Chat.ConsoleHelper;
import t3008Chat.Message;
import t3008Chat.MessageType;

/**
 * Created by DELL on 10/29/2017.
 */

public class Client extends Thread{
    protected Connection connection;
    private volatile boolean clientConnected = false;
    

    public static void main(String [] args){
        Client client = new Client();
        client.run();
    }

    @Override
    public void run() {
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();

        try{
            synchronized(this){
                wait();
            }

        }catch (InterruptedException e) {
            ConsoleHelper.writeMessage("Waiting error");
            System.exit(1);
        }

        if(clientConnected){
            ConsoleHelper.writeMessage("Connection established. To exit, type the command 'exit'.");
            while(clientConnected){
                String message = ConsoleHelper.readString();
                if(message.equals("exit")){
                    break;
                } else {
                    if(shouldSendTextFromConsole()){
                        sendTextMessage(message);
                    }

                }
            }
        } else {
            ConsoleHelper.writeMessage("An error occurred while the client was running.");
        }
    }

    public class SocketThread extends Thread{
    }
    
    protected String getServerAddress(){
        ConsoleHelper.writeMessage("Write server adress.");
        return ConsoleHelper.readString();
    }

    protected int getServerPort(){
        ConsoleHelper.writeMessage("Write server port.");
        return ConsoleHelper.readInt();
    }

    protected String getUserName(){
        ConsoleHelper.writeMessage("Write username.");
        return ConsoleHelper.readString();
    }

    //in this version it will return always true
    protected boolean shouldSendTextFromConsole(){
        return true;
    }

    protected SocketThread getSocketThread(){
        return new SocketThread();
    }

    protected void sendTextMessage(String text) {
        try {
            connection.send(new Message(MessageType.TEXT, text));
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Connection problem!");
            clientConnected = false;
        }
    }
}
