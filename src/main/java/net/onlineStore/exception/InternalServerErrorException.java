package net.onlineStore.exception;

import javax.servlet.http.HttpServletResponse;

public class InternalServerErrorException extends AbstractApplicationException {

    private static final long serialVersionUID = -1616487714598241672L;

    public InternalServerErrorException(String message) {
        super(message, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

    public InternalServerErrorException(Throwable cause) {
        super(cause, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

    public InternalServerErrorException(String message, Throwable cause) {
        super(message, cause, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
}
