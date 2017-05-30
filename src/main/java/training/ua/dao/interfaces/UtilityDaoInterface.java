package training.ua.dao.interfaces;


import training.ua.dao.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public interface UtilityDaoInterface<T> {

    T getById(Long id) throws DaoException;
    List<T> getAll() throws DaoException;
    boolean create(T t) throws DaoException;
    boolean update(T t) throws DaoException;
    boolean delete(T t) throws DaoException;
}
