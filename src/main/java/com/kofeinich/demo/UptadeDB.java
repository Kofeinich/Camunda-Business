package com.kofeinich.demo;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class UptadeDB implements JavaDelegate{
    @Value("${maxTrades}")
    private int maxTrades;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        int successTrades = (int) delegateExecution.getVariable("successTrades");
        int unSuccessTrades = (int) (Math.random() * 100);
        
        maxTrades = maxTrades == 0 ? 100 : maxTrades;

        if (successTrades < 1 || successTrades > maxTrades) {
            throw new BpmnError("tradeError");
        }

        List<Boolean> trades_collection = new ArrayList<>(Collections.nCopies(successTrades, true));

        System.out.println("Prepare to trading! Unsuccessful trades = " + unSuccessTrades + " Successful trades: " + successTrades);

        delegateExecution.setVariable("trades_collection", trades_collection);
        delegateExecution.setVariable("unSuccessTrades", unSuccessTrades);

    }
}
