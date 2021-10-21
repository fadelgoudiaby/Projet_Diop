/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 */
public class CustomApplication extends ResourceConfig{

    public CustomApplication() {
        packages("sn.esp.dit2.servicerest");
        //register(CORSFilter.class);
        register(AuthenticationFilter.class);
    }
    
}
