/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mixey.springmvc.model;

import java.io.Serializable;
import javax.validation.constraints.*;
/**
 *
 * @author dim
 */
public class User implements Serializable{
    
    @Size(min=3, max=30, message="Name must be min 3 and max=30 characters")
    private String name;
    
    @Size(min=3, max=30, message="Password must be min 3 and max=30 characters" )
    private String password;
    
    private boolean admin;

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    } 
}
