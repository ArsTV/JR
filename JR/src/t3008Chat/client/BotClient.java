package t3008Chat.client;

/**
 * Created by DELL on 10/29/2017.
 */
public class BotClient extends Client {

    public static void main(String[] args){
        new BotClient().run();
    }

    public  class BotSocketThread extends SocketThread{

    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return "date_bot_" + (int)(Math.random()*100);
    }
}
