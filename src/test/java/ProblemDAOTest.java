import Dao.ProblemDAO;
import enity.Problem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * ProblemDAO Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>10月 24, 2022</pre>
 */
public class ProblemDAOTest {

    @Before
    public void before() throws Exception {
        System.out.println("测试开始");
    }

    @After
    public void after() throws Exception {
        System.out.println("测试结束");
    }

    /**
     * Method: selectAll()
     */
    @Test
    public void testSelectAll() throws Exception {
        ProblemDAO problemDAO = new ProblemDAO();
        List<Problem> problems = problemDAO.selectAll();
        System.out.println(problems);
    }

    /**
     * Method: selectOne(int problemId)
     */
    @Test
    public void testSelectOne() throws Exception {
        ProblemDAO problemDAO = new ProblemDAO();
        Problem problem = problemDAO.selectOne(2);
        System.out.println(problem);
    }

    /**
     * Method: insert(Problem problem)
     */
    @Test
    public void testInsert() throws Exception {
        ProblemDAO problemDAO = new ProblemDAO();
        Problem problem = new Problem();
        // problem.setId();
        problem.setTitle("两数之和");
        problem.setLevel("简单");
        problem.setDescription("给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。\n" +
                "\n" +
                "你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。\n" +
                "\n" +
                "你可以按任意顺序返回答案。\n" +
                "\n" +
                " \n" +
                "\n" +
                "示例 1：\n" +
                "\n" +
                "输入：nums = [2,7,11,15], target = 9\n" +
                "输出：[0,1]\n" +
                "解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。\n" +
                "示例 2：\n" +
                "\n" +
                "输入：nums = [3,2,4], target = 6\n" +
                "输出：[1,2]\n" +
                "示例 3：\n" +
                "\n" +
                "输入：nums = [3,3], target = 6\n" +
                "输出：[0,1]\n" +
                " \n" +
                "\n" +
                "提示：\n" +
                "\n" +
                "2 <= nums.length <= 104\n" +
                "-109 <= nums[i] <= 109\n" +
                "-109 <= target <= 109\n" +
                "只会存在一个有效答案\n" +
                "进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？\n" +
                "\n" +
                "来源：力扣（LeetCode）\n" +
                "链接：https://leetcode-cn.com/problems/two-sum\n" +
                "著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。");
        problem.setTemplateCode("class Solution {\n" +
                "    public int[] twoSum(int[] nums, int target) {\n" +
                "\n" +
                "    }\n" +
                "}");
        problem.setTestCode("    public static void main(String[] args) {\n" +
                "        Solution solution = new Solution();\n" +
                "        // testcase1\n" +
                "        int[] nums = {2,7,11,15};\n" +
                "        int target = 9;\n" +
                "        int[] result = solution.twoSum(nums, target);\n" +
                "        if (result.length == 2 && result[0] == 0 && result[1] == 1) {\n" +
                "            System.out.println(\"testcase1 OK\");\n" +
                "        } else {\n" +
                "            System.out.println(\"testcase1 failed!\");\n" +
                "        }\n" +
                "\n" +
                "        // testcase2\n" +
                "        int[] nums2 = {3,2,4};\n" +
                "        int target2 = 6;\n" +
                "        int[] result2 = solution.twoSum(nums2, target2);\n" +
                "        if (result2.length == 2 && result[0] == 1 && result[1] == 2) {\n" +
                "            System.out.println(\"testcase2 OK\");\n" +
                "        } else {\n" +
                "            System.out.println(\"testcase2 failed!\");\n" +
                "        }\n" +
                "    }\n");
        problemDAO.insert(problem);
        System.out.println("插入成功!");
    }

    /**
     * Method: delete(int id)
     */
    @Test
    public void testDelete() throws Exception {
        ProblemDAO problemDAO = new ProblemDAO();
        problemDAO.delete(1);
    }

} 
