package controller.command;
/**
 * * @author Yaroslav
 *  * @version 1.0
 *  */



import controller.command.admin.action.AdminOrderHistoryCommand;
import controller.command.admin.action.CreateBillCommand;
import controller.command.admin.page.AdminPageBillCommand;
import controller.command.admin.page.AdminPageCommand;
import controller.command.another.action.LogoutCommand;
import controller.command.another.action.RegistrationCommand;
import controller.command.another.page.LoginCommand;
import controller.command.another.page.MainCommand;
import controller.command.another.page.PageErrorCommand;
import controller.command.another.page.RegistrationCommandPage;
import controller.command.client.action.CreateOrderCommand;
import controller.command.client.action.PayBillCommand;
import controller.command.client.page.ClientBillPageCommand;
import controller.command.client.page.ClientOrderPageCommand;
import controller.command.client.page.ClientPageCommand;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static Logger logger = Logger.getLogger(CommandFactory.class);
    static private Map<String, Command> commandMap = new HashMap<>();

    static {
        commandMap.put("admin", new AdminPageCommand());
        commandMap.put("admin/bill", new AdminPageBillCommand());
        commandMap.put("login", new LoginCommand());
        commandMap.put("main", new MainCommand());
        commandMap.put("logout", new LogoutCommand());
        commandMap.put("registration", new RegistrationCommandPage());
        commandMap.put("registration/create", new RegistrationCommand());
        commandMap.put("client", new ClientPageCommand());
        commandMap.put("client/order", new ClientOrderPageCommand());
        commandMap.put("client/order/create", new CreateOrderCommand());
        commandMap.put("client/bill", new ClientBillPageCommand());
        commandMap.put("client/bill/pay", new PayBillCommand());
        commandMap.put("admin/bill/create" , new CreateBillCommand());
        commandMap.put("admin/history", new AdminOrderHistoryCommand());
    }

    public static Command getCommand(String url) {
        logger.info("Command get");
        Command command = commandMap.get(url);
        if (command == null) {
            return new PageErrorCommand();
        }
        return command;
    }

}
