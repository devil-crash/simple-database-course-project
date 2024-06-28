import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class ApartFile {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要分割的文件路径：");
        String filepath = sc.nextLine();
        //String filepath="D:\\代码\\IMDb";
        System.out.println("请输入要分割文件的名称：");
        String filename = sc.nextLine();
        System.out.println("请输入要分割的列号：(从第0列开始计数)");
        int rowNum=sc.nextInt();
        sc.nextLine();
        System.out.println("如果可以的话，请输入该列的名称以便于更好地命名文件");
        String rowName=sc.nextLine();
        File file = new File(filepath+"\\"+filename);
        sc.close();
        try {
            Scanner Sc = new Scanner(file);
            File apartFile = new File(filepath+"\\apart_"+rowName+"_"+filename);
            File rowFile = new File(filepath+"\\"+rowName+"_"+filename);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(apartFile, Boolean.parseBoolean("UTF-8"))));
            BufferedWriter writer1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(rowFile, Boolean.parseBoolean("UTF-8"))));
            while(Sc.hasNextLine()){
                String temp = Sc.nextLine();
                System.out.println(temp);
                String[] tempArray = temp.split("\t");
                writer.write(tempArray[0]);
                for(int i=1;i<tempArray.length;i++){
                    if(i != rowNum)
                        writer.write("\t"+tempArray[i]);
                }
                writer.write("\n");
                if(Objects.equals(tempArray[rowNum], "\\N"))
                    continue;;
                String[] ta = tempArray[rowNum].split(",");
                for(int i=0;i<ta.length;i++){
                    writer1.write(tempArray[0]+"\t"+ta[i]+"\n");
                }
            }
            writer.close();
            writer1.close();
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
