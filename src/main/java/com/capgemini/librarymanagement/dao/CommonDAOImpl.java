package com.capgemini.librarymanagement.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.capgemini.librarymanagement.beans.BooksInventory;
import com.capgemini.librarymanagement.beans.Users;

@Repository
public class CommonDAOImpl implements CommonDAO {

	@Override
	public Users login(Integer userId, String password) {

		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("TestPersistence");
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		EntityTransaction transaction=entityManager.getTransaction();

		Users user=null;
		String jpql="from Users where userId= :id  and password= :pw  and type='admin'" ;	
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
		
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("TestPersistence");
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
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("TestPersistence");
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
	public List<BooksInventory> searchBook(String name) {
		
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("TestPersistence");
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		EntityTransaction transaction=entityManager.getTransaction();
		String jpql="from Book where book_name='"+name+"'";
		Query query=(Query) entityManager.createQuery(jpql);
		List<BooksInventory> arraylist=new ArrayList<BooksInventory>();
		List<BooksInventory> list=query.getResultList();
		for(BooksInventory book:list) {
			arraylist.add(book);
		}
		return arraylist;
	}

}
