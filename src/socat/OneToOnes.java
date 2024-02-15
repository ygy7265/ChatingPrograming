package socat;

import javax.naming.ldap.SortKey;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class OneToOnes {
    public static void main(String[] args) {
        runServer();
    }
    public static void runServer(){
        ServerSocket server;
        Socket connection;
        BufferedWriter output;
        try{
            server = new ServerSocket(5000, 100);
            System.out.println("클라이언트 연결 대기중 ..");

            connection = server.accept();


            System.out.println(connection.getInetAddress().getHostAddress()+"클라이언트 연결 성공 !");

            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader input = new BufferedReader(new InputStreamReader(is));

            OutputStream os = connection.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            output = new BufferedWriter(osw);

            BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in));

            while(true){

                String serverResponse = serverInput.readLine();

                output.write("서버에서 보낸 메시지 : " + serverResponse);
                output.newLine();
                output.flush();

                String clientResponse = input.readLine();
                System.out.println("클라이언트로부터 받은 메시지 : " + clientResponse);
            }


        }catch(IOException e){
            System.out.println(e);
        }
    }
}
