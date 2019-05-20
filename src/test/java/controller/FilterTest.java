package controller;



import controller.command.filter.AuthenticationFilter;

import org.junit.Test;
import org.mockito.Mock;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;


public class FilterTest {

    @Mock
    HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
    @Mock
    HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);

    @Mock
    FilterChain filterChain = mock(FilterChain.class);


    @Test
    public void testAuthenficationFilter() throws IOException, ServletException {

        // mock the getRequestURI() response
        when(httpServletRequest.getRequestURI()).thenReturn("/otherurl.jsp");

        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        authenticationFilter.doFilter(httpServletRequest, httpServletResponse,
                filterChain);

        // verify if a sendRedirect() was performed with the expected value
        verify(httpServletResponse).sendRedirect("/university/login");
    }
}