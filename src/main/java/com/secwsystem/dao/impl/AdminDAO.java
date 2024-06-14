package com.secwsystem.dao.impl;

import com.secwsystem.dao.DAOForAccount;
import com.secwsystem.dao.pojo.DBConnection;
import com.secwsystem.dao.pojo.AdminPrivate;
import com.secwsystem.dao.pojo.AdminPublic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 管理员数据访问对象（DAO）的实现类，实现了DAOForAll接口，用于对管理员的公共和私有信息进行CRUD操作。
 * <p>
 * 该类提供了添加、删除、更新和查询管理员的公共和私有信息的方法。
 */
public class AdminDAO implements DAOForAccount<AdminPublic, AdminPrivate> {

    /**
     * 获取所有管理员的私有信息。
     * 通过查询数据库中的admin表，获取所有管理员的aid，并根据aid获取对应的管理员私有信息。
     * 如果数据库连接失败或查询过程中出现异常，将抛出运行时异常。
     *
     * @return 包含所有管理员私有信息的ArrayList，如果数据库连接为空，则返回null。
     */
    public ArrayList<AdminPrivate> getAll() {
        // 获取数据库连接
        Connection conn = DBConnection.getConn();
        // 定义PreparedStatement对象，用于执行SQL查询
        PreparedStatement stmt = null;
        // 定义ResultSet对象，用于存储查询结果
        ResultSet rs = null;
        // 初始化一个ArrayList，用于存储查询到的管理员私有信息
        ArrayList<AdminPrivate> admins = new ArrayList<>();
        // 检查数据库连接是否成功，如果失败则直接返回null
        if (conn == null) {
            return null;
        }
        try {
            // 准备SQL查询语句，查询admin表中的所有aid
            String sql = "SELECT * FROM " + DBConnection.admin;
            stmt = conn.prepareStatement(sql);
            // 执行查询操作
            rs = stmt.executeQuery();
            // 遍历查询结果集
            while (rs.next()) {
                // 根据查询到的aid获取对应的管理员私有信息
                AdminPrivate admin = new AdminPrivate();
                admin.setId(rs.getLong("id"));
                admin.setAid(rs.getString("aid"));
                admin.setAName(rs.getString("a_name"));
                admin.setAPassword(rs.getString("a_password"));
                admins.add(admin);
            }
        } catch (SQLException e) {
            // 如果出现SQL异常，抛出运行时异常
            throw new RuntimeException(e);
        } finally {
            // 关闭数据库连接、PreparedStatement和ResultSet
            DBConnection.closeConn(conn, stmt, rs);
        }
        // 返回包含所有管理员私有信息的ArrayList
        return admins;
    }


    /**
     * 添加管理员的私有信息到数据库。
     * 该方法尝试建立与数据库的连接，并执行插入操作将管理员信息保存到数据库中。
     * 如果数据库连接失败或插入操作遇到异常，方法将不会继续执行。
     *
     * @param admin 待添加的管理员私有信息对象，包含管理员的ID、名称和密码。
     * @return 如果添加操作成功，则返回true；如果数据库连接失败或添加操作未执行，则返回false。
     */
    @Override
    public boolean add(AdminPrivate admin) {
        // 尝试获取与数据库的连接
        Connection conn = DBConnection.getConn();
        PreparedStatement stmt = null;
        // 如果获取数据库连接失败，则直接返回false
        if (conn == null) {
            return false;
        }
        try {
            // 准备插入数据的SQL语句，包含管理员的ID、名称和密码
            String sql = "INSERT INTO " + DBConnection.admin + "(aid,a_name,a_password) VALUES(?,?,?)";
            stmt = conn.prepareStatement(sql);
            // 设置SQL语句中的参数值，分别为管理员的ID、名称和密码
            stmt.setString(1, admin.getAid());
            stmt.setString(2, admin.getAName());
            stmt.setString(3, admin.getAPassword());
            // 执行更新操作，将管理员信息插入到数据库中
            stmt.executeUpdate();
        } catch (Exception e) {
            // 如果遇到异常，抛出运行时异常，并包含原始异常信息
            throw new RuntimeException(e);
        } finally {
            // 关闭数据库连接和PreparedStatement对象
            DBConnection.closeConn(conn, stmt, null);
        }
        // 如果执行到这里，说明添加操作成功，返回true
        return true;
    }


    /**
     * 删除管理员信息。
     *
     * @param aid 要删除的管理员的ID。
     * @return 如果删除成功，返回true；否则返回false。
     */
    @Override
    public boolean delete(String aid) {
        // 获取与数据库的连接
        Connection conn = DBConnection.getConn();
        PreparedStatement stmt = null;
        // 如果无法获取数据库连接，则直接返回false
        if (conn == null) {
            return false;
        }
        try {
            // 准备SQL语句，用于删除指定ID的管理员信息
            String sql = "DELETE FROM " + DBConnection.admin + " WHERE id=?";
            stmt = conn.prepareStatement(sql);
            // 设置SQL语句中的参数，即待删除管理员的ID
            stmt.setString(1, aid);
            // 执行更新操作，即执行删除操作
            stmt.executeUpdate();
        } catch (SQLException e) {
            // 如果在操作数据库过程中发生异常，则抛出运行时异常
            throw new RuntimeException(e);
        } finally {
            // 关闭数据库连接和PreparedStatement，以释放资源
            DBConnection.closeConn(conn, stmt, null);
        }
        // 如果执行到这里，说明删除操作完成，返回true
        return true;
    }



