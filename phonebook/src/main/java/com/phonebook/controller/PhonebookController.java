package com.phonebook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.phonebook.entity.Phonebook;
import com.phonebook.service.IPhonebookService;

@Controller
@RequestMapping("user")
public class PhonebookController {
	@Autowired
	private IPhonebookService phonebookService;

	@GetMapping("phonebook/{id}")
	public ResponseEntity<Phonebook> getPhonebookById(@PathVariable("id") Integer id) {
		Phonebook phonebook = phonebookService.getPhonebookById(id);
		return new ResponseEntity<Phonebook>(phonebook, HttpStatus.OK);
	}

	@GetMapping("phonebooks")
	public ResponseEntity<List<Phonebook>> getAllPhonebooks() {
		List<Phonebook> list = phonebookService.getAllPhonebooks();
		return new ResponseEntity<List<Phonebook>>(list, HttpStatus.OK);
	}

	@PostMapping("phonebook")
	public ResponseEntity<Void> addPhonebook(@RequestBody Phonebook phonebook, UriComponentsBuilder builder) {
		boolean flag = phonebookService.addPhonebook(phonebook);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/phonebook/{id}").buildAndExpand(phonebook.getPhonebookId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@PutMapping("phonebook")
	public ResponseEntity<Phonebook> updatePhonebook(@RequestBody Phonebook phonebook) {
		phonebookService.updatePhonebook(phonebook);
		return new ResponseEntity<Phonebook>(phonebook, HttpStatus.OK);
	}

	@DeleteMapping("phonebook/{id}")
	public ResponseEntity<Void> deletePhonebook(@PathVariable("id") Integer id) {
		phonebookService.deletePhonebook(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}