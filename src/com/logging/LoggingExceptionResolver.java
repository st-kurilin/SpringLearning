package com.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author: Andrey Loboda
 * @date : 05.09.11
 */
public class LoggingExceptionResolver extends SimpleMappingExceptionResolver {

    private static Logger log = LoggerFactory.getLogger(LoggingExceptionResolver.class);

    public LoggingExceptionResolver() {
        super();
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
        HttpServletResponse response, Object handler, Exception ex) {
        log.error(getStackTrace(ex));
        return super.resolveException(request, response, handler, ex);
    }

    /**
    * @param throwable, Throwable
    * @return String of the exception
    */
    public String getStackTrace(Throwable throwable) {
        StringWriter stringWritter = new StringWriter();
        PrintWriter printWritter = new PrintWriter(stringWritter, true);
        throwable.printStackTrace(printWritter);
        printWritter.flush();
        stringWritter.flush();
        return stringWritter.toString();
    }
}

