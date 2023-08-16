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
    private static final String PASSWORD = "andrey1978";
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

    @Autowired
    public PhonebookDAOImpl() {
    }

    @Override
    public PhoneBook getPhoneBook(int id) {
        PhoneBook phoneBook = null;
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM phonebook WHERE phonebook_id = '" + id + "'";
            statement.executeQuery(SQL);
            ResultSet resultSet = statement.getResultSet();
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
            String SQL = "SELECT * FROM phonebook";
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
//        Session session = sessionFactory.getCurrentSession();
//        session.merge(newPhoneBook);
    }

    @Override
    public void deleteRecordToPhoneBooks(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        PhoneBook phoneBook = session.get(PhoneBook.class, id);
//        session.remove(phoneBook);
    }

    @Override
    public PhoneBook getNewPhoneBook() {
        return new PhoneBook();
    }

    @Override
    public void addRecordToPhoneBooks(PhoneBook newPhoneBook) {
//        Session session = sessionFactory.getCurrentSession();
//        session.persist(newPhoneBook);
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

