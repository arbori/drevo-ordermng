package com.drevo.ordermng.exception;

public class EmailConfirmationException extends Exception {
    public EmailConfirmationException() {
    }
 
    public EmailConfirmationException(String var1) {
       super(var1);
    }
 
    public EmailConfirmationException(String var1, Throwable var2) {
       super(var1, var2);
    }
 
    public EmailConfirmationException(Throwable var1) {
       super(var1);
    }
 
    protected EmailConfirmationException(String var1, Throwable var2, boolean var3, boolean var4) {
       super(var1, var2, var3, var4);
    }     
}
