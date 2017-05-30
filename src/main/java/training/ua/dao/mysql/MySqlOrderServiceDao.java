package training.ua.dao.mysql;

import training.ua.dao.DaoException;
import training.ua.dao.AbstractUtilityDao;
import training.ua.dao.dbcp.ConnectionPool;
import training.ua.dao.entities.OrderService;
import training.ua.dao.interfaces.OrderServiceDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlOrderServiceDao extends AbstractUtilityDao<OrderService> implements OrderServiceDao {

    private static MySqlOrderServiceDao orderServiceDao = new MySqlOrderServiceDao();

    private MySqlOrderServiceDao() {
    }

    public static MySqlOrderServiceDao getInstance() {
        return orderServiceDao;
    }

    @Override
    public String getSelectByOrderIdQuery() {
        return MySqlQuery.ORDER_SERVICE_SELECT_BY_ORDER_ID;
    }

    @Override
    protected String getSelectByIdQuery() {
        return MySqlQuery.ORDER_SERVICE_SELECT_BY_ID;
    }

    @Override
    protected String getSelectQuery() {
        return MySqlQuery.ORDER_SERVICE_SELECT;
    }

    @Override
    protected String getCreateQuery() {
        return MySqlQuery.ORDER_SERVICE_CREATE;
    }

    @Override
    protected String getUpdateQuery() {
        return MySqlQuery.ORDER_SERVICE_UPDATE;
    }

    @Override
    protected String getDeleteQuery() {
        return MySqlQuery.ORDER_SERVICE_DELETE;
    }

    @Override
    protected List<OrderService> parseResultSet(ResultSet resultSet) throws DaoException {
        List<OrderService> orderServices = new ArrayList<>();

        try {
            while (resultSet.next()){
                OrderService orderService = new OrderService();
                orderService.setId(resultSet.getLong("order_service_id"));
                orderServices.add(orderService);
            }
        } catch (SQLException e) {
            throw new DaoException("Can not parse result set", e);
        }

        return orderServices;
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
    protected void preparedStatementForCreate(PreparedStatement statement, OrderService orderService) throws DaoException {

        try{
            statement.setLong(1, orderService.getOrderId());
            statement.setLong(2, orderService.getServiceId());
        } catch (SQLException e) {
            throw new DaoException("Can not set prepared statement", e);
        }
    }

    @Override
    protected void preparedStatementForUpdate(PreparedStatement statement, OrderService orderService) throws DaoException {

        try{
            statement.setLong(1, orderService.getOrderId());
            statement.setLong(2, orderService.getServiceId());
            statement.setLong(3, orderService.getId());
        } catch (SQLException e) {
            throw new DaoException("Can not set prepared statement", e);
        }
    }

    @Override
    protected void preparedStatementForDelete(PreparedStatement statement, OrderService orderService) throws DaoException {

        try{
            statement.setLong(1, orderService.getId());
        } catch (SQLException e) {
            throw new DaoException("Can not set prepared statement", e);
        }
    }

    @Override
    public List<OrderService> getOrderServicesByOrderId(Long id) throws DaoException {
        List<OrderService> orderServices = new ArrayList<>();

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(getSelectByOrderIdQuery())
        ){
            preparedStatementForSearchById(statement, id);

            try(ResultSet resultSet = statement.executeQuery()){
                orderServices = parseResultSet(resultSet);
            }

        } catch (SQLException | DaoException e) {
            throw new DaoException("Can not get by id", e);
        }

        return orderServices;
    }
}
