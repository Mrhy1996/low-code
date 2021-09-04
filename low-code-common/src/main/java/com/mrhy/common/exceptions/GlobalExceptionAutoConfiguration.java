package com.mrhy.common.exceptions;

import com.mrhy.common.enums.BusinessException;
import com.mrhy.common.enums.ObjectResponse;
import com.mrhy.common.enums.OperationFlag;
import com.mrhy.common.properties.GlobalExceptionProperties;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author cooper
 * @description 全局异常拦截
 * @date 2021/7/7 2:08 下午
 */
@ControllerAdvice
@Log4j2
@EnableConfigurationProperties(GlobalExceptionProperties.class)
@ConditionalOnWebApplication
@ConditionalOnProperty(prefix = "global.exception", value = "enabled", havingValue = "true")
public class GlobalExceptionAutoConfiguration {

    public GlobalExceptionAutoConfiguration() {
        log.info("======globalExceptionHandler init======");
    }

    /**
     * @description 业务异常
     * @author cooper
     * @date 2021/7/7 2:14 下午
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ObjectResponse businessExceptionHandler(BusinessException e) {
        log.error("业务异常", e);
        return new ObjectResponse(e.getErrorCode(), e.getMessage());
    }

    /**
     * @description 参数异常
     * @author cooper
     * @date 2021/7/7 2:14 下午
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ObjectResponse illegalArgumentExceptionHandler(IllegalArgumentException e) {
        log.error("参数异常", e);
        return new ObjectResponse(OperationFlag.ILLEGAL_ARGUMENT.getReturnCode(), "传参不正确:" + e.getMessage());
    }

    /**
     * @description 空指针异常
     * @author cooper
     * @date 2021/7/7 2:15 下午
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public ObjectResponse nullPointerExceptionHandler(NullPointerException e) {
        log.error("空指针异常", e);
        return new ObjectResponse(OperationFlag.ILLEGAL_ARGUMENT.getReturnCode(), "空指针异常:" + e.getMessage());
    }

    /**
     * @description 方法违法
     * @author cooper
     * @date 2021/7/7 2:16 下午
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public ObjectResponse paramExceptionHandler(MethodArgumentNotValidException e) {
        BindingResult exceptions = e.getBindingResult();
        ObjectResponse objectResponse = new ObjectResponse();
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
                FieldError fieldError = (FieldError) errors.get(0);
                objectResponse.setDescription(fieldError.getDefaultMessage());
                objectResponse.setReturnCode(OperationFlag.ILLEGAL_ARGUMENT.getReturnCode());
            }
        }
        return objectResponse;

    }


}