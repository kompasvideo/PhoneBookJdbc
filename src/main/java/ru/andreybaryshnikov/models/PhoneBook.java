package ru.andreybaryshnikov.models;

import lombok.*;

@Getter
@Setter
@ToString
public class PhoneBook {
    private int phoneBookID;
    // Фамилия
    private String lastName;
    // Имя
    private String firstName;
    // Отчество
    private String threeName;
    // Номер телефона
    private String numberPhone;
    //Адрес
    private String address;
    // Описание
    private String description;

    public PhoneBook() {
        phoneBookID = 0;
        lastName = "";
        firstName = "";
        threeName = "";
        numberPhone = "";
        address = "";
        description = "";
    }

    public PhoneBook(int phoneBookID, String lastName, String firstName, String threeName, String numberPhone, String address, String description) {
        this.phoneBookID = phoneBookID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.threeName = threeName;
        this.numberPhone = numberPhone;
        this.address = address;
        this.description = description;
    }
}
