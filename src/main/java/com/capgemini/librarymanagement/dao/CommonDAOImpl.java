package com.capgemini.librarymanagement.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.capgemini.librarymanagement.beans.BooksInventory;
import com.capgemini.librarymanagement.beans.BooksRegistration;
import com.capgemini.librarymanagement.beans.Users;

@Repository
public class CommonDAOImpl implements CommonDAO {

	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

	@Override
	public Users login(String userId, String password) {


		EntityManager entityManager=entityManagerFactory.createEntityManager();
		EntityTransaction transaction=entityManager.getTransaction();

		Users user=null;
		String jpql="from Users where userId= :id  and password= :pw" ;	
		Query query=(Query) entityManager.createQuery(jpql);
		query.setParameter( "id",userId);
		query.setParameter( "pw",password);

		try {
			Users obj=(Users) query.getSingleResult();

			if(obj!=null) {
				return obj;
			}else {
				return obj;	
			}
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}



	@Override
	public List<Users> showAllStudentsInfo() {

		
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		EntityTransaction transaction=entityManager.getTransaction();
		List<Users> arraylist=new ArrayList<Users>();
		try {
			String jpql="from Users where type='student'";
			Query query=(Query) entityManager.createQuery(jpql);
			List<Users> list=query.getResultList();
			for(Users student:list) {
				arraylist.add(student);
			}
		} catch (Exception e) {
			return arraylist;
		}
		return arraylist;
	}

	@Override
	public List<Users> showAllLibrariansInfo() {
		
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		EntityTransaction transaction=entityManager.getTransaction();
		List<Users> arraylist=new ArrayList<Users>();
		try {
			String jpql="from Users where type='librarian'";
			Query query=(Query) entityManager.createQuery(jpql);
			List<Users> list=query.getResultList();
			for(Users student:list) {
				arraylist.add(student);
			}
		} catch (Exception e) {
			return arraylist;
		}
		return arraylist;
	}

	@Override
	public List<BooksInventory> searchBookByAuthor(String author1) {
		
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		EntityTransaction transaction=entityManager.getTransaction();
		String jpql="from BooksInventory where author1='"+author1+"'";
		Query query=(Query) entityManager.createQuery(jpql);
		List<BooksInventory> arraylist=new ArrayList<BooksInventory>();
		List<BooksInventory> list=query.getResultList();
		for(BooksInventory book:list) {
			arraylist.add(book);
		}
		return arraylist;
	}



	@Override
	public Boolean cancelRequest(String registrationId, String userId) {
		
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		EntityTransaction transaction=entityManager.getTransaction();
		transaction.begin();
		String select="from Registration where registrationId=:registrationId and userId=:userId";
		Query query=entityManager.createQuery(select);

		query.setParameter("registrationId", registrationId);
		query.setParameter("userId", userId);
		BooksRegistration book=null;
		try {
			book=(BooksRegistration)query.getSingleResult();
			entityManager.remove(book);
			transaction.commit();

		}catch(Exception e) {
			transaction.rollback();
			return false;
		}
		entityManager.close();
		return true;
	}

}
