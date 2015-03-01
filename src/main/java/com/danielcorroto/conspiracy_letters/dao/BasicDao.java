package com.danielcorroto.conspiracy_letters.dao;

import java.util.Collection;
import java.util.List;

/**
 * Interface para todos los DAO
 * 
 * @author Daniel Corroto
 *
 * @param <T>
 *            Clase del modelo
 */
public interface BasicDao<T> {
	/**
	 * Guarda un item
	 * 
	 * @param item
	 *            Item para ser guardado
	 * @return Item guardado
	 */
	public T save(T item);

	/**
	 * Almacena varios items
	 * 
	 * @param items
	 *            Items para ser guardados
	 * @return Items guardados
	 */
	public Collection<T> saveAll(Collection<T> items);

	/**
	 * Lista de todos los items
	 * 
	 * @return Lista con todos los items de la base de datos
	 */
	public List<T> list();

	/**
	 * Lista de size items desde la posición offset
	 * 
	 * @param offset
	 *            Posición inicial de búsqueda
	 * @param size
	 *            Máxima cantidad de items
	 * @return Lista de elementos encontrados
	 */
	public List<T> list(int offset, int size);

	/**
	 * Obtiene un item a partir de su id
	 * 
	 * @param id
	 *            Clave
	 * @return Item encontrado
	 */
	public T get(Long id);

	/**
	 * Obtiene un item cuyo field = value
	 * 
	 * @param field
	 *            Nombre del campo
	 * @param value
	 *            Valor a ser encontrado
	 * @return Item encontrado
	 */
	public T findBy(String field, Object value);

	/**
	 * Obtiene mútlples items cuyos field = value
	 * 
	 * @param field
	 *            Nombre del campo
	 * @param value
	 *            Valor a ser encontrado
	 * @return Item searched
	 */
	public List<T> multipleFindBy(String field, Object value);

	/**
	 * Elimina un item a partir de su id
	 * 
	 * @param id
	 *            Clave
	 */
	public void delete(Long id);

	/**
	 * Obtiene el número de filas de la tabla
	 * 
	 * @return Cantidad de filas de la tabla
	 */
	public long size();
}
