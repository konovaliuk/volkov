package training.ua.dao.interfaces;

import training.ua.dao.DaoException;
import training.ua.dao.entities.OrderService;

import java.util.List;

public interface OrderServiceDao extends UtilityDaoInterface<OrderService> {

    String getSelectByOrderIdQuery();

    List<OrderService> getOrderServicesByOrderId(Long id) throws DaoException;

}
