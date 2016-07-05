package models;

import java.util.*;
import play.data.validation.Constraints.*;
import play.data.validation.*;

public class User {
    
    
    @Required protected String email;
    @Required protected String password;
    @Required protected String permission;
    
        
    public void setEmail(String email) {
        this.email=email;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setPassword(String password) {
        this.password=password;
    }
    
    public String getPassword() {
        return password;
    }
}