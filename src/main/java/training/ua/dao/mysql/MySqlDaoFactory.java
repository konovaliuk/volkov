package training.ua.dao.mysql;

import training.ua.dao.interfaces.*;

public class MySqlDaoFactory implements AbstractDaoFactory {

    private static MySqlDaoFactory daoFactory = new MySqlDaoFactory();

    private MySqlDaoFactory(){}

    @Override
    public OrderDao createOrderDao() {
        return MySqlOrderDao.getInstance();
    }

    @Override
    public OrderServiceDao createOrderServiceDao() {
        return MySqlOrderServiceDao.getInstance();
    }

    @Override
    public RoleDao createRoleDao() {
        return MySqlRoleDao.getInstance();
    }

    @Override
    public ServiceDao createServiceDao() {
        return MySqlServiceDao.getInstance();
    }

    @Override
    public UserDao createUserDao() {
        return MySqlUserDao.getInstance();
    }

    public static MySqlDaoFactory getInstance(){
        return daoFactory;
    }
}
