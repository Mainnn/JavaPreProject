package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static UserService service = new UserServiceImpl();
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        service.createUsersTable();
        List<User> userList = new ArrayList<>();
        userList.add(new User("Tommi","Fommi",(byte) 10));
        userList.add(new User("Pig","Pig",(byte) 10));
        userList.add(new User("Pen","Fommi",(byte) 10));
        userList.add(new User("Ken","Fommi",(byte) 1));
        for (User user:userList) {
            service.saveUser(user.getName(),user.getLastName(),user.getAge());
            System.out.format("User с именем – %s добавлен в базу данных",user.getName());
            System.out.println();
        }
        userList = service.getAllUsers();
        User us = userList.get(1);
        userList.stream()
                .forEachOrdered(x -> System.out.println(x.toString()));
        service.cleanUsersTable();
        service.dropUsersTable();
    }
}
