package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()){

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users_table " +
                    "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), lastname VARCHAR(255), age INT)");

            System.out.println("Таблица Успешно создана.");

    } catch (SQLException e) {
            System.err.println("Ошибка создания таблицы");
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users_table");
            System.out.println("Таблица users_table удалена упешно");

        } catch (SQLException e) {
            System.err.println("Ошибка удаление таблицы");
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName ,byte age) {
        String sql = "INSERT INTO  users_table (name, lastname, age) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Ошибка добавления пользователя");
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users_table WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1,id);

            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("Пользователь успешно удален");
            }else {
                System.out.println("Удаление пользователя прошло не успешно");
            }
        } catch (SQLException e) {
            System.err.println("Ошибка удаления");
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List <User> users = new ArrayList<>();
        String sql = "SELECT id, name, lastname, age FROM users_table";

        try (Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);) {
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastname");
                byte age = resultSet.getByte("age");

                User user = new User(name,lastName,age);
                users.add(user);
            }


        } catch (SQLException e) {
            System.err.println("Ошибка получения всех записей из таблицы");
            e.printStackTrace();
        }
        System.out.println(users.toString());
        return users;
    }


    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            String sql = "TRUNCATE TABLE users_table";

            statement.executeUpdate(sql);
            System.out.println("Таблица users_table успешно очищена.");

        } catch (SQLException e) {
            System.err.println("Ошибка очистки таблицы");
            e.printStackTrace();
        }

    }
}
