package Dao;

import Utils.DBUtil;
import enity.Problem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @title: ProblemDAO
 * @Author Xu
 * @Date: 24/10/2022 下午 2:49
 * @Version 1.0
 */
public class ProblemDAO {
    // 获取所有问题的基本信息
    public List<Problem> selectAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "select id,title,level from oj_table";
        List<Problem> problems = new ArrayList<>();
        try {
            connection = DBUtil.getConnection();
            assert connection != null;
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Problem problem = new Problem();
                problem.setId(resultSet.getInt("id"));
                problem.setTitle(resultSet.getString("title"));
                problem.setLevel(resultSet.getString("level"));
                problems.add(problem);
            }
            return problems;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, resultSet);
        }
        return null;
    }

    // 获取指定问题的详细信息
    public Problem selectOne(int problemId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from oj_table where id = ?";
        try {
            connection = DBUtil.getConnection();
            assert connection != null;
            statement = connection.prepareStatement(sql);
            statement.setInt(1, problemId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Problem problem = new Problem();
                problem.setId(resultSet.getInt("id"));
                problem.setTitle(resultSet.getString("title"));
                problem.setLevel(resultSet.getString("level"));
                problem.setDescription(resultSet.getString("description"));
                problem.setTemplateCode(resultSet.getString("templateCode"));
                problem.setTestCode(resultSet.getString("testCode"));
                return problem;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, resultSet);
        }
        return null;
    }

    // 插入题目信息
    public void insert(Problem problem) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            // 1. 和数据库建立连接
            connection = DBUtil.getConnection();
            // 2. 构造 SQL 语句
            String sql = "insert into oj_table values(null, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, problem.getTitle());
            statement.setString(2, problem.getLevel());
            statement.setString(3, problem.getDescription());
            statement.setString(4, problem.getTemplateCode());
            statement.setString(5, problem.getTestCode());
            // 3. 执行 SQL
            int ret = statement.executeUpdate();
            if (ret != 1) {
                System.out.println("题目新增失败!");
            } else {
                System.out.println("题目新增成功!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, null);
        }
    }

    // 删除题目信息
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            // 1. 和数据库建立连接
            connection = DBUtil.getConnection();
            // 2. 拼装 SQL 语句
            String sql = "delete from oj_table where id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            // 3. 执行 SQL
            int ret = statement.executeUpdate();
            if (ret != 1) {
                System.out.println("删除题目失败!");
            } else {
                System.out.println("删除题目成功!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, null);
        }
    }
}