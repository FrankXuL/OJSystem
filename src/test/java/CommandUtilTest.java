import Compile.CommandUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @title: CommandUtilTest
 * @Author Xu
 * @Date: 25/10/2022 上午 11:31
 * @Version 1.0
 */
public class CommandUtilTest {
    @Before
    public void before() {
        System.out.println("CommandUtil类测试开始");
    }

    @After
    public void after() {
        System.out.println("CommandUtil类测试结束");
    }

    @Test
    public void TestCommand(){
        CommandUtil.run("javac", "stdout.txt", "stderr.txt");
    }
}