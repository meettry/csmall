package cn.tedu.csmall.commons.exception;

import cn.tedu.csmall.commons.restful.ResponseCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 业务异常
 *
 * @author Meettry
 * @date 2022/10/21 14:40
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CoolSharkServiceException extends RuntimeException {

    private ResponseCode responseCode;

    public CoolSharkServiceException(ResponseCode responseCode, String message) {
        super(message);
        setResponseCode(responseCode);
    }
}
