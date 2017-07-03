package com.phonebook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phonebook.dao.IPhonebookDAO;
import com.phonebook.entity.Phonebook;
@Service
public class PhonebookService implements IPhonebookService {
	@Autowired
	private IPhonebookDAO phonebookDAO;
	@Override
	public Phonebook getPhonebookById(int articleId) {
		Phonebook obj = phonebookDAO.getPhonebookById(articleId);
		return obj;
	}	
	@Override
	public List<Phonebook> getAllPhonebooks(){
		return phonebookDAO.getAllPhonebooks();
	}
	@Override
	public synchronized boolean addPhonebook(Phonebook phonebook){
       if (phonebookDAO.articleExists(phonebook.getName(), phonebook.getNickname())) {
    	   return false;
       } else {
    	   phonebookDAO.addPhonebook(phonebook);
    	   return true;
       }
	}
	@Override
	public void updatePhonebook(Phonebook phonebook) {
		phonebookDAO.updatePhonebook(phonebook);
	}
	@Override
	public void deletePhonebook(int articleId) {
		phonebookDAO.deletePhonebook(articleId);
	}
}
