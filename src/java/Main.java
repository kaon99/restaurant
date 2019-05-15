import model.dao.daoimpl.DaoFactory;
import model.dao.daointerface.UserDao;
import model.entity.User;

public class Main {
    public static void main(String[] args) {
        UserDao service = DaoFactory.getInstance().createUserDao();
        User user = service.getByLoginAndPass("admin", "admin");
        User user1 = service.findByEmail("admin");
        System.out.print(user);
    }
}
