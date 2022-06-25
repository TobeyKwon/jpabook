package jpabook.jpashop.domain.exception;

public class DuplicatedUsernameException extends IllegalStateException {

    public DuplicatedUsernameException() {}

    public DuplicatedUsernameException(String s) {
        super(s);
    }

    public DuplicatedUsernameException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicatedUsernameException(Throwable cause) {
        super(cause);
    }
}
