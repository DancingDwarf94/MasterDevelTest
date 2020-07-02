package com.masterdevel.backendserver.Configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class AuthConfigurations {

    public boolean isUnauthenticatedRoute(String route){
        return unauthenticatedRoutes().contains(route);
    }

    private List<String> unauthenticatedRoutes(){
        List<String> routes = new ArrayList<String>();
        routes.add("/credential");
        routes.add("/error");

        return routes;
    }
    
}