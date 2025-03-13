package com.DailyTasks.core.servlets;

import com.DailyTasks.core.service.AuthService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import java.io.IOException;
import java.io.PrintWriter;

@Component(
        service = Servlet.class,
        property = {
                "sling.servlet.paths=/bin/secureCheque",
                "sling.servlet.methods=GET"
        }
)
public class SecureChequeServlet extends SlingAllMethodsServlet {

    @Reference
    private AuthService authService;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        String userToken = request.getHeader("Authorization");

        if (userToken == null || !isValidToken(userToken)) {
            response.setStatus(SlingHttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized access");
            return;
        }

        // Assuming cheque images are stored in /content/dam/bank/cheques
        String chequePath = "/content/dam/bank/cheques/sample.jpg"; 

        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.write("{\"secureImageUrl\": \"" + chequePath + "\"}");
    }

    private boolean isValidToken(String token) {
        String accessToken = authService.getAccessToken();
        return token.equals("Bearer " + accessToken);
    }
}