package t3110;

import java.io.*;

import t3110.exception.WrongZipFileException;


/**
 * Created by DELL on 11/1/2017.
 */
public class Archiver {
    public static void main(String[] args) throws IOException, Exception {
    	Operation operation;
        for(;;){
            try{
                operation = askOperation();
                CommandExecutor.execute(operation);

                if(operation == Operation.EXIT)
                    break;

            }catch (WrongZipFileException e){
                ConsoleHelper.writeMessage("You did not select an archive file or selected the wrong file.");
            } catch (Exception e){
                ConsoleHelper.writeMessage("An error has occurred. Check the entered data.");
            }

        }
    }

    //asking about type of operation and return it
    public static Operation askOperation() throws IOException {
        ConsoleHelper.writeMessage("Write operation number.");
        ConsoleHelper.writeMessage(Operation.CREATE.ordinal() +  " - to archive the file.");
        ConsoleHelper.writeMessage(Operation.ADD.ordinal() +     " - add file to the archive.");
        ConsoleHelper.writeMessage(Operation.REMOVE.ordinal() +  " - remove file frome the archive.");
        ConsoleHelper.writeMessage(Operation.EXTRACT.ordinal() + " - extract the archive.");
        ConsoleHelper.writeMessage(Operation.CONTENT.ordinal() + " - show the list of archived files.");
        ConsoleHelper.writeMessage(Operation.EXIT.ordinal() +    " - exit.");

        return Operation.values()[ConsoleHelper.readInt()];
    }
}
