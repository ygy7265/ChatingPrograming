import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class Inet {

    public static void main(String[] args) {

        try{
            InetAddress adress = InetAddress.getByName("www.naver.com");
            String hostAddress = adress.getHostAddress();
            String hostName = adress.getHostName();
            System.out.println("adrees : " + adress);
            System.out.println("hostName : " + hostName);
            System.out.println("hostAddress : " + hostAddress);

            InetAddress local = InetAddress.getLocalHost();

            String hostAddress1 = local.getHostAddress();
            System.out.println("local hostAddress : " + hostAddress1);

            URL webURL = new URL("http://ssc.ac.kr");
            System.out.println("webURL : " + webURL);
            System.out.println("webURLHost : " + webURL.getHost());
            System.out.println("webURLProtocol : " + webURL.getProtocol());
            System.out.println("webURLPort : " + webURL.getPort());
            System.out.println("webURLPath : " + webURL.getPath());

            InputStream is = webURL.openStream();
            InputStreamReader isr = new InputStreamReader(is);

            BufferedReader br = new BufferedReader(isr);
            String data = br.readLine();
            Object line = webURL.getContent();

            System.out.println("line : " + line.getClass());
            System.out.println("data"+data);

        }catch (UnknownHostException e){
            System.out.println("UnknownHostException :" + e);
        }catch (MalformedURLException e){
            System.out.println("urlException :" + e);
        }catch (IOException e){
            System.out.println("IOException :" + e);
        }
        String[] protocols = {"http", "https", "ftp", "mailto", "telnet", "file", "ldap", "gopher", "jdbc", "rmi", "jndi", "jar", "doc", "netdoc", "nfs", "verbatim", "finger", "daytime", "systemresource"};
        final String host = "www.ssc.ac.kr";
        final String file = "/root/";

        for(String protocol : protocols){
            try{
                URL urlTest = new URL(protocol, host, file);
                System.out.println(host +"는" +protocol +"를 지원 합니다.");
            }catch (MalformedURLException e){
                System.out.println(host +"는" +protocol +"를 지원 안합니다.");
            }
        }
    }
}
