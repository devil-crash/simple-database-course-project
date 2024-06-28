import java.io.*;
import java.util.Scanner;

public class CutFile {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要裁切的文件路径：");
        String filepath = sc.nextLine();
        System.out.println("请输入要裁切文件的名称：");
        String filename = sc.nextLine();
        System.out.println("请输入要裁切的行数：");
        int lineNum=sc.nextInt();
        File file = new File(filepath+"\\"+filename);
        sc.close();
        try {
            Scanner Sc = new Scanner(file);
            File newFile = new File(filepath+"\\test_"+filename);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile, Boolean.parseBoolean("UTF-8"))));
            while(lineNum-- > 0){
                String temp = Sc.nextLine();
                System.out.println(temp);
                writer.write(temp+"\n");
            }
            writer.close();
            System.out.println("Finish!");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
