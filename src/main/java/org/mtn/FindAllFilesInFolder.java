package org.mtn;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class FindAllFilesInFolder {

    static List<Path> getAllFiles(String rootDirectory) {
        File root = new File(rootDirectory);
        Stack<File> stack = new Stack<>();
        List<Path> fileList = new ArrayList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            File current = stack.pop();
            if (current.isDirectory()) {
                for (File file : current.listFiles()) {
                    stack.push(file);
                }
            } else {
                fileList.add(current.toPath());
            }
        }
        return fileList;
    }


}
