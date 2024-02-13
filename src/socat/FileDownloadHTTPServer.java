package socat;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FileDownloadHTTPServer {

    public static void main(String[] args) {
        byte[] content;
        byte[] header;
        byte[] data;
        int b, port;
        String encoding = "ASCII";
        String contenttype = "text/plain";

        try {
            if (args[0].endsWith(".html") || args[0].endsWith(".htm")) {
                contenttype = "text/html";
            }

            FileInputStream in = new FileInputStream(args[0]);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            while((b=in.read()) != -1)
                out.write(b);   //배열 버퍼에 저장
                data = out.toByteArray();

            try {
                port = Integer.parseInt(args[1]);
                if (port < 1 || port > 65535)
                    port = 80;
            } catch (Exception e) {
                port = 80;
            }

            if(args.length > 2)
                encoding = args[2];

            ServerSocket server = new ServerSocket(port);

            while(true){
                Socket connection = null;
                FileDownload client = null;

                try{
                    connection = server.accept();

                    client = new FileDownload(connection, data, encoding, contenttype, port);
                    client.start();
                }catch(IOException e){

                }
            }

        } catch (IOException e) {

        }
    }
}


