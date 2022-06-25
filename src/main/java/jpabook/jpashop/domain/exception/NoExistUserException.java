package jpabook.jpashop.domain.exception;

public class NoExistUserException extends IllegalStateException {

    public NoExistUserException() {
    }

    public NoExistUserException(String s) {
        super(s);
    }

    public NoExistUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoExistUserException(Throwable cause) {
        super(cause);
    }
}
