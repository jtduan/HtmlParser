package cn.jtduan;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by jtduan on 2016/9/21.
 */
public class Util {
    public static String readFile(String fileName) throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream("src/main/resources/"+fileName), "GBK");
        StringBuffer sbread = new StringBuffer();
        while (isr.ready()) {
            sbread.append((char) isr.read());
        }
        isr.close();
        return sbread.toString();
    }
}
