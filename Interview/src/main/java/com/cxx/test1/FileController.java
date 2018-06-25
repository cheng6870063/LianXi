package com.cxx.test1;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

public class FileController {
    public static void main(String[] args){
        try {
            File file1 = new File(".\\");
            System.out.println(file1);
            System.out.println(file1.getAbsolutePath());
            System.out.println(file1.getAbsoluteFile());
            System.out.println(file1.getCanonicalPath());
            //一个点的参数
            File file2 = new File(".");
            System.out.println(file2);
            System.out.println(file2.getAbsolutePath());
            System.out.println(file2.getAbsoluteFile());
            System.out.println(file2.getCanonicalPath());
            //两个点的参数
            File file3 = new File("..");
            System.out.println(file3);
            System.out.println(file3.getAbsolutePath());
            System.out.println(file3.getAbsoluteFile());
            System.out.println(file3.getCanonicalPath());
            //没有参数
            File file4 = new File("");
            System.out.println(file4);
            System.out.println(file4.getAbsolutePath());
            System.out.println(file4.getAbsoluteFile());
            System.out.println(file4.getCanonicalPath());

            File[] files = new File(".").listFiles();
            System.out.println(files.length);

            File[] hiddenFiles1 = new File(".").listFiles(new FileFilter() {
                public boolean accept(File file) {
                    return file.isHidden();
                }
            });
            System.out.println(hiddenFiles1.length);
            File[] hiddenFiles2 = new File(".").listFiles(File::isHidden);
            System.out.println(hiddenFiles2.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
