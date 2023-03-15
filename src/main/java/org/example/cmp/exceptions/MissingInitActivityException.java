package org.example.cmp.exceptions;

public class MissingInitActivityException extends Exception{
    public MissingInitActivityException() {
        super("No initial activity found");
    }
}
