package controller.command.admin.action;

import controller.command.Command;
import controller.util.PageResourseManager;
import model.service.OrderService;
import model.service.implement.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class AdminOrderHistoryCommand implements Command {
   private OrderService orderService = new OrderServiceImpl();
    private int recordsPerPage = 5;
    private int currentPage = 1;
    private int numberOfPages;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        getCurrentPage(request);
        int rows = orderService.getNumberOfRows();
        request.setAttribute("orders", orderService.findOrdersPagination(currentPage, recordsPerPage));
        numberOfPages = rows / recordsPerPage;
        if (numberOfPages % recordsPerPage > 0) {
            numberOfPages++;
        }
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);

        return PageResourseManager.getProperty("admin.history");
    }

    private void getCurrentPage(HttpServletRequest request) {
        Optional<String> page = Optional.ofNullable(request.getParameter("currentPage"));
        currentPage = page.map(Integer::valueOf).orElse(1);
    }
}
