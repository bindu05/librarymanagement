package com.capgemini.librarymanagement.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	public BooksTransaction acceptRequest(String RegistrationId) {
		return null;
	}

	@Override
	public List<BooksInventory> showAllBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BooksTransaction addFine(String registrationId, Date returnDate) {
		// TODO Auto-generated method stub
		return null;
	}

}
