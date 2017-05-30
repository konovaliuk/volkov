package training.ua.dao;

import training.ua.dao.dbcp.ConnectionPool;
import training.ua.dao.interfaces.UtilityDaoInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractUtilityDao<T> implements UtilityDaoInterface<T> {

    protected abstract String getSelectByIdQuery();
    protected abstract String getSelectQuery();
    protected abstract String getCreateQuery();
    protected abstract String getUpdateQuery();
    protected abstract String getDeleteQuery();

    protected abstract List<T> parseResultSet(ResultSet resultSet) throws DaoException;
    protected abstract void preparedStatementForSearchById(PreparedStatement statement, Long id) throws DaoException;
    protected abstract void preparedStatementForCreate(PreparedStatement statement, T t) throws DaoException;
    protected abstract void preparedStatementForUpdate(PreparedStatement statement, T t) throws DaoException;
    protected abstract void preparedStatementForDelete(PreparedStatement statement, T t) throws DaoException;

    @Override
    public T getById(Long id) throws DaoException{
        List<T> list = new ArrayList<T>();
        T t = null;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(getSelectByIdQuery());
        ){
            preparedStatementForSearchById(statement, id);

            try(ResultSet resultSet = statement.executeQuery()){
                list = parseResultSet(resultSet);
            }

        } catch (SQLException | DaoException e) {
            throw new DaoException("Can not get by id", e);
        }

        if (!list.isEmpty()){
                t = list.get(0);
        }
        return t;
    }

    @Override
    public List<T> getAll() throws DaoException {
        List<T> list = new ArrayList<>();

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(getSelectQuery());
            ResultSet resultSet = statement.executeQuery()
        ){
            list = parseResultSet(resultSet);
        } catch (SQLException | DaoException e) {
            throw new DaoException("Can not get all", e);
        }

        return list;
    }

    @Override
    public boolean create(T t) throws DaoException {
        boolean isCreate = false;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(getCreateQuery())
        ){
            preparedStatementForCreate(statement, t);

            if (statement.executeUpdate() == 1){
                isCreate = true;
            }
        } catch (SQLException | DaoException e) {
            throw new DaoException("Can not create", e);
        }
        return isCreate;
    }

    @Override
    public boolean update(T t) throws DaoException{
        boolean isUpdate = false;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(getUpdateQuery())
        ){
            preparedStatementForUpdate(statement, t);

            if (statement.executeUpdate() == 1){
                isUpdate = true;
            }
        } catch (SQLException | DaoException e) {
            throw new DaoException("Can not update", e);
        }
        return isUpdate;
    }

    @Override
    public boolean delete(T t) throws DaoException{
        boolean isDelete = false;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(getDeleteQuery())
        ){
            preparedStatementForDelete(statement, t);

            if (statement.executeUpdate() == 1){
                isDelete = true;
            }
        } catch (SQLException | DaoException e) {
            throw new DaoException("Can not delete", e);
        }
        return isDelete;
    }
}
