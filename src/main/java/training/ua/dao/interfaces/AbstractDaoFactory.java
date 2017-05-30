package training.ua.dao.interfaces;

public interface AbstractDaoFactory {

    OrderDao createOrderDao();
    OrderServiceDao createOrderServiceDao();
    RoleDao createRoleDao();
    ServiceDao createServiceDao();
    UserDao createUserDao();
}
