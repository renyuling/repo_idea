import java.io.File;

public class FilePathTest {
    public static void main(String[] args) {
        File file = new File("E:\\Javasoftware\\MySql安装");
        System.out.println(file.getParentFile());
    }
}
