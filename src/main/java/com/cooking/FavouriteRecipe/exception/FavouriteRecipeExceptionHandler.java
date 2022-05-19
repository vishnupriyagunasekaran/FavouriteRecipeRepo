package com.cooking.FavouriteRecipe.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.jsonwebtoken.ExpiredJwtException;

import java.util.stream.Collectors;

@ControllerAdvice
public class FavouriteRecipeExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

    	ExceptionHandlerResponse exceptionResponse = new ExceptionHandlerResponse("Input validation failed",
                ex.getBindingResult().getFieldErrors()
                        .stream()
                        .map(x -> x.getDefaultMessage())
                        .collect(Collectors.toList()));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    	ExceptionResponse exceptionBody = new ExceptionResponse("Input validation failed", ex.getMessage());
        return new ResponseEntity<>(exceptionBody, HttpStatus.BAD_REQUEST);
    }
    
    /**
     * This method is used to send as a response with expired jwt message
     * @param ExpiredJwtException
     * @return This returns the Bad request with exception details
     */
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Object> handleExpiredJwtException(ExpiredJwtException expiredJwtException) {
    	ExceptionResponse exceptionBody = new ExceptionResponse("JWT Token Expired", expiredJwtException.getMessage());
        return new ResponseEntity<>(exceptionBody, HttpStatus.BAD_REQUEST);

    }

    /**
     * This method is used to send as a response with serice exception message
     * @param ServiceException
     * @return This returns the Internal Server Error with exception details
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Object> handlePersistFailureException(ServiceException ServiceException) {
    	ExceptionResponse exceptionBody = new ExceptionResponse("INTERNAL_SERVER_ERROR", ServiceException.getErrorMessage());
        return new ResponseEntity<>(exceptionBody, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    /**
     * This method is used to send as a response with recipe not found message
     * @param recipeNotFoundException
     * @return This returns the Not found with exception details
     */
    @ExceptionHandler(RecipeNotFoundException.class)
    public ResponseEntity<Object> handleNoDataFoundException(RecipeNotFoundException recipeNotFoundException) {
    	ExceptionResponse exceptionBody = new ExceptionResponse("RECIPE NOT FOUND FOR THE SEARCH PARAMETER", recipeNotFoundException.getExceptionMessage());
        return new ResponseEntity<>(exceptionBody, HttpStatus.NOT_FOUND);

    }

}
