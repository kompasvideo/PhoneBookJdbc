package ru.andreybaryshnikov.services;

import ru.andreybaryshnikov.dao.PhonebookDAO;
import ru.andreybaryshnikov.models.PhoneBook;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PhoneBookServiceImpl implements PhoneBookService{
    private PhonebookDAO phoneBookDAO;

    public PhoneBookServiceImpl(PhonebookDAO phoneBookDAO) {
        this.phoneBookDAO = phoneBookDAO;
    }

    @Override
    public PhoneBook getPhoneBook(int id) {
        return phoneBookDAO.getPhoneBook(id);
    }
    @Override
    public List<PhoneBook> getPhoneBooks() {
        return phoneBookDAO.getPhoneBooks();
    }
    @Override
    public void editRecordToPhoneBooks(PhoneBook newPhoneBook){
        phoneBookDAO.editRecordToPhoneBooks(newPhoneBook);
    }
    @Override
    public void deleteRecordToPhoneBooks(int id){
        phoneBookDAO.deleteRecordToPhoneBooks(id);
    }
    @Override
    public PhoneBook getNewPhoneBook() {
        return phoneBookDAO.getNewPhoneBook();
    }
    @Override
    public void addRecordToPhoneBooks(PhoneBook newPhoneBook) {
        phoneBookDAO.addRecordToPhoneBooks(newPhoneBook);
    }
}
