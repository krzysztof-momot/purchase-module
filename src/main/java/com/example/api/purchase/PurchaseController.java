package com.example.api.purchase;

import com.example.api.PurchaseDTO;
import com.example.application.InvalidCreditCardException;
import com.example.application.MakePurchaseAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class PurchaseController {

    private final MakePurchaseAction makePurchaseAction;

    private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseController.class);

    @Autowired
    public PurchaseController(MakePurchaseAction makePurchaseAction) {
        this.makePurchaseAction = makePurchaseAction;
    }

    //TODO: authorization
    @PostMapping(path = "/api/buy")
    @ResponseBody
    public PurchaseDTO buy(@Valid @RequestBody PurchaseRequestDTO purchaseRequest) {
        return makePurchaseAction.execute(purchaseRequest.getCreditCard(), purchaseRequest.getItems());
    }

    @ExceptionHandler(InvalidCreditCardException.class)
    public void invalidCreditCardExceptionHandler(HttpServletRequest request, InvalidCreditCardException e) {
        LOGGER.error("Request on: " + request.getRequestURL() + " raised " + e);
        throw e;
    }
}
