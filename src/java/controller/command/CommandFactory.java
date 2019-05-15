package controller.command;


import controller.command.admin.page.AdminCommand;
import controller.command.another.action.LogoutCommand;
import controller.command.another.action.RegistrationCommand;
import controller.command.another.page.*;
import controller.command.client.page.ClientPageCommand;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static Logger logger = Logger.getLogger(CommandFactory.class);
    static private Map<String, Command> commandMap = new HashMap<>();

    static {
        commandMap.put("admin", new AdminCommand());
        commandMap.put("login", new LoginCommand());
        commandMap.put("main", new MainCommand());
        commandMap.put("logout", new LogoutCommand());
        commandMap.put("registration", new RegistrationCommandPage());
        commandMap.put("registration/create", new RegistrationCommand());
        commandMap.put("client", new ClientPageCommand());



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
