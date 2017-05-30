package training.ua.dao.mysql;


public interface MySqlQuery {

    String ORDER_SELECT_BY_USER_ID = "SELECT * FROM exchange.orders WHERE user_id = ?;";
    String ORDER_SELECT_BY_ID = "SELECT * FROM exchange.orders WHERE order_id = ?;";
    String ORDER_SELECT = "SELECT * FROM exchange.orders;";
    String ORDER_CREATE = "INSERT INTO exchange.orders (order_date, is_paid, pay_day, users_user_id) values (?, ?, ?, ?);";
    String ORDER_UPDATE = "UPDATE exchange.orders SET order_date = ?, is_paid = ?, pay_day = ?, users_user_id = ? WHERE order_id = ?;";
    String ORDER_DELETE = "DELETE FROM exchange.orders WHERE order_id = ?;";

    String ORDER_SERVICE_SELECT_BY_ORDER_ID = "SELECT * FROM exchange.order_service WHERE orders_order_id = ?;";
    String ORDER_SERVICE_SELECT_BY_ID = "SELECT * FROM exchange.order_service WHERE order_service_id = ?;";
    String ORDER_SERVICE_SELECT = "SELECT * FROM exchange.order_service;";
    String ORDER_SERVICE_CREATE = "INSERT INTO exchange.order_service (orders_order_id, services_service_id) values (?, ?);";
    String ORDER_SERVICE_UPDATE = "UPDATE exchange.order_service SET orders_order_id = ?, services_service_id = ? WHERE order_service_id = ?;";
    String ORDER_SERVICE_DELETE = "DELETE FROM exchange.order_service WHERE order_service_id = ?;";

    String ROLE_SELECT_BY_ID = "SELECT * FROM exchange.roles WHERE role_id = ?;";
    String ROLE_SELECT = "SELECT * FROM exchange.roles";
    String ROLE_CREATE = "INSERT INTO exchange.roles (role) values (?);";
    String ROLE_UPDATE = "UPDATE exchange.roles SET role = ? WHERE role_id = ?;";
    String ROLE_DELETE = "DELETE FROM exchange.roles WHERE role_id = ?;";

    String SERVICE_SELECT_BY_ID = "SELECT * FROM exchange.services WHERE service_id = ?;";
    String SERVICE_SELECT = "SELECT * FROM exchange.services;";
    String SERVICE_CREATE = "INSERT INTO exchange.services (name, price) values (?, ?);";
    String SERVICE_UPDATE = "UPDATE exchange.services SET name = ?, price = ? WHERE service_id = ?;";
    String SERVICE_DELETE = "DELETE FROM exchange.services WHERE service_id = ?;";

    String USER_SELECT_BY_EMAIL = "SELECT * FROM exchange.users WHERE email = ?;";
    String USER_SELECT_BY_ID = "SELECT * FROM exchange.users WHERE user_id = ?;";
    String USER_SELECT = "SELECT * FROM exchange.users;";
    String USER_CREATE = "INSERT INTO exchange.users (email, password, first_name, last_name, is_blocked, date_of_blocking, roles_role_id) values (?, ?, ?, ?, ?, ?, ?);";
    String USER_UPDATE = "UPDATE exchange.users SET email = ?, password = ?, first_name = ?, last_name = ?, is_blocked = ?, date_of_blocking = ?, roles_role_id = ? WHERE user_id = ?;";
    String USER_DELETE = "DELETE FROM exchange.users WHERE user_id = ?;";
}
