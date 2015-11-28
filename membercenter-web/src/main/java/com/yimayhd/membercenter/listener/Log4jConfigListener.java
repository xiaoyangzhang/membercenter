package com.yimayhd.membercenter.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by root on 15-11-28.
 */
public class Log4jConfigListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            LogbackWebConfigurer.initLogging(sce.getServletContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
