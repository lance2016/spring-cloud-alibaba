package com.cloud.nacos.consumer.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 读取文件内容
 */
@Slf4j
public class FileUtil {

    /**
     * 读取文件指定行
     */
    public static String readAppointedLineNumber(File sourceFile, int lineNumber)
            throws IOException {
        String str = "";
        try (FileReader in = new FileReader(sourceFile);
             LineNumberReader reader = new LineNumberReader(in)) {
            String s = "";
            if (lineNumber <= 0 || lineNumber > getTotalLines(sourceFile)) {
                throw new RuntimeException("不在文件的行数范围之内");
            }
            int lines = 0;
            while (s != null) {
                lines++;
                s = reader.readLine();
                if ((lines - lineNumber) == 0) {
                    str = s;
                }
            }
            return str;
        }
    }


    /**
     * 读取文件指定行
     */
    public static List<String> getContent(File sourceFile, int startLine, int endLine)
            throws IOException {
        List<String> stringList = new ArrayList<>();
        try (FileReader in = new FileReader(sourceFile);
             LineNumberReader reader = new LineNumberReader(in)) {
            String s = "";
            if (startLine > endLine) {
                throw new RuntimeException("开始行数不能小于结束行数");
            }
            if (endLine <= 0 || endLine > getTotalLines(sourceFile)) {
                throw new RuntimeException("不在文件的行数范围之内");
            }
            int lines = 0;
            while (s != null) {
                lines++;
                s = reader.readLine();
                if (lines >= startLine) {
                    stringList.add(s);
                }
                if ((lines - endLine) == 0) {
                    break;
                }
            }
            return stringList;
        }
    }

    /**
     * 文件内容的总行数
     */
    public static int getTotalLines(File file) {
        int lines = 0;
        try (FileReader in = new FileReader(file);
             LineNumberReader reader = new LineNumberReader(in)) {
            String s = reader.readLine();
            while (s != null) {
                lines++;
                s = reader.readLine();
            }
        } catch (IOException e) {
            log.error("[IO]异常：" + e.getMessage(), e);
        }
        return lines;
    }

    /**
     * 删除文件
     */
    public static boolean deleteFile(String filePath) {
        boolean bool = true;
        File file = new File(filePath);
        if (file.exists()) {
            System.gc();
            bool = file.delete();
        }
        return bool;
    }


    /**
     * 删除文件夹
     */
    public static void deleteDirectory(String filePath) {
        File file = new File(filePath);
        if (file.isFile()) {
            file.delete();//清理文件
        } else {
            File list[] = file.listFiles();
            if (list != null) {
                for (File f : list) {
                    deleteDirectory(f.getPath());
                }
                file.delete();//清理目录
            }
        }
    }

    /**
     * 为文件追加一行数据
     *
     * @param filePath      文件
     * @param appendContent 追加内容
     * @param appendLine    追加在第几行，行号从1开始，如果为1，则第一行追加该内容，原内容放在第二行
     * @return 是否追加成功
     */
    public static boolean appendLine(String filePath, List<String> appendContent, Long appendLine) throws IOException {
        if (appendLine < 1) {
            throw new RuntimeException("appendLine mast grater than 0");
        }
        File testFile = new File(filePath);
        // 创建临时文件
        File outFile = File.createTempFile("fileTmp", ".tmp", testFile.getParentFile());
        //  源文件
        // 源文件输入流
        try (FileInputStream fis = new FileInputStream(testFile); FileOutputStream fos = new FileOutputStream(outFile)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            PrintWriter out = new PrintWriter(fos);
            // 保存一行数据
            String thisLine;
            // jvm 退出 临时文件删除
            outFile.deleteOnExit();
            if (appendLine == 1) {
                appendContent.forEach(out::println);
            }
            long line = 1;
            while ((thisLine = in.readLine()) != null) {
                line++;
                //  当读取到目标行时 写入需要写入的内容
                if (line == appendLine) {
                    appendContent.forEach(out::println);
                }
                // 输出读取到的数据
                out.println(thisLine);
            }
            // 各种关
            out.flush();
            // 删除原始文件
            testFile.delete();
            // 把临时文件改名为原文件名
            return outFile.renameTo(testFile);
        }
    }

    /**
     * 创建一个新文件，如果父目录不存在则将目录也创建
     *
     * @param absolutePath 文件绝对路径
     * @return
     * @throws IOException
     */
    public static boolean createNewFile(String absolutePath) throws IOException {
        File file = new File(absolutePath);
        //判断目标文件所在的目录是否存在
        if (!file.getParentFile().exists()) {
            //如果目标文件所在的目录不存在，则创建父目录
            return file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            return file.createNewFile();
        }
        return true;
    }

    public static void writeFile(String absolutePath,List<String> content) throws IOException {
        File file = new File(absolutePath);
        FileOutputStream fos = new FileOutputStream(file);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        for (int i = 0; i < content.size()-1; i++) {
            bw.write(content.get(i));
            bw.newLine();
        }
        bw.write(content.get(content.size()-1));
        bw.close();
    }


}