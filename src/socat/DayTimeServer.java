package socat;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class DayTimeServer {
    public final static int daytimeport = 13;


    public static void main(String[] args) {
        ServerSocket theServer;
        Socket theSocket = null;

        try{
            //13번 포트에서 들어오는 클라이언트의 접속 요청을 기다리는 서버소켓 객체를 생성한다.
            theServer = new ServerSocket(daytimeport);
            while(true){
                try {
                    //클라이언트의 접속요청을 기다리고 클라이언트의 소켓과 연결된 서버 측의 소켓(theSocket) 을 생성한다.
                    theSocket = theServer.accept();

                    //클라이언트에 데이터를 전송할 OutputStream 객체 생성
                    OutputStream os = theSocket.getOutputStream();

                    //클라이언트에 데이터를 전송하는 객체
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));

                    Date now = new Date();

                    //전송
                    writer.write(now.toString() + "Test");
                    writer.newLine();
                    //바이트가 다안채워지면 전송이안되기때문에 강제 전송
                    writer.flush();
                    theSocket.close();
                } catch (IOException e) {
                    System.out.println(e);
                }finally{
                    try{
                        if(theSocket != null) theSocket.close();
                    }catch(IOException e){
                        System.out.println(e);
                    }
                }

            }
        }catch(IOException e){
            System.out.println(e);
        }
    }
}
