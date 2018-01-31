package com.tcm.college.repository;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tcm.college.entity.Consumer;
import com.tcm.college.entity.ConsumerRole;
import com.tcm.college.exception.CollegeException;
import com.tcm.college.utill.Constant;
import com.tcm.college.utill.HQL;

@Transactional
@Component
public class ConsumerRepositoryImpl implements ConsumerRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Consumer getConsumer(String email) throws CollegeException {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(HQL.SELECT_CONSUMER_BY_EMAIL);
			query.setParameter("email", email);

			List<?> userList = query.list();
			if (userList != null && !userList.isEmpty()) {
				return (Consumer) userList.get(0);
			}
		} catch (HibernateException hibernateException) {
			throw new CollegeException("Error in retrieving user.", hibernateException);
		}
		return null;
	}

	@Override
	public void updateLogInFailCount(String email, String authType, boolean accountLockRequire)
			throws CollegeException {
		try {
			Session session = sessionFactory.getCurrentSession();

			Query query;

			if (Constant.AUTH_SUCCESS.equalsIgnoreCase(authType)) {
				query = session.createQuery(HQL.UPDATE_CONSUMER_LOGIN_FAIL_AS_ZERO);
				query.setParameter("email", email);
			} else {
				if (accountLockRequire) {
					query = session.createQuery(HQL.UPDATE_CONSUMER_STATUS_WITH_LOGIN_FAIL_COUNT);
					query.setParameter("invalCount", 10);
					query.setParameter("status", Constant.CONSUMER_INACTIVE);
					query.setParameter("email", email);
				} else {
					query = session.createQuery(HQL.UPDATE_CONSUMER_LOGIN_FAIL_COUNT);
					query.setParameter("email", email);
				}
			}
			query.executeUpdate();
		} catch (HibernateException hibernateException) {
			throw new CollegeException("Error in updating login fail count.", hibernateException);
		}

	}

	@Override
	public List<ConsumerRole> getConsumerRoleList() throws CollegeException {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(HQL.SELECT_ALL_CONSUMER_ROLE_LIST);
			return (List<ConsumerRole>) query.list();
		} catch (HibernateException hibernateException) {
			throw new CollegeException("Error in retriving consumer role list.", hibernateException);
		}
	}

	@Override
	public void createConsumer(Consumer consumer) throws CollegeException {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.save(consumer);
		} catch (HibernateException hibernateException) {
			throw new CollegeException("Error in consumer creation.", hibernateException);
		}
	}

	@Override
	public List<ConsumerRole> getConsumerRoleList(String role) throws CollegeException {
		try
        {
            Session session = sessionFactory.getCurrentSession();
           
            Query query = session.createQuery(HQL.SELECT_CONSUMER_ROLE_LIST);
                       
            return (List<ConsumerRole>)query.list();
        }
        catch (HibernateException hibernateException)
        {
            throw new CollegeException("Error in retriving consumer role list.", hibernateException);
        }
	}

}
