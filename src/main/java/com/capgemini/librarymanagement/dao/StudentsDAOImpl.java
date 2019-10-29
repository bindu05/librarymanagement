package com.capgemini.librarymanagement.dao;

import java.util.Date;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.capgemini.librarymanagement.beans.BooksInventory;
import com.capgemini.librarymanagement.beans.BooksRegistration;

@Repository
public class StudentsDAOImpl implements StudentsDAO {

	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

	@Override
	public BooksRegistration requestBook(BooksInventory books, String id) {


		EntityManager entityManager=entityManagerFactory.createEntityManager();
		EntityTransaction transaction=entityManager.getTransaction();
		transaction.begin();

		String viewBookDetails="from BooksInventory where bookId =: bookId";
		Query query=entityManager.createQuery(viewBookDetails);
		query.setParameter("bookId", books.getBookId());

		BooksInventory book=(BooksInventory) query.getSingleResult();

		BooksRegistration reg=new BooksRegistration();
		reg.setBookId(book.getBookId());

		Date date=new Date();
		reg.setRegistrationDate(date);

		Random random=new Random();
		int registrationId=random.nextInt(500);

		reg.setRegistrationId(Integer.toString(registrationId));
		reg.setUserId(id);

		entityManager.persist(reg);
		transaction.commit();
		return reg;
	}//end of requestBook

}
