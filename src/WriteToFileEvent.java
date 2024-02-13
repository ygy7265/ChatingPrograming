import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteToFileEvent extends Frame implements ActionListener {

    Label lfile, ldata;
    TextField tfile, tdata;

    Button save;
    String filename, data;

    byte buffer[] = new byte[80];

    public WriteToFileEvent(String str){
        super(str);
        setLayout(new FlowLayout());
        lfile = new Label("파일이름을 입력하세요");
        add(lfile);
        tfile = new TextField(20);
        add(tfile);
        ldata = new Label("저장할 데이터를 입력하세요");
        add(ldata);
        tdata = new TextField(20);
        add(tdata);
        Button save = new Button("저장하기");

        save.addActionListener(this);
        add(save);
        addWindowListener(new WinListener());
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        filename = tfile.getText();
        data = tdata.getText();
        buffer = data.getBytes();

        try{
            FileOutputStream fout = new FileOutputStream(filename);
            fout.write(buffer);
        }catch (IOException ee){
            System.out.print(ee.toString());
        }
    }

    public static void main(String[] args) {
        WriteToFileEvent text = new WriteToFileEvent("파일저장");
        text.setSize(270,150);
        text.show();
    }

}
class WinListener extends WindowAdapter{
    public void windowClosing(WindowEvent we) {
        System.exit(0);
    }
}
