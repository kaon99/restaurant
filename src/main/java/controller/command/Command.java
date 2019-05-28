package controller.command;
/**
 * * @author Yaroslav
 *  * @version 1.0
 *  */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    String execute(HttpServletRequest request, HttpServletResponse response);
}
