package com.sample.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.sample.exception.CustomerExceptionClass;

@Component
public class GenericDAOImpl<T, P extends Serializable> implements GenericDAO<T, P> {

	// The session factory.
	private SessionFactory sessionFactory;
	public GenericDAOImpl() {
	}

	/**
	 * Instantiates a new Generic DAO Hibernate Impl.
	 * @param sessionFactory sessionFactory
	 */
	public GenericDAOImpl(final SessionFactory sessionFactory) throws CustomerExceptionClass {
		// 1.Initialize Session Factory
		this.sessionFactory = sessionFactory;
	}
	
	/**
     * Gets the session factory.
     *
     * @return the session factory
     */
    public final SessionFactory getSessionFactory() {
        return sessionFactory;
    }

	/* (non-Javadoc)
	 * @see com.allcad.common.dataaccess.GenericDAO#saveOrUpdate(java.lang.Object)
	 */
	@Override
	public final void saveOrUpdate(final T object) throws CustomerExceptionClass {
		Session session = null;
		try {
			// Get session from session Factory
			session = sessionFactory.openSession();
			// Create Transaction Process
			Transaction tx = session.beginTransaction();
			// Call  SaveOrUpdate()
			session.saveOrUpdate(object);
			// Transaction Commit
			tx.commit();
		} catch (RuntimeException e) {
			throw new CustomerExceptionClass(e.getCause().getMessage(), e);
		} finally {
			closeSession(session);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
    public final List<T> callHQLQuery(final String hqlQuery)
            throws CustomerExceptionClass {
        Session session = null;
        System.out.println(hqlQuery);
        try {
            // Get session from session Factory (Open Session)
            session = sessionFactory.openSession();
            // Call createQuery()
            Query query = session.createQuery(hqlQuery);
            // Call  query.list()
            // Return list of Persistence class
            return query.list();
        } catch (RuntimeException e) {
        	e.printStackTrace();
            throw new CustomerExceptionClass(e.getCause().getMessage(), e);
        } finally {
            closeSession(session);
        }
    }

	/**
	 * @param session session
	 */
	private void closeSession(final Session session) {
		if (session != null) {
			// 9. Close session
			session.close();
		}
	}
}
