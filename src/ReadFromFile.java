import java.io.*;

public class ReadFromFile {

    public static void main(String[] args) {
        int byteRead;
        byte[] buffer = new byte[256];
        FileInputStream fin  = null;

        try{
            fin = new FileInputStream("test.txt");

            FileOutputStream fout = new FileOutputStream("num1.txt");
            DataOutputStream dout = new DataOutputStream(fout);

            dout.writeUTF("Hello");
//            dout.writeBoolean(true);
//            dout.writeDouble(0.1);
//            dout.writeInt(3);

            FileInputStream fin1 = new FileInputStream("num1.txt");
            DataInputStream din = new DataInputStream(fin1);


            boolean sdin = din.readBoolean();
            String tdin = din.readUTF();
//            String udin = din.readLine();
            System.out.println("sdin : " + sdin);
//            System.out.println("udin : " + udin);
            System.out.println("tdin : " + tdin);
            while((byteRead = fin.read(buffer)) >= 0){ // 파일에서 메모리에저장
                System.out.write(buffer,0,byteRead); //메모리에서 저장한 것 읽어오기.
            }
        }catch (IOException e){
            System.err.println("스트림으로 부터 데이터 읽을 수 없음");
        }finally {
            try{
                if(fin != null)
                    fin.close();
            }catch (IOException ee){
                System.err.println("에러");
            }
        }
    }
}