    /**
     * 更新管理员的公共信息。
     * 该方法尝试连接数据库，并执行更新操作。如果连接失败或更新过程中出现SQL异常，则方法会处理异常并返回false。
     * 成功执行更新操作后，方法会返回true。
     * 注意：此方法的实现目前假设数据库连接和相关SQL语句配置正确且可用。
     *
     * @param admin 待更新的管理员公共信息对象，包含新的名称和ID。
     * @return 如果更新操作成功，则返回true；如果更新操作失败或出现异常，则返回false。
     */
    @Override
    public boolean updatePublic(AdminPublic admin) {
        // 获取数据库连接
        Connection conn = DBConnection.getConn();
        PreparedStatement stmt = null;
        // 如果连接获取失败，则直接返回false
        if (conn == null) {
            return false;
        }
        try {
            // 准备更新SQL语句，设置占位符以防止SQL注入
            String sql = "UPDATE " + DBConnection.admin + " SET a_name=? WHERE aid=?";
            stmt = conn.prepareStatement(sql);
            // 设置SQL语句参数，分别为新的名称和管理员ID
            stmt.setString(1, admin.getAName());
            stmt.setString(2, admin.getAid());
            // 执行更新操作
            stmt.executeUpdate();
        } catch (SQLException e) {
            // 如果出现SQL异常，则抛出运行时异常，并在上层处理
            throw new RuntimeException(e);
        } finally {
            // 关闭数据库连接和PreparedStatement，释放资源
            DBConnection.closeConn(conn, stmt, null);
        }
        // 如果执行到这里，说明更新操作成功，返回true
        return true;
    }


    /**
     * 更新管理员的私有信息。
     * 该方法尝试连接数据库，并执行更新操作。如果数据库连接失败，则不执行更新并返回false。
     * 如果更新操作成功，则返回true。此方法覆盖了超类中的相应方法。
     *
     * @param admin 一个AdminPrivate对象，包含待更新的管理员信息。
     * @return 如果数据库连接成功且更新操作执行，则返回true；否则返回false。
     */
    @Override
    public boolean updatePrivate(AdminPrivate admin) {
        // 获取数据库连接
        Connection conn = DBConnection.getConn();
        PreparedStatement stmt = null;
        // 如果数据库连接失败，返回false
        if (conn == null) {
            return false;
        }
        try {
            // 准备SQL语句，用于更新管理员信息
            String sql = "UPDATE " + DBConnection.admin + " SET a_name=?,a_password=? WHERE aid=?";
            stmt = conn.prepareStatement(sql);
            // 设置SQL语句中的参数值
            stmt.setString(1, admin.getAName());
            stmt.setString(2, admin.getAPassword());
            stmt.setString(3, admin.getAid());
            // 执行更新操作
            stmt.executeUpdate();
        } catch (SQLException e) {
            // 如果出现SQL异常，抛出运行时异常
            throw new RuntimeException(e);
        } finally {
            // 关闭数据库连接和PreparedStatement
            DBConnection.closeConn(conn, stmt, null);
        }
        // 如果执行到这里，说明更新操作完成，返回true
        return true;
    }


    /**
     * 根据管理员ID查询管理员的公共信息。
     * 该方法通过数据库连接获取特定管理员的详细信息。如果数据库连接失败或查询不到对应管理员信息，则返回null。
     *
     * @param aid 管理员的唯一标识符。
     * @return AdminPublic对象，包含管理员的公共信息；如果未找到对应管理员或数据库连接失败，则返回null。
     */
    @Override
    public AdminPublic getPublic(String aid) {
        // 获取数据库连接
        Connection conn = DBConnection.getConn();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        AdminPublic admin = null;

        // 检查数据库连接是否成功
        if (conn == null) {
            return null;
        }
        try {
            // 准备SQL查询语句，动态绑定管理员ID
            String sql = "SELECT * FROM " + DBConnection.admin + " WHERE aid=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, aid);

            // 执行查询并获取结果集
            rs = stmt.executeQuery();

            // 如果查询结果存在，则创建并填充AdminPublic对象
            if (rs.next()) {
                admin = new AdminPublic(rs.getString("aid"), rs.getString("a_name"));
            }
        } catch (SQLException e) {
            // 抛出运行时异常，当遇到SQL异常时
            throw new RuntimeException(e);
        } finally {
            // 关闭数据库连接、PreparedStatement和ResultSet，释放资源
            DBConnection.closeConn(conn, stmt, rs);
        }
        return admin;
    }


    /**
     * 根据管理员ID查询管理员的私有信息。
     * 该方法通过数据库查询来获取特定管理员的详细信息。如果数据库连接失败或查询不到结果，则返回null。
     *
     * @param aid 管理员的唯一标识符。用于在数据库中定位特定管理员的信息。
     * @return AdminPrivate 对象，包含管理员的私有信息。如果查询失败或找不到对应管理员，则返回null。
     */
    @Override
    public AdminPrivate getPrivate(String aid) {
        // 获取数据库连接
        Connection conn = DBConnection.getConn();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        AdminPrivate admin = null;

        // 检查数据库连接是否成功
        if (conn == null) {
            return null;
        }
        try {
            // 准备SQL查询语句，使用占位符来避免SQL注入
            String sql = "SELECT * FROM " + DBConnection.admin + " WHERE aid=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, aid);
            // 执行查询并获取结果集
            rs = stmt.executeQuery();
            // 如果查询结果存在，则创建并填充AdminPrivate对象
            if (rs.next()) {
                admin = new AdminPrivate(rs.getLong("id"), rs.getString("aid"),
                        rs.getString("a_name"), rs.getString("a_password"));
            }
        } catch (SQLException e) {
            // 将SQL异常转换为运行时异常并抛出
            throw new RuntimeException(e);
        } finally {
            // 关闭数据库连接和相关资源
            DBConnection.closeConn(conn, stmt, rs);
        }
        return admin;
    }

}
