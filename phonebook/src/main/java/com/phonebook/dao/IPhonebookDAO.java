package com.phonebook.dao;
import java.util.List;

import com.phonebook.entity.Phonebook;
public interface IPhonebookDAO {
    List<Phonebook> getAllPhonebooks();
    Phonebook getPhonebookById(int articleId);
    void addPhonebook(Phonebook phonebook);
    void updatePhonebook(Phonebook phonebook);
    void deletePhonebook(int articleId);
    boolean articleExists(String title, String category);
}
 