/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.esp.dit2.server;

import javax.xml.ws.Endpoint;
import sn.esp.dit2.servicesoap.UserService;

/**
 *
 * @author pbfall
 */
public class MainSoap {
    
    public static void main(String[] args) {
        String url = "http://localhost:2299/";
        Endpoint.publish(url, new UserService());
        System.out.println("server listening on  " + url);
    }
}
