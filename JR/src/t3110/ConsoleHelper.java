package t3110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ConsoleHelper {
    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String temp = reader.readLine();
        reader.close();
        return temp;
    }

    public static int readInt() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int temp = Integer.parseInt(reader.readLine());
        reader.close();
        return temp;
    }


}
