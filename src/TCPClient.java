import java.io.*;
import java.net.*;

public class TCPClient implements Runnable {
    private String host;
    private int port;
    private PrintWriter serverWriter;
    private BufferedReader serverReader;

    public TCPClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void run(){
        try{
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(this.host, this.port), 10000);

            serverWriter = new PrintWriter(socket.getOutputStream());
            serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            readInstructions();

            while(true) {
                String s = consoleReader.readLine();
                serverWriter.println(s);
                serverWriter.flush();

                if(s.equalsIgnoreCase("stop")){
                    break;
                }

                readFromServer();

            }

            serverWriter.close();
            serverReader.close();
            consoleReader.close();
        } catch (UnknownHostException e) {
            System.err.println("Exception: " + e.toString());
        } catch (IOException e) {
            System.err.println("Exception: " + e.toString());
        }
    }

    public void readFromServer() throws IOException {
        String response = serverReader.readLine();
        if(response.equals("NOTOK")){
            System.out.println("Incorrect string format");
            return;
        } else if (response.equals("OK")) {
            return;
        }
        System.out.println(response);
        JournalWriter.writeToJournal(response);
    }

    public void readInstructions() throws IOException{
        //for(int i = 0; i < 7; i++){
        readFromServer();
        readFromServer();
        //}
    }

}


