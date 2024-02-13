package socat;

import java.io.IOException;
import java.net.ServerSocket;

public class LookForServerPorts {

    public static void main(String[] args) {
        ServerSocket theServer = null;
        for (int i = 2024; i < 65535; i++) {
            try {
                theServer = new ServerSocket(i);
                System.out.println(i + "번째 포트는 사용가능");
                theServer.close();
            } catch (IOException e) {
                System.out.println(i + "번째 포트는 사용중");
            }
        }
    }
}
