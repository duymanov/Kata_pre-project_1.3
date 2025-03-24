package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    void createUsersTable() ;

    void dropUsersTable();

    void saveUser(String name,String lastName,byte age);

    void removeUserById(long id);

    Optional<List<User>> getAllUsers();

    void cleanUsersTable();
}