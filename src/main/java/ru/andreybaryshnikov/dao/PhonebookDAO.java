package ru.andreybaryshnikov.dao;

import ru.andreybaryshnikov.models.PhoneBook;
import java.util.List;

public interface PhonebookDAO {
    PhoneBook getPhoneBook(int id);
    List<PhoneBook> getPhoneBooks();
    void editRecordToPhoneBooks(PhoneBook newPhoneBook);
    void deleteRecordToPhoneBooks(int id);
    PhoneBook getNewPhoneBook();
    void addRecordToPhoneBooks(PhoneBook newPhoneBook);
}
