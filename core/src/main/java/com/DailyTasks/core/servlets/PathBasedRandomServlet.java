package com.DailyTasks.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import java.io.IOException;
import java.util.Random;


@Component(service = Servlet.class,
        property = {
                "sling.servlet.paths=" + "/bin/randomgenerator" 
        })
public class PathBasedRandomServlet extends SlingAllMethodsServlet {


    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");


        String sessionParam = request.getParameter("session");
        String randomParam = request.getParameter("random");

        
        String message;

        if (sessionParam != null) {
            message = "Generated session word: " + generateRandomWord();
        }
        else if (randomParam != null) {
            message = "Generated random numbers: " + generateRandomNumbers();
        }
    
        else {
            message = "No valid parameter provided. Use 'session' or 'random'.";
        }

    
        response.getWriter().write(message);
    }

    
    String generateRandomWord() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder word = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            word.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return word.toString();
    }

  
    String generateRandomNumbers() {
        Random random = new Random();
        StringBuilder numbers = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            numbers.append(random.nextInt(10)); 
        }
        return numbers.toString();
    }
}
