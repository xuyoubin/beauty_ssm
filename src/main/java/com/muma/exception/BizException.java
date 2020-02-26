package com.muma.exception;

import org.slf4j.helpers.MessageFormatter;

import java.text.MessageFormat;

/**
 * 
 * @author xuyb
 *
 */
public class BizException extends RuntimeException {


    private static final long serialVersionUID = 7486978056601616453L;

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message,  cause);
    }

    public BizException(String message, Object... args) {
        this(message.contains("{}") ? MessageFormatter.arrayFormat(message, args).getMessage() : new MessageFormat(message).format(args));
    }

}
