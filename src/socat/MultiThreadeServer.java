package socat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MultiThreadeServer {
    static List<BufferedWriter> clientWriter = new ArrayList<>();
    public static void main(String[] args) {
        runServer();
    }

    public static void runServer(){

        try{
            //서버 열고 (서버 소켓 생성)
            ServerSocket serverSocket = new ServerSocket(5000);

            //클라이언트 연결들어오면 스레드 생성
            while(true){
                //연결 들어오면 소켓 생성
                Socket connection = serverSocket.accept();

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
                clientWriter.add(writer);
                Thread clientThread = new Thread(new ClientHandler(connection));
                clientThread.start();
            }
        }catch(IOException e){
            System.out.println(e);
        }
    }


    static class ClientHandler implements Runnable {
        private Socket connection;
        private BufferedWriter cl;
        public ClientHandler(Socket connection){
            this.connection = connection;
        }

        @Override
        public void run() {
            try(
                    BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    BufferedWriter output = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()))
            ){

                String clientMessage;

                while((clientMessage = input.readLine()) != null){
                    System.out.println("클라이언트 : "+clientMessage);
                    System.out.println(clientWriter.size());
                for(BufferedWriter cl : clientWriter){
                    cl.write(clientMessage);
                    cl.newLine();
                    cl.flush();
                }

                }

                System.out.print("세션종료");

            }catch (IOException e){
                System.out.println(e.toString());
            }finally{
                clientWriter.remove(cl);
            }
        }
    }
}

