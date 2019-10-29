package com.capgemini.librarymanagement.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.librarymanagement.beans.BooksInventory;
import com.capgemini.librarymanagement.beans.BooksRegistration;
import com.capgemini.librarymanagement.beans.BooksTransaction;
import com.capgemini.librarymanagement.beans.Users;

@Repository
public class LibrarianDAOImpl implements LibrarianDAO {

	@Autowired
	private CommonDAO dao;

	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;


	@Override
	public Boolean addBook(BooksInventory books) {

		EntityManager entityManager=entityManagerFactory.createEntityManager();
		EntityTransaction transaction=entityManager.getTransaction();

		boolean isadded = false;
		try {
			transaction.begin();
			entityManager.persist(books);
			transaction.commit();
			isadded = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		entityManager.close();
		return isadded;
	}

	@Override
	public Boolean updateBook(BooksInventory book) {
		
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		EntityTransaction transaction=entityManager.getTransaction();

		try {
			transaction.begin();
			String jpql="UPDATE BooksInventory SET author1=:aut1,author2=:aut2,bookName=:nm,"
					+ "publisher=:pb,yearOfPublication=:yop WHERE bookId=:id";
			Query query=(Query) entityManager.createQuery(jpql);	
			query.setParameter("id",book.getBookId());
			query.setParameter("aut1", book.getAuthor1());
			query.setParameter("aut2", book.getAuthor2());
			query.setParameter("nm", book.getBookName());
			query.setParameter("pb", book.getPublisher());
			query.setParameter("yop", book.getYearOfPublication());

			int res=query.executeUpdate();
			transaction.commit();
			if(res>0) {
				entityManager.close();

				return true;
			}else {
			}		
		}catch(Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public Boolean deleteBook(String bookId) {
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			BooksInventory book = null;
			book = entityManager.find(BooksInventory.class, bookId);
			if (book == null) {
				return false;
			} else {
				transaction.begin();
				entityManager.remove(book);
				transaction.commit();

			}
		} catch (Exception e) {
			transaction.rollback();
			return false;
		}
		entityManager.close();
		return true;
	}


	@Override
	public List<BooksRegistration> showAllRequestedBooksInfo() {
		
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		EntityTransaction transaction=entityManager.getTransaction();
		List<BooksRegistration> arraylist=new ArrayList<BooksRegistration>();
		try {
			String jpql="from BooksRegistration";
			Query query=(Query) entityManager.createQuery(jpql);
			List<BooksRegistration> list=query.getResultList();
			for(BooksRegistration book:list) {
				arraylist.add(book);
			}
		} catch (Exception e) {
			return arraylist;
		}
		return arraylist;
	}

	@Override
	public BooksTransaction acceptRequest(String registrationId) {

		BooksTransaction trans = new BooksTransaction();
		try {
			
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();

			String viewRegistrationDetails = "from BooksRegistration where registrationId = : registrationId";
			Query query = entityManager.createQuery(viewRegistrationDetails);
			query.setParameter("registrationId", registrationId);

			BooksRegistration bookDetails = (BooksRegistration) query.getSingleResult();

			Random random = new Random();
			int transactionid = random.nextInt();

			trans.setRegistrationId(bookDetails.getRegistrationId());
			trans.setTransactionId(Integer.toString(transactionid));
			trans.setIssueDate(bookDetails.getRegistrationDate());
			trans.setFine(0);

			Calendar cal = Calendar.getInstance();
			cal.setTime(bookDetails.getRegistrationDate());
			cal.add(Calendar.DATE, 14);
			trans.setReturnDate(cal.getTime());

			entityManager.persist(trans);
			transaction.commit();
		} catch (Exception e) {
		}
		return trans;
	}

	@Override
	public List<BooksInventory> showAllBooks() {
		
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		EntityTransaction transaction=entityManager.getTransaction();
		List<BooksInventory> arraylist=new ArrayList<BooksInventory>();
		try {
			String jpql="from BooksInventory";
			Query query=(Query) entityManager.createQuery(jpql);
			List<BooksInventory> list=query.getResultList();
			for(BooksInventory book:list) {
				arraylist.add(book);
			}
		} catch (Exception e) {
			return arraylist;
		}
		return arraylist;
	}


	@Override
	public BooksTransaction addFine(String registrationId, Date returnDate) {
		BooksTransaction book = new BooksTransaction();
		try {
			
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();

			String viewTransactionDetails = "from BooksTransaction where registrationId=:registrationId";
			Query query = entityManager.createQuery(viewTransactionDetails);
			query.setParameter("registrationId", registrationId);

			book = (BooksTransaction) query.getSingleResult();
			Date rtn = book.getReturnDate();

			BooksTransaction bookPresent = entityManager.find(BooksTransaction.class, book.getTransactionId());

			int days = (int) ((returnDate.getTime() - rtn.getTime()) / (1000 * 60 * 60 * 24));
			if (days > 0) {
				bookPresent.setFine(days * 1);
			} else {
				bookPresent.setFine(book.getFine());
			}
			bookPresent.setIssueDate(book.getIssueDate());
			bookPresent.setRegistrationId(book.getRegistrationId());
			bookPresent.setReturnDate(returnDate);
			bookPresent.setTransactionId(book.getTransactionId());

			transaction.commit();
			transaction.begin();
			String returnbook = "from BooksRegistration where registrationId=:registrationId";
			Query query1 = entityManager.createQuery(returnbook);
			query1.setParameter("registrationid", bookPresent.getRegistrationId());
			BooksRegistration delete = null;

			try {
				delete = (BooksRegistration) query1.getSingleResult();
				entityManager.remove(delete);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
			}
			entityManager.close();

		} catch (Exception e) {
		}
		return book;

	}


}
