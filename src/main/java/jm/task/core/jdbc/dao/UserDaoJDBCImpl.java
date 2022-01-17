package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private PreparedStatement statement;
    Connection conn = Util.conn;
    public UserDaoJDBCImpl()  {
    }

    public void createUsersTable() {
        try {
            statement = conn.prepareStatement("CREATE TABLE IF NOT EXISTS `Users` (\n" +
                    "  `id` MEDIUMINT  NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` varchar(30) NOT NULL,\n" +
                    "  `lastName` varchar(30) NOT NULL,\n" +
                    "  `age` int NOT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ")\n");
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try {
            statement = conn.prepareStatement("DROP TABLE if EXISTS Users");
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            statement = conn.prepareStatement("INSERT INTO  Users (name,lastName,age) values (?,?,?)");
            statement.setString(1,name);
            statement.setString(2,lastName);
            statement.setInt(3,age);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeUserById(long id) {
        try {
            statement = conn.prepareStatement("DELETE from users where id = ?");
            statement.setInt(1, (int) id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        ;
        List<User>list = new ArrayList<>();
            try(Statement st = conn.createStatement();
                ResultSet resultSet = st.executeQuery("SELECT * FROM users");) {
                while (resultSet.next()){
                User user = new User(resultSet.getString(2),resultSet.getString(3),resultSet.getByte(4));
                user.setId((long) resultSet.getInt(1));
                list.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return list;
    }

    public void cleanUsersTable() {
        try {
            statement =conn.prepareStatement("TRUNCATE TABLE Users");
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
