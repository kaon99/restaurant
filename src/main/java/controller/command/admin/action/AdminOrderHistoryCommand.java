package controller.command.admin.action;

import controller.command.Command;
import controller.util.AttributesResourseManager;
import controller.util.Constants;
import controller.util.PageResourseManager;
import model.service.OrderService;
import model.service.implement.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class AdminOrderHistoryCommand implements Command {
   private OrderService orderService = new OrderServiceImpl();
    private int currentPage;
    private int numberOfPages;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        getCurrentPage(request);
        request.setAttribute(AttributesResourseManager.getProperty("parameter.orders") , orderService.findOrdersPagination(currentPage, Constants.RECORDS_FOR_PAGES));
        numberOfPages();
        request.setAttribute(AttributesResourseManager.getProperty("parameter.number.of.pages"), numberOfPages);
        request.setAttribute(AttributesResourseManager.getProperty("parameter.current.page"), currentPage);
        request.setAttribute(AttributesResourseManager.getProperty("parameter.records.per.page"), Constants.RECORDS_FOR_PAGES);

        return PageResourseManager.getProperty("admin.history");
    }

    private void getCurrentPage(HttpServletRequest request) {
        Optional<String> page = Optional.ofNullable(request.getParameter("currentPage"));
        currentPage = page.map(Integer::valueOf).orElse(Constants.FIRST_CURRENT_PAGE);
    }
    private void numberOfPages (){
        int rows = orderService.getNumberOfRows();
        numberOfPages = rows / Constants.RECORDS_FOR_PAGES;
        if (numberOfPages % Constants.RECORDS_FOR_PAGES > Constants.ZERO) {
            numberOfPages++;
        }
    }

}
