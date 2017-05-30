package training.ua.dao.interfaces;

import training.ua.dao.DaoException;
import training.ua.dao.entities.Order;

import java.util.List;

public interface OrderDao extends UtilityDaoInterface<Order> {

    String getSelectByUserIdQuery();

    List<Order> getOrdersByUserId(Long id) throws DaoException;

}
