package training.ua.dao.mysql;

import training.ua.dao.DaoException;
import training.ua.dao.AbstractUtilityDao;
import training.ua.dao.entities.Service;
import training.ua.dao.interfaces.ServiceDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlServiceDao extends AbstractUtilityDao<Service> implements ServiceDao {

    private static MySqlServiceDao serviceDao = new MySqlServiceDao();

    private MySqlServiceDao() {
    }

    public static MySqlServiceDao getInstance() {
        return serviceDao;
    }

    @Override
    protected String getSelectByIdQuery() {
        return MySqlQuery.SERVICE_SELECT_BY_ID;
    }

    @Override
    protected String getSelectQuery() {
        return MySqlQuery.SERVICE_SELECT;
    }

    @Override
    protected String getCreateQuery() {
        return MySqlQuery.SERVICE_CREATE;
    }

    @Override
    protected String getUpdateQuery() {
        return MySqlQuery.SERVICE_UPDATE;
    }

    @Override
    protected String getDeleteQuery() {
        return MySqlQuery.SERVICE_DELETE;
    }

    @Override
    protected List<Service> parseResultSet(ResultSet resultSet) throws DaoException {
        List<Service> services = new ArrayList<>();

        try {
            while (resultSet.next()){
                Service service = new Service();
                service.setId(resultSet.getLong("service_id"));
                service.setName(resultSet.getString("name"));
                service.setPrice(resultSet.getDouble("price"));
                services.add(service);
            }
        } catch (SQLException e) {
            throw new DaoException("Can not parse result set", e);
        }

        return services;
    }

    @Override
    protected void preparedStatementForSearchById(PreparedStatement statement, Long id) throws DaoException {
        try{
            statement.setLong(1, id);
        } catch (SQLException e) {
            throw new DaoException("Can not set prepared statement", e);
        }
    }

    @Override
    protected void preparedStatementForCreate(PreparedStatement statement, Service service) throws DaoException {

        try{
            statement.setString(1, service.getName());
            statement.setDouble(2, service.getPrice());
        } catch (SQLException e) {
            throw new DaoException("Can not set prepared statement", e);
        }
    }

    @Override
    protected void preparedStatementForUpdate(PreparedStatement statement, Service service) throws DaoException {

        try{
            statement.setString(1, service.getName());
            statement.setDouble(2, service.getPrice());
            statement.setLong(3, service.getId());
        } catch (SQLException e) {
            throw new DaoException("Can not set prepared statement", e);
        }
    }

    @Override
    protected void preparedStatementForDelete(PreparedStatement statement, Service service) throws DaoException {

        try{
            statement.setLong(1, service.getId());
        } catch (SQLException e) {
            throw new DaoException("Can not set prepared statement", e);
        }
    }
}
