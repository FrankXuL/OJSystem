import Utils.FileUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @title: FileUtilTest
 * @Author Xu
 * @Date: 25/10/2022 下午 12:05
 * @Version 1.0
 */
public class FileUtilTest {
    @Before
    public void before() {
        System.out.println("FileUtil类测试开始");
    }

    @After
    public void after() {
        System.out.println("FileUtil类测试结束");
    }

    @Test
    public void TestFileUtil() {
        FileUtil.writeFile("D:/test1.txt", FileUtil.readFile("D:/test.txt"));
    }
}