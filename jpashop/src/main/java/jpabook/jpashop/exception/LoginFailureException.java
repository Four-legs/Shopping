package jpabook.jpashop.exception;

public class LoginFailureException extends RuntimeException{
    public LoginFailureException(){
        super();
    }

    public LoginFailureException(String message) {
        super(message);
    }
}
