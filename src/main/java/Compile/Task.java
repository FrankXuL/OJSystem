package Compile;

import Utils.FileUtil;

import java.io.File;

/**
 * @title: Task
 * @Author Xu
 * @Date: 25/10/2022 上午 11:19
 * @Version 1.0
 */
public class Task {
    //目录
    private static final String WORK_DIR = "./tmp/";
    //编译代码的类名
    private static final String CLASS = "Solution";
    //存放编译代码的文件名
    private static final String CODE = WORK_DIR + "Solution.java";
    //存放编译错误信息的文文件名
    private static final String COMPILE_ERROR = WORK_DIR + "compileError.txt";
    //存放标准输出的文件名
    private static final String STDOUT = WORK_DIR + "stdout.txt";
    //存放运行时标准错误的文件名
    private static final String STDERR = WORK_DIR + "Stderr.txt";

    public Answer compileAndRun(Question question) {
        Answer answer = new Answer();
        File workDir = new File(WORK_DIR);
        if (!workDir.exists()) {
            workDir.mkdirs();
        }
        FileUtil.writeFile(CODE, question.getCode());
        //编译
        String compileCmd = String.format("javac -encoding utf8 %s -d %s", CODE, WORK_DIR);
        System.out.println("编译命令: " + compileCmd);
        CommandUtil.run(compileCmd, null, COMPILE_ERROR);
        //编译出错
        String compileError = FileUtil.readFile(COMPILE_ERROR);
        if (!compileError.equals("")) {
            System.out.println("编译出错");
            answer.setError(1);
            answer.setReason(compileError);
            return answer;
        }
        //运行
        String runCmd = String.format("java -classpath %s %s", WORK_DIR, CLASS);
        System.out.println("运行命令: " + runCmd);
        CommandUtil.run(runCmd,STDOUT,STDERR);
        //运行出错
        String runError = FileUtil.readFile(STDERR);
        if(!runError.equals("")){
            System.out.println("运行出错!");
            answer.setError(2);
            answer.setReason(runError);
            return answer;
        }
        answer.setError(0);
        answer.setStdout(FileUtil.readFile(STDOUT));
        return answer;
    }

}