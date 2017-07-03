package com.phonebook.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.phonebook.entity.Phonebook;
@Transactional
@Repository
public class PhonebookDAO implements IPhonebookDAO {
	@PersistenceContext	
	private EntityManager entityManager;	
	@Override
	public Phonebook getPhonebookById(int articleId) {
		return entityManager.find(Phonebook.class, articleId);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Phonebook> getAllPhonebooks() {
		String hql = "FROM Phonebook as pb ORDER BY pb.articleId";
		return (List<Phonebook>) entityManager.createQuery(hql).getResultList();
	}	
	@Override
	public void addPhonebook(Phonebook phonebook) {
		entityManager.persist(phonebook);
	}
	@Override
	public void updatePhonebook(Phonebook phonebook) {
		Phonebook pb = getPhonebookById(phonebook.getPhonebookId());
		pb.setName(phonebook.getName());
		pb.setNickname(phonebook.getNickname());
		pb.setPhone(phonebook.getPhone());
		entityManager.flush();
	}
	@Override
	public void deletePhonebook(int articleId) {
		entityManager.remove(getPhonebookById(articleId));
	}
	@Override
	public boolean articleExists(String name, String nickname) {
		String hql = "FROM Phonebook as pb WHERE pb.name = ? and pb.nickname = ?";
		int count = entityManager.createQuery(hql).setParameter(1, name)
		              .setParameter(2, nickname).getResultList().size();
		return count > 0 ? true : false;
	}
}
