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

            client = new Socket(InetAddress.getLocalHost(),5000);

            System.out.println("서버연결 성공");


            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            while(true){
                System.out.print("클라이언트에서 보낼 메시지 >> ");
//                System.out.println("1");
//                String message = userInput.readLine();
//
//                output.write(message);
                output.newLine();
                output.flush();

                String serverResponse = input.readLine();

                System.out.println("서버 응답 :" + serverResponse);

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
