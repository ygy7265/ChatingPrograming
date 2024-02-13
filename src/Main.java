import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.attribute.AclEntryPermission;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static int bufferSize = 80;
    static byte buffer3[] = new byte[bufferSize];
    static int size = 0;
    static byte[] b = new byte[(127-31)*2]; //바이트 기억공간 할당하기
    public static void main(String[] args) throws IOException {
        //byte 사용법
        for(int i=32; i<127; i++){
            System.out.write(i);

            if(i%8 == 7)
                System.out.write('\n');
            else
                System.out.write('\t');
        }

        System.out.write('\n');
        System.out.println(" byte 사용법 ------------------------------------");
        //byte[] 사용법

        int index=0;
        for(int i=32; i<127; i++){
            b[index++] = (byte) i;

            if(i%8 == 7 ){
                b[index++] = (byte) '\n';
            }
            else{
                b[index++] = (byte) '\t';
            }


        }
        b[index++] = (byte) '\n';
        System.out.write(b,32,10);
        //System.out.write(b);

        System.out.println(" byte[] 사용법 ------------------------------------");

        //버퍼 사용법
        byte[] buffer;
        String s = "Hello World";

        for(int i=0; i<args.length; i++){
            buffer = args[i].getBytes(); // 가져온 문자열 바이트로 변환
            System.out.write(buffer);
            System.out.write('\n');
        }

        System.out.println(" 버퍼 사용법------------------------------------");
        
        //read 사용법
//        int data;
//        while((data = System.in.read()) >= 0)
//            System.out.write(data);

//        System.out.println(" read------------------------------------");
//
//        //read[] 사용법
//        byte[] buffer2 = new byte[80];
//
//        int numberRead;
//        while((numberRead = System.in.read(buffer2)) >= 0)
//            System.out.write(buffer2,0,numberRead);
//
//        //readCharactersOffset
//
//
//        int dataRead;
//
//
//
//
//        while((dataRead = System.in.read(buffer3,size,bufferSize-size)) >=0 ){
//            size += dataRead;
//            System.out.write(buffer3,0,size);
//
//            if(dataRead == -1){
//                break;
//            }
//
//            if(size == bufferSize){
//                increaseBufferSize();
//            }
//
//
//        }
        System.out.println("readcharacters------------------------------------");

        byte[] data = new byte[System.in.available()];
        int available = System.in.available();
        //System.out.write(available);
        System.out.println(available);

        URL u = new URL("http://www.ssc.ac.kr/");
        InputStream in = u.openStream();

        System.out.println("available------------------------------------");

        //파일이있으면 파일덮어쓰기 없으면 생성 기존에있는 데이터는 다사라지고 새로운 데이터만 남음
        FileOutputStream fout = new FileOutputStream("example.txt");
        buffer = "동해물과 백두산이 마르고 닳도록".getBytes();
        System.out.write(buffer);
        fout.write(buffer,0,buffer.length);
        fout.close();
        System.out.println("FileOutputStream------------------------------------");

    }
//MainMethod End






    //버퍼크기 자동적으로 늘리게하기
    static void increaseBufferSize(){
        bufferSize += 80;
        byte[] newBuffer = new byte[bufferSize];

        System.arraycopy(buffer3,0,newBuffer,0,size); //복사할때 사용 (복사하고자하는소스,어느인덱스에서부터읽어올지 처음이면 0,복사할소스,어느부분부터 복사할껀지,복사할 소스의 사이즈)
        buffer3 = newBuffer;
    }


}