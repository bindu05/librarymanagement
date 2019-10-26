package com.capgemini.librarymanagement.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.capgemini.librarymanagement.beans.Users;

@Repository
public class AdminDAOImpl implements AdminDAO {



	@Override
	public Boolean registerStudents(Users user) {

		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("TestPersistence");
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		EntityTransaction transaction=entityManager.getTransaction();
		try {

			user.setType("student");
			if(searchStudents(user.getUserId())!=null) {
				return false;
			}else {
				transaction.begin();
				entityManager.persist(user);
				transaction.commit();
				return true;
			}		
		}catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean modifyStudents(Users user) {

		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("TestPersistence");
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		EntityTransaction transaction=entityManager.getTransaction();

		try {
			
			if(searchStudents(user.getUserId())==null) {
				return false;
			}else {

				user.setType("student");
				String jpql="update Users set  emailId=:em,password=:pw, userName=:nm where userId=:id and type='student'";
				Query query=(Query) entityManager.createQuery(jpql);	
				query.setParameter("id",user.getUserId());
				query.setParameter("em", user.getEmailId());
				query.setParameter("pw", user.getPassword());
				query.setParameter("nm", user.getUserName());
				transaction.begin();
				int res=query.executeUpdate();
				transaction.commit();
				if(res>0) {
					return true;
				}else {
					transaction.rollback();

				}		
			}	
		}catch(Exception e) {
			transaction.rollback();
			e.printStackTrace();

		}
		return false;
	}

	@Override
	public Boolean deleteStudents(String userId) {
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("TestPersistence");
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		EntityTransaction transaction=entityManager.getTransaction();

		try {
			Users user=null;
			user=searchStudents(userId);
			if(user==null) {
				return false;
			}else {
				transaction.begin();
				entityManager.remove(user);
				transaction.commit();
				return true;
			}

		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Users searchStudents(String userId) {

		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("TestPersistence");
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		EntityTransaction transaction=entityManager.getTransaction();

		Users user=null;
		String jpql="from Users where userId='"+userId+"' and type='student'" ;	
		Query query=(Query) entityManager.createQuery(jpql);
		//query.setParameter( "id",userId);

		try {
			Users obj = (Users) query.getSingleResult();

			if(user!=null) {
				return obj;
			}else {
				return obj;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public Boolean deleteLibrarian(String userId) {
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("TestPersistence");
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		EntityTransaction transaction=entityManager.getTransaction();

		Users user1=null;
		user1=searchLibrarian(userId);
		if(user1==null) {
			return false;
		}else {
			transaction.begin();
			entityManager.remove(userId);
			transaction.commit();
			return true;
		}
	}

	@Override
	public Boolean modifyLibrarian(Users user) {
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("TestPersistence");
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		EntityTransaction transaction=entityManager.getTransaction();

		try {
			user.setType("librarian");
			if(searchStudents(user.getUserId())==null) {
				return false;
			}else {

				String jpql="update Users set  emailId=:em,password=:pw, userName=:nm, where userId=:id and type='librarian'";
				Query query=(Query) entityManager.createQuery(jpql);	
				query.setParameter("id",user.getUserId());
				query.setParameter("em", user.getEmailId());
				query.setParameter("pw", user.getPassword());
				query.setParameter("nm", user.getUserName());
				transaction.begin();
				int res=query.executeUpdate();
				transaction.commit();
				if(res>0) {
					return true;
				}else {
					transaction.rollback();
					return false;
				}		
			}	
		}catch(Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Users searchLibrarian(String userId) {
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("TestPersistence");
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		EntityTransaction transaction=entityManager.getTransaction();

		Users user=null;
		String jpql="from Users where userId= :id and type='librarian'" ;	
		Query query=(Query) entityManager.createQuery(jpql);
		query.setParameter( "id",userId);

		try {
			Users obj = (Users) query.getSingleResult();
			if(user!=null) {
				return obj;
			}else {
				return obj;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return user;
		}
	}

	@Override
	public Boolean addLibrarian(Users user) {
		 	EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("TestPersistence");
			EntityManager entityManager=entityManagerFactory.createEntityManager();
			EntityTransaction transaction=entityManager.getTransaction();
			try {

				user.setType("librarian");
				if(searchLibrarian(user.getUserId())!=null) {
					return false;
				}else {
					transaction.begin();
					entityManager.persist(user);
					transaction.commit();
					return true;
				}		
			}catch (Exception e) {
				transaction.rollback();
				e.printStackTrace();
				return false;
			}
		}
	}






