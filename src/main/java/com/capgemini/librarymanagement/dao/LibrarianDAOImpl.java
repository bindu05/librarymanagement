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
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.librarymanagement.beans.BooksInventory;
import com.capgemini.librarymanagement.beans.BooksRegistration;
import com.capgemini.librarymanagement.beans.BooksTransaction;

@Repository
public class LibrarianDAOImpl implements LibrarianDAO {

	@Autowired
	private CommonDAO dao;

	@Override
	public Boolean addBook(BooksInventory book) {
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("TestPersistence");
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		EntityTransaction transaction=entityManager.getTransaction();

		try{

			BooksInventory temp = (BooksInventory) dao.searchBookByName(book.getBookName());
			if(temp!=null) {
			}else {
				book.setBookId(book.getBookId());
				book.setBookName(book.getBookName());
				book.setAuthor1(book.getAuthor1());
				book.setAuthor2(book.getAuthor2());
				book.setPublisher(book.getPublisher());
				book.setYearOfPublication(book.getYearOfPublication());

				transaction.begin();
				entityManager.persist(book); 
				transaction.commit();

				return true;
			}
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();

		}

		return false;
	}

	@Override
	public Boolean updateBook(BooksInventory book) {
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("TestPersistence");
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		EntityTransaction transaction=entityManager.getTransaction();

		try {
			transaction.begin();
			String jpql="UPDATE BooksInventory SET book_name=:nm,author1=:aut1,author2=:aut2,"
					+ "publisher=:pb,yearofpublication=:yop WHERE book_id=:id";
			Query query=(Query) entityManager.createQuery(jpql);	
			query.setParameter("id",book.getBookId());
			query.setParameter("nm", book.getBookName());
			query.setParameter("aut1", book.getAuthor1());
			query.setParameter("aut2", book.getAuthor2());
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
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("TestPersistence");
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		EntityTransaction transaction=entityManager.getTransaction();

		try {
			BooksInventory book=null;
			book=entityManager.find(BooksInventory.class, bookId);
			if(book==null) {

			}else {
				transaction.begin();
				entityManager.remove(book);
				transaction.commit();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}return false;
	}

	@Override
	public List<BooksTransaction> showAllIssuedBooksInfo() {
		
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("TestPersistence");
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		EntityTransaction transaction=entityManager.getTransaction();
		List<BooksTransaction> arraylist=new ArrayList<BooksTransaction>();
		try {
			String jpql="from BooksTransaction";
			Query query=(Query) entityManager.createQuery(jpql);
			List<BooksTransaction> list=query.getResultList();
			for(BooksTransaction book:list) {
				arraylist.add(book);
			}
		} catch (Exception e) {
			return arraylist;
		}
		return arraylist;
	}
	

	@Override
	public List<BooksRegistration> showAllRequestedBooksInfo() {
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("TestPersistence");
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
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("TestPersistence");
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		EntityTransaction transaction=entityManager.getTransaction();
		transaction.begin();
		
		String viewDetails = "from BooksRegistration where registrationId=:registrationId";
		Query query = entityManager.createQuery(viewDetails);
		query.setParameter("registrationId", registrationId);
		
		BooksRegistration books = (BooksRegistration) query.getSingleResult();
		Random random = new Random();
		int transactionId = random.nextInt();
		
		if(transactionId<0) {
			transactionId = transactionId*(-1);
		}
		BooksTransaction trans = new BooksTransaction();
		trans.setRegistrationId(books.getRegistrationId());
		trans.setTransactionId(Integer.toString(transactionId));
		trans.setIssueDate(books.getRegistrationDate());
		trans.setFine(0);
		
		Calendar calendar =  Calendar.getInstance();
		calendar.setTime(books.getRegistrationDate());
		calendar.add(Calendar.DATE, 15);
		trans.setReturnDate(calendar.getTime());
		
		entityManager.persist(trans);
		transaction.commit();
		
		return trans;
	}

	@Override
	public List<BooksInventory> showAllBooks() {
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("TestPersistence");
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
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("TestPersistence");
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		EntityTransaction transaction=entityManager.getTransaction();
		
		transaction.begin();
		String viewDetails = "from BooksTransaction where registrationId=:registrationId";
		Query query = entityManager.createQuery(viewDetails);
		query.setParameter("registrationId", registrationId);
		
		BooksTransaction book = (BooksTransaction) query.getSingleResult();
		Date rn = book.getReturnDate();
		
		BooksTransaction booksPresent = entityManager.find(BooksTransaction.class, book.getTransactionId());
		int days = (int)((returnDate.getTime()-rn.getTime())/(1000*60*60*24));
		if((days-15)>0) {
			booksPresent.setFine((days-15)*1);
		}else {
			booksPresent.setFine(book.getFine());
		}
		
		booksPresent.setIssueDate(book.getIssueDate());
		booksPresent.setRegistrationId(book.getRegistrationId());
		booksPresent.setReturnDate(book.getReturnDate());
		booksPresent.setTransactionId(book.getTransactionId());
		
		transaction.commit();
		System.out.println(booksPresent.getFine());
		return book;
		
	} // End of addFine

}
