package com.kofeinich.demo;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Random;

@Component
public class PleaseTrading implements JavaDelegate{
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        ArrayList<Boolean> trades_collection = (ArrayList<Boolean>) delegateExecution.getVariable("trades_collection");
        int unSuccessTrades = (int) delegateExecution.getVariable("unSuccessTrades");

        Thread.sleep(2000);

        if ( new Random().nextBoolean() ) {
            unSuccessTrades--;
            System.out.println("Trades complete with errors!");
        } else {
            trades_collection.remove(trades_collection.size() - 1);
            System.out.println("Trades complete correctly!");
        }
        delegateExecution.setVariable("unSuccessTrades", unSuccessTrades);
        delegateExecution.setVariable("successTrades", trades_collection.size());
        delegateExecution.setVariable("trades_collection", trades_collection);
    }
}
