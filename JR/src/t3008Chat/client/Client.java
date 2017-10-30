package t3008Chat.client;

import java.io.IOException;
import java.net.Socket;

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
    	
    	@Override
        public void run() {
            try {
                Socket socket = new Socket(getServerAddress(), getServerPort());
                connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();
                
            } catch (IOException e) {
                ConsoleHelper.writeMessage("IOException!");
                notifyConnectionStatusChanged(false);
                
            } catch (ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Class not found exception.");
                notifyConnectionStatusChanged(false);
            }
        }

        protected void processIncomingMessage(String message){
            ConsoleHelper.writeMessage(message);
        }

        //massage that user connected
        protected void informAboutAddingNewUser(String userName){
            ConsoleHelper.writeMessage("User " + userName + " connected.");
        }

      //massage that user disconnected
        protected void informAboutDeletingNewUser(String userName){
            ConsoleHelper.writeMessage("User " + userName + " disconnected.");
        }

        //client connected and have to awake main Client thread
        protected void notifyConnectionStatusChanged(boolean clientConnected){
            synchronized (Client.this){
                Client.this.clientConnected = clientConnected;
                Client.this.notify();
            }
        }
        
        protected void clientHandshake() throws IOException, ClassNotFoundException{
            Message message =null;
            while(!clientConnected){
                try {
                    message = connection.receive();
                } catch (ClassNotFoundException e){
                    ConsoleHelper.writeMessage("Class error.");
                }

                if(message.getType() == MessageType.NAME_REQUEST){
                    connection.send(new Message(MessageType.USER_NAME, getUserName()));
                } else if(message.getType() == MessageType.NAME_ACCEPTED){
                    notifyConnectionStatusChanged(true);
                } else{
                    throw new IOException("Unexpected MessageType");
                }
            }
        }

        protected void clientMainLoop() throws IOException, ClassNotFoundException{
            Message message = null;

            while(true){
                try {
                    message = connection.receive();
                } catch (IOException e){
                    ConsoleHelper.writeMessage("Message error.");
                    break;
                }

                if(message.getType() == MessageType.TEXT){
                    processIncomingMessage(message.getData());
                } else if(message.getType() == MessageType.USER_ADDED){
                    informAboutAddingNewUser(message.getData());
                } else if(message.getType() == MessageType.USER_REMOVED){
                    informAboutDeletingNewUser(message.getData());
                } else{
                    throw new IOException("Unexpected MessageType");
                }
            }
        }
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
