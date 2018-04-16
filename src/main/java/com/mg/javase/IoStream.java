package com.mg.javase;

import java.io.*;

/**
 * 流分为字节流和字符流
 * 1.字节流以字节为基本单位进行操作,默认不使用缓冲区,子节流常用于处理二进制数据,如图片,视频等
 * 2.字符流以字符为进本单位操作,默认使用缓冲区(Writer实现了Flushable接口的flush方法),字节流常用于处理文本文件,支持指定的Unicode编码格式
 */
public class IoStream {
    public static void main(String[] args) throws FileNotFoundException {
//        input();
//        System.out.println(copyFile("F:\\1519625497.46.pdf", "E:\\aaa\\"));
//        System.out.println(copyText("F:\\aaa.txt", "E:\\aaa"));
        System.out.println(deleteFile(new File("E:\\aaa.txt")));

    }

    /**
     * Java 的控制台输入由 System.in 完成。
     */
    public static void input(){
        String str = "";
        // 使用 System.in 创建 BufferedReader
        BufferedReader br = new BufferedReader(new
                InputStreamReader(System.in));
        System.out.println("输入字符, 按下 'q' 键退出。");
        // 读取字符
        do {
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(str);
        } while(!"q".equals(str));
    }

    /**
     * 将源文件复制到目标文件夹或者是重命名文件
     * 使用FileInputStream和FileOutputStream(文件流(字节流))
     * @param sourcePath
     * @param aimDirOrFile
     * @return
     */
    public static String copyFile(String sourcePath, String aimDirOrFile){
        File file = new File(sourcePath);
        File aimDirFile  = new File(aimDirOrFile);
        if (aimDirFile.isDirectory()){
            if(!aimDirOrFile.endsWith(File.separator)){
                aimDirOrFile = aimDirOrFile + File.separator;
            }
            aimDirOrFile = aimDirOrFile + file.getName();
        }
        File aim = new File(aimDirOrFile);
//        OutputStream outputStream = null;
//        InputStream inputStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(aim));
//            inputStream = new FileInputStream(file);
//            outputStream = new FileOutputStream(aim);
            byte[] bytes = new byte[1024];
            int len = 0;
            while((len = bufferedInputStream.read(bytes)) != -1){
                if (len < 1024){
                    System.out.println("此处不能将1024全部复制");
                }
//                fileOutputStream.write(bytes);
//                outputStream.write(bytes, 0 ,len);
                bufferedOutputStream.write(bytes, 0, len);
            }
        } catch (FileNotFoundException e) {
            System.out.println("无此文件");
            return "找不到源文件";
        } catch (IOException e) {
            System.out.println("复制文件失败");
            e.printStackTrace();
            return "复制文件失败";
        } finally {
            try {
                bufferedInputStream.close();
                bufferedOutputStream.close();
            }catch (IOException e){
                System.out.println("关闭资源失败");
                e.printStackTrace();
                return "关闭资源失败";
            }

        }
        return "S";
    }

    /**
     * 使用字符流复制文本文件
     * @param sourcePath
     * @param aimDirOrFile
     * @return
     */
    private static String copyText(String sourcePath, String aimDirOrFile){
        File source = new File(sourcePath);
        File aimDOF = new File(aimDirOrFile);
        if (aimDOF.isDirectory()){
            if (!aimDirOrFile.endsWith(File.separator)){
                aimDirOrFile = aimDirOrFile + File.separator;
            }
            aimDirOrFile = aimDirOrFile + source.getName();
        }
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(source), "UTF-8"));
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(aimDirOrFile)), "UTF-8"));
            char[] chars = new char[1024];
            int len = 0;
            while ((len = reader.read(chars)) != -1){
                writer.write(chars, 0, len);
            }
        } catch (FileNotFoundException e) {
            System.out.println("文件不存在");
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("文件复制失败");
            e.printStackTrace();
        } finally {
            try {
                reader.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "复制成功,新的文件路径为" + aimDirOrFile;
    }

    /**
     * 删除文件或者文件夹及其子文件
     * 通过递归先删除子文件夹下的文件,然后删除当前目录
     * @param file
     * @return
     */
    public static boolean deleteFile(File file) {
        if (!file.exists()){
            System.out.println("删除文件不存在");
            return false;
        }
        if (file.isFile()) {
            file.delete();
            return true;
        }
        boolean flag = true;
        File[] files = file.listFiles();
        for (File file1 : files){
            if (file1.isFile()){
                flag = file1.delete();
                if (!flag){
                    System.out.println(file1.getAbsolutePath() + "删除失败");
                    break;
                }
            } else {
                flag = deleteFile(file1);
                if (!flag){
                    System.out.println(file1.getAbsolutePath() + "删除失败");
                    break;
                }
            }
        }
        //删除当前目录
        if (file.delete()) {
            System.out.println("删除文件" + file.getPath() + "成功！");
            return true;
        } else {
            return false;
        }
    }
}
