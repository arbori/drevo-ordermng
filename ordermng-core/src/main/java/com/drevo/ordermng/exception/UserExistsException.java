package com.drevo.ordermng.exception;

public class UserExistsException extends Exception {
    public UserExistsException() {
    }
 
    public UserExistsException(String var1) {
       super(var1);
    }
 
    public UserExistsException(String var1, Throwable var2) {
       super(var1, var2);
    }
 
    public UserExistsException(Throwable var1) {
       super(var1);
    }
 
    protected UserExistsException(String var1, Throwable var2, boolean var3, boolean var4) {
       super(var1, var2, var3, var4);
    }    
}
