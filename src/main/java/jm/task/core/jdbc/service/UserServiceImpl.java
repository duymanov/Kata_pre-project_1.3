package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
//import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {


//    UserDao userDao = new UserDaoJDBCImpl();
    UserDao userDao = new UserDaoHibernateImpl();

    public void createUsersTable() {
       userDao.createUsersTable();

      System.out.println("OK1");
   }

    public void dropUsersTable() {
      userDao.dropUsersTable();
       System.out.println("OK2");
    }

    public void saveUser(String name, String lastName, byte age) {
       userDao.saveUser(name,lastName,age);
       System.out.println("OK3");
    }

    public void removeUserById(long id) {
      userDao.removeUserById(id);
       System.out.println("OK4");
    }

    public List<User> getAllUsers() {
     List<User> users = userDao.getAllUsers();
        return users;
    }

    public void cleanUsersTable() {
       System.out.println("OK6");
       userDao.cleanUsersTable();
    }
}
