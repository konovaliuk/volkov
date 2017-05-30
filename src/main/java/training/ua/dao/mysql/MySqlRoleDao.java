package training.ua.dao.mysql;

import training.ua.dao.DaoException;
import training.ua.dao.AbstractUtilityDao;
import training.ua.dao.entities.Role;
import training.ua.dao.interfaces.RoleDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlRoleDao extends AbstractUtilityDao<Role> implements RoleDao {

    private static MySqlRoleDao roleDao = new MySqlRoleDao();

    private MySqlRoleDao() {
    }

    public static MySqlRoleDao getInstance() {
        return roleDao;
    }

    @Override
    protected String getSelectByIdQuery() {
        return MySqlQuery.ROLE_SELECT_BY_ID;
    }

    @Override
    protected String getSelectQuery(){
        return MySqlQuery.ROLE_SELECT;
    }

    @Override
    protected String getCreateQuery() {
        return MySqlQuery.ROLE_CREATE;
    }

    @Override
    protected String getUpdateQuery() {
        return MySqlQuery.ROLE_UPDATE;
    }

    @Override
    public String getDeleteQuery() {
        return MySqlQuery.ROLE_DELETE;
    }


    @Override
    protected List<Role> parseResultSet(ResultSet resultSet) throws DaoException {
        List<Role> roles = new ArrayList<>();

        try {
            while (resultSet.next()){
                Role role = new Role();
                role.setId(resultSet.getLong("role_id"));
                role.setRole(resultSet.getString("role"));
                roles.add(role);
            }
        } catch (SQLException e) {
            throw new DaoException("Can not parse result set", e);
        }

        return roles;
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
    protected void preparedStatementForCreate(PreparedStatement statement, Role role) throws DaoException {

        try{
            statement.setString(1, role.getRole());
        } catch (SQLException e) {
            throw new DaoException("Can not set prepared statement", e);
        }
    }

    @Override
    protected void preparedStatementForUpdate(PreparedStatement statement, Role role) throws DaoException {

        try{
            statement.setString(1, role.getRole());
            statement.setLong(2, role.getId());
        } catch (SQLException e) {
            throw new DaoException("Can not set prepared statement", e);
        }
    }

    @Override
    protected void preparedStatementForDelete(PreparedStatement statement, Role role) throws DaoException {
        try{
            statement.setLong(1, role.getId());
        } catch (SQLException e) {
            throw new DaoException("Can not set prepared statement", e);
        }
    }
}
