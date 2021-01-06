package net.onlineStore.exception;

import javax.servlet.http.HttpServletResponse;

public class ValidationException extends AbstractApplicationException {
    private static final long serialVersionUID = 5007865938185869867L;

    public ValidationException(String s) {
        super(s, HttpServletResponse.SC_BAD_REQUEST);
    }
}