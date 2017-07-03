package com.concretepage.client;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.phonebook.entity.Phonebook;

public class RestClientUtil {
    public void getPhonebookByIdDemo() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/user/phonebook/{id}";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Phonebook> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Phonebook.class, 1);
        Phonebook phonebook = responseEntity.getBody();
        System.out.println("Id:"+phonebook.getPhonebookId()+", Title:"+phonebook.getName()
                 +", Category:"+phonebook.getNickname());      
    }
	public void getAllPhonebooksDemo() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/user/phonebooks";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Phonebook[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Phonebook[].class);
        Phonebook[] phonebooks = responseEntity.getBody();
        for(Phonebook phonebook : phonebooks) {
              System.out.println("Id:"+phonebook.getPhonebookId()+", Title:"+phonebook.getName()
                      +", Category: "+phonebook.getNickname());
        }
    }
    public void addPhonebookDemo() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/user/phonebook";
	    Phonebook objPhonebook = new Phonebook();
	    objPhonebook.setName("Spring REST Security using Hibernate");
	    objPhonebook.setNickname("Spring");
        HttpEntity<Phonebook> requestEntity = new HttpEntity<Phonebook>(objPhonebook, headers);
        URI uri = restTemplate.postForLocation(url, requestEntity);
        System.out.println(uri.getPath());    	
    }
    public void updatePhonebookDemo() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/user/phonebook";
	    Phonebook objPhonebook = new Phonebook();
	    objPhonebook.setPhonebookId(1);
	    objPhonebook.setName("Update:Java Concurrency");
	    objPhonebook.setNickname("Java");
        HttpEntity<Phonebook> requestEntity = new HttpEntity<Phonebook>(objPhonebook, headers);
        restTemplate.put(url, requestEntity);
    }
    public void deletePhonebookDemo() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/user/phonebook/{id}";
        HttpEntity<Phonebook> requestEntity = new HttpEntity<Phonebook>(headers);
        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, 4);        
    }
    public static void main(String args[]) {
    	RestClientUtil util = new RestClientUtil();
        //util.getPhonebookByIdDemo();
    	util.getAllPhonebooksDemo();
    	//util.addPhonebookDemo();
    	//util.updatePhonebookDemo();
    	//util.deletePhonebookDemo();
    }    
}
