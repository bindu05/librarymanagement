package com.capgemini.librarymanagement.dao;

import java.util.Date;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.capgemini.librarymanagement.beans.BooksInventory;
import com.capgemini.librarymanagement.beans.BooksRegistration;

@Repository
public class StudentsDAOImpl implements StudentsDAO {

	
	@Override
	public BooksRegistration requestBook(BooksInventory book, String userId) {
		
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("TestPersistence");
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		EntityTransaction transaction=entityManager.getTransaction();
		
		transaction.begin();
		String viewDetails = "from BooksInventory where bookId =: bookId";
		Query query = entityManager.createQuery(viewDetails);
		query.setParameter("bookId", book.getBookId());
		BooksInventory books = (BooksInventory) query.getSingleResult();
		
		BooksRegistration reg = new BooksRegistration();
		reg.setBookId(books.getBookId());
		
		Date date = new Date();
		reg.setRegistrationDate(date);
		
		Random random = new Random();
		int registrationId = random.nextInt();
		
		reg.setRegistrationId(Integer.toString(registrationId));
		reg.setUserId(userId);
		
		entityManager.persist(reg);
		transaction.commit();
		
		return reg;
	}

}
