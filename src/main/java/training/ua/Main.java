package training.ua;

import training.ua.dao.DaoException;
import training.ua.dao.DaoFactory;
import training.ua.dao.entities.Role;
import training.ua.dao.entities.User;
import training.ua.dao.interfaces.AbstractDaoFactory;
import training.ua.dao.interfaces.RoleDao;

public class Main {

    public static void main(String[] args) {

        AbstractDaoFactory daoFactory = DaoFactory.getDaoFactory();
        RoleDao roleDao = daoFactory.createRoleDao();

        Role role = new Role();

        try {
            User user = DaoFactory.getDaoFactory().createUserDao().getUserByEmail("root");
            System.out.println(user);
        } catch (DaoException e) {
            e.printStackTrace();
        }

    }
}
