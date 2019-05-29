package controller.command.admin.action;

import controller.command.Command;
import controller.util.AttributesResourseManager;
import controller.util.PageResourseManager;
import model.service.OrderService;
import model.service.implement.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class AdminOrderHistoryCommand implements Command {
   private OrderService orderService = new OrderServiceImpl();
    private final int recordsPerPage = 5;
    private int currentPage;
    private int numberOfPages;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        getCurrentPage(request);
        request.setAttribute(AttributesResourseManager.getProperty("parameter.orders") , orderService.findOrdersPagination(currentPage, recordsPerPage));
        numberOfPages();
        request.setAttribute(AttributesResourseManager.getProperty("parameter.number.of.pages"), numberOfPages);
        request.setAttribute(AttributesResourseManager.getProperty("parameter.current.page"), currentPage);
        request.setAttribute(AttributesResourseManager.getProperty("parameter.records.per.page"), recordsPerPage);

        return PageResourseManager.getProperty("admin.history");
    }

    private void getCurrentPage(HttpServletRequest request) {
        Optional<String> page = Optional.ofNullable(request.getParameter("currentPage"));
        currentPage = page.map(Integer::valueOf).orElse(1);
    }
    private void numberOfPages (){
        int rows = orderService.getNumberOfRows();
        numberOfPages = rows / recordsPerPage;
        if (numberOfPages % recordsPerPage > 0) {
            numberOfPages++;
        }
    }

}
