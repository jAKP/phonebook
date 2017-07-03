package com.phonebook.service;

import java.util.List;

import com.phonebook.entity.Phonebook;

public interface IPhonebookService {
     List<Phonebook> getAllPhonebooks();
     Phonebook getPhonebookById(int articleId);
     boolean addPhonebook(Phonebook phonebook);
     void updatePhonebook(Phonebook phonebook);
     void deletePhonebook(int articleId);
}
