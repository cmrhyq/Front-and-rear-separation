package com.alan.blog.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * <p></p>
 *
 * @author Alan Huang
 * @version v1.0.0
 * @className FileGenerate.java
 * @project blog
 * @package com.alan.blog.utils
 * @date 2021/10/30-12:15
 * @email cmrhyq@163.com
 */
@Slf4j
public class FileGenerate {
    /**
     * 路径
     */
    private static String PATH;
    /**
     * 文件路径+名称
     */
    private static String FILE_NAME_TEMP;

    /**
     * 创建文件
     *
     * @param fileName    文件名
     * @param fileContent 内容
     * @return 创建结果
     */
    public static boolean createFile(String fileName, String fileContent) throws IOException {
        boolean bool = false;
        if (fileName!=null&&fileContent!=null){
            File file = new File("");
            // 获取项目的路径并拼接
            PATH = file.getCanonicalPath() + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "file" + File.separator;
            // 文件路径+名称+文件类型
            FILE_NAME_TEMP = PATH + fileName + ".pem";
            file = new File(FILE_NAME_TEMP);
            try {
                if (!file.exists()) {
                    file.createNewFile();
                    bool = true;
                    log.info("文件创建成功,所在路径为:" + FILE_NAME_TEMP);
                    writeFileContent(FILE_NAME_TEMP, fileContent);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            log.info("文件名或内容为空");
        }
        return bool;
    }

    /**
     * 向文件写入内容
     *
     * @param filePath 路径和名称
     * @param newStr   内容
     * @return 写入结果
     * @throws IOException
     */
    public static boolean writeFileContent(String filePath, String newStr) throws IOException {
        boolean bool = false;
        if (!filePath.isEmpty()&&!newStr.isEmpty()){
            // 新的写入，换行
            String fileIn = newStr + "\r\n";
            String temp = "";

            FileInputStream fis = null;
            InputStreamReader isr = null;
            BufferedReader br = null;
            FileOutputStream fos = null;
            PrintWriter pw = null;
            try {
                // 文件路径
                File file = new File(filePath);
                // 将文件读入输入流
                fis = new FileInputStream(file);
                isr = new InputStreamReader(fis);
                br = new BufferedReader(isr);
                StringBuffer sb = new StringBuffer();
                // 文件原有内容
                for (int i = 0; (temp = br.readLine()) != null; i++) {
                    sb.append(temp);
                    sb = sb.append(System.getProperty("line.separator"));
                }
                sb.append(fileIn);
                fos = new FileOutputStream(file);
                pw = new PrintWriter(fos);
                pw.write(sb.toString().toCharArray());
                pw.flush();
                bool = true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //不要忘记关闭
                if (pw != null) {
                    pw.close();
                }
                if (fos != null) {
                    fos.close();
                }
                if (br != null) {
                    br.close();
                }
                if (isr != null) {
                    isr.close();
                }
                if (fis != null) {
                    fis.close();
                }
            }
        } else {
            log.info("文件路径或内容为空");
        }
        return bool;
    }

    /**
     * 删除文件
     *
     * @param fileName 文件名
     * @return 删除结果
     */
    public static boolean delFile(String fileName) throws IOException {
        boolean bool = false;
        if (fileName != null) {
            File file = new File("");
            PATH = file.getCanonicalPath() + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "file" + File.separator;
            FILE_NAME_TEMP = PATH + fileName + ".pem";
            file = new File(FILE_NAME_TEMP);
            try {
                if (file.exists()) {
                    file.delete();
                    bool = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            log.info("文件名为空");
        }
        return bool;
    }

    public static void main(String[] args) throws IOException {
        log.info("生成结果:" + createFile("SystemKey", "s"));
//        log.info("删除结果:"+delFile("SystemKey"));
    }
}
