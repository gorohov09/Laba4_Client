import java.io.*;

public class JournalWriter {
    private static String journalPath;
    private static FileWriter journalWriter;

    public static boolean openJournal(String path){
        try {
            journalPath = path;
            journalWriter = new FileWriter(journalPath, true);
            return true;
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }

    public static void writeToJournal(String s){
        try {
            if(journalWriter != null) {
                journalWriter.write("server: " + s + "\n");
                journalWriter.flush();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void closeJournal(){
        try {
            journalWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}


