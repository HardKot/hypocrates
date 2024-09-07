package com.hypocrates.hypocrates.infrastructure.configs.mvc;

import com.hypocrates.hypocrates.infrastructure.context.ClinicContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.net.URI;

public class ClinicInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        var clinicCodeHeader = request.getHeader("X-Clinic-Code");
        if (clinicCodeHeader != null) {
//            ClinicContext.setClinic(clinicCodeHeader);
            return true;
        }

        var requestUrl = new URI(request.getRequestURL().toString()).toURL();
        var urlPart = requestUrl.getHost().split("\\.");
        var clinicCode = "demo";
        if (urlPart.length > 2) {
            clinicCode = urlPart[urlPart.length - 3];
        }
//        ClinicContext.setClinicCode(clinicCode);

        return true;
    }
}
