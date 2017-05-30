package training.ua.dao.mysql;

import training.ua.dao.DaoException;
import training.ua.dao.AbstractUtilityDao;
import training.ua.dao.dbcp.ConnectionPool;
import training.ua.dao.entities.User;
import training.ua.dao.interfaces.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserDao extends AbstractUtilityDao<User> implements UserDao {

    private static MySqlUserDao userDao = new MySqlUserDao();

    private MySqlUserDao() {
    }

    public static MySqlUserDao getInstance() {
        return userDao;
    }

    @Override
    public String getSelectByEmailQuery() {
        return MySqlQuery.USER_SELECT_BY_EMAIL;
    }

    @Override
    protected String getSelectByIdQuery() {
        return MySqlQuery.USER_SELECT_BY_ID;
    }

    @Override
    protected String getSelectQuery() {
        return MySqlQuery.USER_SELECT;
    }

    @Override
    protected String getCreateQuery() {
        return MySqlQuery.USER_CREATE;
    }

    @Override
    protected String getUpdateQuery() {
        return MySqlQuery.USER_UPDATE;
    }

    @Override
    protected String getDeleteQuery() {
        return MySqlQuery.USER_DELETE;
    }

    @Override
    protected List<User> parseResultSet(ResultSet resultSet) throws DaoException {
        List<User> users = new ArrayList<>();

        try {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("user_id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setBlocked(resultSet.getBoolean("is_blocked"));
                user.setDateOfBlocking(resultSet.getTimestamp("date_of_blocking"));
                user.setRoleId(resultSet.getLong("roles_role_id"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException("Can not parse result set", e);
        }

        return users;
    }

    @Override
    protected void preparedStatementForSearchById(PreparedStatement statement, Long id) throws DaoException {
        try {
            statement.setLong(1, id);
        } catch (SQLException e) {
            throw new DaoException("Can not set prepared statement", e);
        }
    }

    @Override
    protected void preparedStatementForCreate(PreparedStatement statement, User user) throws DaoException {

        try {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setBoolean(5, user.getBlocked());
            statement.setTimestamp(6, user.getDateOfBlocking());
            statement.setLong(7, user.getRoleId());
        } catch (SQLException e) {
            throw new DaoException("Can not set prepared statement", e);
        }
    }

    @Override
    protected void preparedStatementForUpdate(PreparedStatement statement, User user) throws DaoException {

        try {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setBoolean(5, user.getBlocked());
            statement.setTimestamp(6, user.getDateOfBlocking());
            statement.setLong(7, user.getRoleId());
            statement.setLong(8, user.getId());
        } catch (SQLException e) {
            throw new DaoException("Can not set prepared statement", e);
        }
    }

    @Override
    protected void preparedStatementForDelete(PreparedStatement statement, User user) throws DaoException {

        try {
            statement.setLong(1, user.getId());
        } catch (SQLException e) {
            throw new DaoException("Can not set prepared statement", e);
        }
    }

    @Override
    public void preparedStatementForSearchByEmail(PreparedStatement statement, String email) throws DaoException {
        try {
            statement.setString(1, email);
        } catch (SQLException e) {
            throw new DaoException("Can not set prepared statement", e);
        }
    }

    @Override
    public User getUserByEmail(String email) throws DaoException {
        List<User> users = new ArrayList<>();
        User user = null;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(getSelectByEmailQuery())
        ){
            preparedStatementForSearchByEmail(statement, email);

            try(ResultSet resultSet = statement.executeQuery()){
                users = parseResultSet(resultSet);
            }
        } catch (SQLException | DaoException e) {
            throw new DaoException("Can not get by id", e);
        }

        if (!users.isEmpty()){
            user = users.get(0);
        }

        return user;
    }
}
