package socat;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class FileDownload extends Thread{

    private byte[] content;
    private byte[] header;
    private int port;
    Socket connection;
    BufferedOutputStream out;
    BufferedInputStream in;

    public FileDownload(Socket connection,String data,String encoding,String MIMEType, int port) throws UnsupportedEncodingException {
       this(connection,data.getBytes(encoding),encoding,MIMEType,port);
    }

    public FileDownload(Socket connection,byte[] data, String encoding,String MIMEType,int port) throws UnsupportedEncodingException{
        this.connection = connection;
        this.content = data;
        this.port = port;
        String header = "HTTP 1.0 200 OK \r\n" + "Server : OneFile 1.0 \r\n" + "Content-length:" + this.content.length+"\r\n"+ "Content-Type: "+ MIMEType + "\r\n\r\n";
        this.header = header.getBytes("ASCII");
    }

    @Override
    public void run() {
        try{
            out = new BufferedOutputStream(connection.getOutputStream());
            in = new BufferedInputStream(connection.getInputStream());

            StringBuffer request = new StringBuffer(80);
            while(true){
                int c = in.read();
                if(c=='\r' || c=='\n' || c==-1)
                    break;
                request.append((char) c);
            }

            System.out.println(request.toString());
            if(request.toString().indexOf("HTTP/") != -1){
                out.write(this.header);
            }

            out.write(this.content);
            out.flush();
        }catch(IOException e){
            System.out.println(e);
        }finally{
            try {
                if (connection != null)
                    connection.close();
            } catch (IOException e) {
                System.out.println(e);
            }

        }
    }
}
