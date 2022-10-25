import Utils.Task;
import enity.Answer;
import enity.Question;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @title: TaskTest
 * @Author Xu
 * @Date: 25/10/2022 下午 5:09
 * @Version 1.0
 */
public class TaskTest {
    @Before
    public void before() {
        System.out.println("Task类测试开始");
    }

    @After
    public void after() {
        System.out.println("Task类测试结束");
    }

    @Test
    public void TaskUtil() {
        Task task = new Task();
        Question question = new Question();
        question.setCode("public class Solution {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(\"hello world\");\n" +
                "    }\n" +
                "}");
        Answer answer = task.compileAndRun(question);
        System.out.println(answer);
    }
}