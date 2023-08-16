package ru.andreybaryshnikov.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.andreybaryshnikov.models.PhoneBook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PhonebookDAOImpl implements PhonebookDAO {
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "andrey";
    private static Connection connection;

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public PhonebookDAOImpl() {
    }

    @Override
    public PhoneBook getPhoneBook(int id) {
        PhoneBook phoneBook = null;
        try {
            PreparedStatement prepStat = connection.prepareStatement(
                "SELECT * FROM phonebook WHERE phonebook_id = ?");
            prepStat.setInt(1, id);
            prepStat.execute();
            ResultSet resultSet = prepStat.getResultSet();
            resultSet.next();
            phoneBook = setPhoneBook(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return phoneBook;
    }

    @Override
    public List<PhoneBook> getPhoneBooks() {
        List<PhoneBook> allPhoneBooks = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM phonebook ORDER BY phonebook_id";
            statement.executeQuery(SQL);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                allPhoneBooks.add(setPhoneBook(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allPhoneBooks;
    }

    @Override
    public void editRecordToPhoneBooks(PhoneBook newPhoneBook) {
        try {
            PreparedStatement prepStat = connection.prepareStatement(
            "UPDATE phonebook SET first_name=?, last_name=?, three_name=?, number_phone=?, address=?, " +
                "description=? WHERE phonebook_id = ?");
            prepStat.setString(1, newPhoneBook.getFirstName());
            prepStat.setString(2, newPhoneBook.getLastName());
            prepStat.setString(3, newPhoneBook.getThreeName());
            prepStat.setString(4, newPhoneBook.getNumberPhone());
            prepStat.setString(5, newPhoneBook.getAddress());
            prepStat.setString(6, newPhoneBook.getDescription());
            prepStat.setInt(7, newPhoneBook.getPhoneBookID());
            prepStat.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteRecordToPhoneBooks(int id) {
        try {
            PreparedStatement prepStat = connection.prepareStatement(
                "DELETE FROM phonebook WHERE phonebook_id = ?");
            prepStat.setInt(1, id);
            prepStat.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PhoneBook getNewPhoneBook() {
        return new PhoneBook();
    }

    @Override
    public void addRecordToPhoneBooks(PhoneBook newPhoneBook) {
        try {
            PreparedStatement prepStat = connection.prepareStatement(
                "INSERT INTO phonebook (first_name, last_name, three_name, number_phone, address, description) " +
                    "VALUES (?,?,?,?,?,?)");
            prepStat.setString(1, newPhoneBook.getFirstName());
            prepStat.setString(2, newPhoneBook.getLastName());
            prepStat.setString(3, newPhoneBook.getThreeName());
            prepStat.setString(4, newPhoneBook.getNumberPhone());
            prepStat.setString(5, newPhoneBook.getAddress());
            prepStat.setString(6, newPhoneBook.getDescription());
            prepStat.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private PhoneBook setPhoneBook(ResultSet resultSet) throws SQLException {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.setPhoneBookID(resultSet.getInt("phonebook_id"));
        phoneBook.setFirstName(resultSet.getString("first_name"));
        phoneBook.setLastName(resultSet.getString("last_name"));
        phoneBook.setThreeName(resultSet.getString("three_name"));
        phoneBook.setNumberPhone(resultSet.getString("number_phone"));
        phoneBook.setAddress(resultSet.getString("address"));
        phoneBook.setDescription(resultSet.getString("description"));
        return phoneBook;
    }
}

