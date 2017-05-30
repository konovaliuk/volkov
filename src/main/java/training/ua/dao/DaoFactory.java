package training.ua.dao;

import training.ua.dao.interfaces.AbstractDaoFactory;
import training.ua.dao.mysql.MySqlDaoFactory;

public class DaoFactory {

    public static AbstractDaoFactory getDaoFactory(){
        return MySqlDaoFactory.getInstance();
    }
}
