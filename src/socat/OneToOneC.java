package socat;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class OneToOneC {

    public static void main(String[] args) {
        runClient();
    }

    public static void runClient(){
        Socket client = null;

        try{
            System.out.println(InetAddress.getLocalHost());
            client = new Socket(InetAddress.getLocalHost(),5000);



            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            String serverMessage;
            InputThread inTh = new InputThread(input);
            inTh.start();
            while((serverMessage = userInput.readLine()) != null){
                System.out.print("클라이언트에서 보낼 메시지 >> ");

                output.write(serverMessage);
                output.newLine();
                output.flush();
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try {
                client.close();

            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}

class InputThread extends Thread {
    BufferedReader br;

    public InputThread(BufferedReader br){
        this.br = br;
    }

    @Override
    public void run() {
        try {
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.out.print(e);
        }
    }
}
