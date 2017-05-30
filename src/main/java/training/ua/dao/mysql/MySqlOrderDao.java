package training.ua.dao.mysql;

import training.ua.dao.DaoException;
import training.ua.dao.AbstractUtilityDao;
import training.ua.dao.dbcp.ConnectionPool;
import training.ua.dao.entities.Order;
import training.ua.dao.interfaces.OrderDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlOrderDao extends AbstractUtilityDao<Order> implements OrderDao {

    private static MySqlOrderDao orderDao = new MySqlOrderDao();

    private MySqlOrderDao() {
    }

    public static MySqlOrderDao getInstance() {
        return orderDao;
    }

    @Override
    public String getSelectByUserIdQuery() {
        return MySqlQuery.ORDER_SELECT_BY_USER_ID;
    }

    @Override
    protected String getSelectByIdQuery() {
        return MySqlQuery.ORDER_SELECT_BY_ID;
    }

    @Override
    protected String getSelectQuery() {
        return MySqlQuery.ORDER_SELECT;
    }

    @Override
    protected String getCreateQuery() {
        return MySqlQuery.ORDER_CREATE;
    }

    @Override
    protected String getUpdateQuery() {
        return MySqlQuery.ORDER_UPDATE;
    }

    @Override
    protected String getDeleteQuery() {
        return MySqlQuery.ORDER_DELETE;
    }

    @Override
    protected List<Order> parseResultSet(ResultSet resultSet) throws DaoException {
        List<Order> orders = new ArrayList<>();

        try {
            while (resultSet.next()){
                Order order = new Order();
                order.setId(resultSet.getLong("order_id"));
                order.setOrderDate(resultSet.getTimestamp("order_date"));
                order.setPaid(resultSet.getBoolean("is_paid"));
                order.setPayDay(resultSet.getTimestamp("pay_day"));
                order.setUserId(resultSet.getLong("users_user_id"));
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException("Can not parse result set", e);
        }

        return orders;
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
    protected void preparedStatementForCreate(PreparedStatement statement, Order order) throws DaoException {

        try{
            statement.setTimestamp(1, order.getOrderDate());
            statement.setBoolean(2, order.getPaid());
            statement.setTimestamp(3, order.getPayDay());
            statement.setLong(4, order.getUserId());
        } catch (SQLException e) {
            throw new DaoException("Can not set prepared statement", e);
        }
    }

    @Override
    protected void preparedStatementForUpdate(PreparedStatement statement, Order order) throws DaoException {

        try{
            statement.setTimestamp(1, order.getOrderDate());
            statement.setBoolean(2, order.getPaid());
            statement.setTimestamp(3, order.getPayDay());
            statement.setLong(4, order.getUserId());
            statement.setLong(5, order.getId());
        } catch (SQLException e) {
            throw new DaoException("Can not set prepared statement", e);
        }
    }

    @Override
    protected void preparedStatementForDelete(PreparedStatement statement, Order order) throws DaoException {

        try{
            statement.setLong(1, order.getId());
        } catch (SQLException e) {
            throw new DaoException("Can not set prepared statement", e);
        }
    }

    @Override
    public List<Order> getOrdersByUserId(Long id) throws DaoException {
        List<Order> orders = new ArrayList<>();

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(getSelectByUserIdQuery())
        ){
            preparedStatementForSearchById(statement, id);

            try(ResultSet resultSet = statement.executeQuery()){
                orders = parseResultSet(resultSet);
            }

        } catch (SQLException | DaoException e) {
            throw new DaoException("Can not get by id", e);
        }

        return orders;
    }
}
