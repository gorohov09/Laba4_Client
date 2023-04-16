
import java.io.IOException;

public class Main {
    //java.exe Main 192.168.0.103 8000 C:\Users\andre\IdeaProjects\Laba_4_Client\clientJournal.txt
    public static void main(String[] args) throws IOException {
        if(!args[0].matches("\\d{1,3}[.]\\d{1,3}[.]\\d{1,3}[.]\\d{1,3}")){
            System.out.println("Ошибка ввода");
            return;
        }
        String ips[] = args[0].split("[.]");
        for (int i = 0; i < 4; i++) {
            int d = Integer.parseInt(ips[i]);
            if(d > 255){
                System.out.println("Ошибка ввода");
                return;
            }
        }

        if(!JournalWriter.openJournal(args[2])){
            System.out.println("Ошибка вваода");
            return;
        }

        TCPClient tcpClient = new TCPClient(args[0], Integer.parseInt(args[1]));
        Thread th = new Thread(tcpClient);
        th.start();
    }
}
