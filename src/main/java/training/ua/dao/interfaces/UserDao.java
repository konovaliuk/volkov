package training.ua.dao.interfaces;

import training.ua.dao.DaoException;
import training.ua.dao.entities.User;

import java.sql.PreparedStatement;

public interface UserDao extends UtilityDaoInterface<User> {

    String getSelectByEmailQuery();

    void preparedStatementForSearchByEmail(PreparedStatement statement, String email) throws DaoException;

    User getUserByEmail(String email) throws DaoException;
}
