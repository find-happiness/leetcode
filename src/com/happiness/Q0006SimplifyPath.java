package com.happiness;

import java.util.Arrays;
import java.util.Stack;

public class Q0006SimplifyPath {
    static final String P_DIR ="..";
    static final String C_DIR = ".";
    static final String SPLIT = "/";
    public String simplifyPath(String path) {

        if (path == null || path.length() == 0) {
            return "";
        }

        String[] strArr = path.split(SPLIT);
        Stack<String> pathStack = new Stack<>();
        for (String dir : strArr) {

            if (dir.equals(P_DIR)) {
                if (pathStack.size() > 0)
                    pathStack.pop();
            }else if (dir.length()>0 && !dir.equals(C_DIR)){
                pathStack.push(dir);
            }

        }

        StringBuffer sb = new StringBuffer();
        sb.append(SPLIT);
        sb.append(String.join(SPLIT,pathStack));
        return sb.toString();
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Q0006SimplifyPath q = new Q0006SimplifyPath();
        String simplifyPath="";
        String path ="/home//foo/";
        for (int i = 0; i < 100000; i++) {
            simplifyPath  = q.simplifyPath(path);
        }

        long end = System.currentTimeMillis();
        System.out.println(" time: " +(end - start)+"  path = " + simplifyPath );
    }
}
