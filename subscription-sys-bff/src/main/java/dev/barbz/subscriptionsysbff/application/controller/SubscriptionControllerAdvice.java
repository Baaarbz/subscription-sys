package dev.barbz.subscriptionsysbff.application.controller;

import dev.barbz.subscriptionsysbff.application.response.ErrorResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static java.util.Objects.isNull;

@ControllerAdvice
public record SubscriptionControllerAdvice() {

    @ResponseBody
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        Class<?> type = e.getRequiredType();
        String error;
        String detailedMessage;

        if (isNull(type)) {
            error = "NULL_PARAMETER";
            detailedMessage = String.format("Param: %s can not be null", e.getName());
        } else if (type.isEnum()) {
            error = "NOT_ENUM_VALUE";
            String enumValues = StringUtils.join(type.getEnumConstants(), ", ");
            detailedMessage = String.format("Param: %s must have a value among: %s", e.getName(), enumValues);
        } else {
            error = "WRONG_TYPE_PARAMETER";
            detailedMessage = String.format("Param: %s must be of type %s", e.getName(), type.getTypeName());
        }

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ErrorResponse(error, detailedMessage));
    }

    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        String error = "INVALID_BODY_REQUEST";
        String detailedMessage = e.getMessage();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(error, detailedMessage));
    }
}
