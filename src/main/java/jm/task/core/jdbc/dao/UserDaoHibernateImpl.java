package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS users_table " +
                    "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), lastname VARCHAR(255), age INT)";
            Query query = session.createSQLQuery(sql);
            query.executeUpdate();
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery("DROP TABLE users_table");
            query.executeUpdate();
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name , String lastName , byte age) {
        try(Session session = Util.getSessionFactory().openSession()){
            User user = new User(name,lastName,age);
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try(Session session = Util.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class , id);
            session.delete(user);
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try(Session session = Util.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            String hql = "FROM User";
            List<User> users = session.createQuery(hql).list();

            transaction.commit();

            return users;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void cleanUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            String sql = "TRUNCATE TABLE users_table";
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        }
    }
}
