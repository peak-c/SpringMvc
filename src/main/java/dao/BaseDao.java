package dao;

import exception.CustomException;

import java.util.List;

/**
 * Created by peakone on 2015/9/24.
 */
public interface BaseDao<T> {

    public List<T> findAll() throws Exception;

    public void persist(T t) throws Exception;

    public void update(T t) throws Exception;

    public void delete(T t) throws Exception;

    public void deleteByIds(Long... ids) throws Exception;

    public T findById(Long id) throws Exception;
}
