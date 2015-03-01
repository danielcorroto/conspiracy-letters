package com.danielcorroto.conspiracy_letters.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.danielcorroto.conspiracy_letters.dao.BasicDao;

/**
 * Abstract class para cualquier DAO
 * 
 * @author Daniel Corroto
 *
 * @param <T>
 *            Clase del modelo
 */
public abstract class BasicDaoImpl<T> implements BasicDao<T> {
	private static final Logger LOGGER = Logger.getLogger(BasicDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public T save(T item) {
		LOGGER.trace("Saving " + item);
		T saved = (T) getSession().merge(item);
		return saved;
	}

	@Override
	public Collection<T> saveAll(Collection<T> items) {
		Collection<T> saveds = new ArrayList<T>();
		for (T item : items) {
			T saved = save(item);
			saveds.add(saved);
		}
		return saveds;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> list() {
		LOGGER.trace("List");
		List<T> found = getSession().createCriteria(getType()).list();
		return found;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> list(int offset, int size) {
		LOGGER.trace("List from offset: " + offset + " size: " + size);
		List<T> found = getSession().createCriteria(getType()).setFirstResult(offset).setMaxResults(size).list();
		return found;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(Long id) {
		LOGGER.trace("Getting " + id);
		T found = (T) getSession().get(getType(), id);
		return found;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findBy(String field, Object value) {
		LOGGER.trace("Find by " + field + " = " + value);
		T found = (T) getSession().createCriteria(getType()).add(Restrictions.eq(field, value)).uniqueResult();
		return found;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> multipleFindBy(String field, Object value) {
		LOGGER.trace("Multiple find by " + field + " = " + value);
		List<T> found = getSession().createCriteria(getType()).add(Restrictions.eq(field, value)).list();
		return found;
	}

	@Override
	public void delete(Long id) {
		LOGGER.trace("Removing " + id);
		T item = get(id);

		if (null != item) {
			getSession().delete(item);
		}
	}

	@Override
	public long size() {
		Class<T> type = getType();
		Long size = (Long) getSession().createCriteria(type).setProjection(Projections.rowCount()).uniqueResult();
		return size;
	}

	protected Session getSession() {
		Session session = getSessionFactory().getCurrentSession();
		if (session == null) {
			session = getSessionFactory().openSession();
		}
		return session;
	}

	protected SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@SuppressWarnings("unchecked")
	private Class<T> getType() {
		Class<T> type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		return type;
	}
}
