package t3008Chat.client;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import t3008Chat.ConsoleHelper;

/**
 * Created by DELL on 10/29/2017.
 */
public class BotClient extends Client {

    public static void main(String[] args){
        new BotClient().run();
    }

    public  class BotSocketThread extends SocketThread{
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Hello chat.I'm time bot. I can understand next commands: "
            		+ "date, day, month, year, time, hours, minutes, seconds.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            if(message != null){
            ConsoleHelper.writeMessage(message);
            SimpleDateFormat simpleDateFormat = null;

            if(message.contains(": ")) {
                String[] messageSplit = message.split(": ");
                String userName = messageSplit[0];
                String textMessage = messageSplit[1];

                if (textMessage.equals("date")) {
                    simpleDateFormat = new SimpleDateFormat("d.MM.YYYY");
                } else if (textMessage.equals("day")) {
                    simpleDateFormat = new SimpleDateFormat("d");
                } else if (textMessage.equals("month")) {
                    simpleDateFormat = new SimpleDateFormat("MMMM");
                } else if (textMessage.equals("year")) {
                    simpleDateFormat = new SimpleDateFormat("YYYY");
                } else if (textMessage.equals("time")) {
                    simpleDateFormat = new SimpleDateFormat("H:mm:ss");
                } else if (textMessage.equals("hours")) {
                    simpleDateFormat = new SimpleDateFormat("H");
                } else if (textMessage.equals("minutes")) {
                    simpleDateFormat = new SimpleDateFormat("m");
                } else if (textMessage.equals("seconds")) {
                    simpleDateFormat = new SimpleDateFormat("s");
                }

                if (simpleDateFormat != null) {
                    sendTextMessage(String.format("Iformation for %s: %s", userName,
                            simpleDateFormat.format(Calendar.getInstance().getTime())));
                }
            }
            }
        }
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    // there is not necessary to send messages from console
    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return "date_bot_" + (int)(Math.random()*100);
    }
}
