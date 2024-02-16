package socat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MultiThreadeServer {

    public static void main(String[] args) {
        runServer();
    }

    public static void runServer(){

        try(ServerSocket serverSocket = new ServerSocket(5000);){

            System.out.println("-----------Server Open----------");

            //클라이언트 연결들어오면 스레드 생성
            while(true){
                //연결 들어오면 소켓 생성
                Socket connection = serverSocket.accept();
                System.out.println("Session ON");

                Thread clientThread = new Thread(new ClientHandler(connection));
                clientThread.start();
            }
        }catch(IOException e){
            System.out.println(e);
        }
    }


    static class ClientHandler implements Runnable {
        static List<BufferedWriter> clientWriter = new ArrayList<>();
        private final Socket connection;
        private BufferedReader input;
        private BufferedWriter output;

        public ClientHandler(Socket connection){

            this.connection = connection;
            try{
                input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                output = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));

                clientWriter.add(output);
            }catch (IOException e){
                e.toString();
            }

        }

        @Override
        public void run() {
            try{

                String clientMessage;

                String name = input.readLine();
                System.out.println(name + " 입장 ");
                output.write(name+" 님 환영합니다");
                output.newLine();
                output.flush();
                while((clientMessage = input.readLine()) != null){

                    if(clientMessage.equals("exit")){
                        break;

                    }

                    for(BufferedWriter cl : clientWriter){
                            cl.write(name+">>"+clientMessage);
                            cl.newLine();
                            cl.flush();
                    }

                }

            }catch (IOException e){
                System.out.println(e.toString());
            }finally{
                try {
                    connection.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("remove");
                clientWriter.remove(output);
                System.out.println(clientWriter.size());
            }
        }
    }
}

