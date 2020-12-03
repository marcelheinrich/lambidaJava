package com.amazonaws.lambda.demo;

import java.util.Date;
import com.google.gson.Gson;
import java.util.UUID;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaFunctionHandler implements RequestHandler<Object, MyLambdaResponse> {

    @Override
    public MyLambdaResponse handleRequest(Object input, Context context) {
    	Gson gson = new Gson();
    	String json = gson.toJson(input);
    	context.getLogger().log("Input: " + input);
        MyLambdaResponse lambdaResponse = new MyLambdaResponse();
        try {
            lambdaResponse.setResponseMessage("Hello " + input);
            lambdaResponse.setTransactionID(UUID.randomUUID().toString());
        } catch (Exception e) {
            e.printStackTrace();
            lambdaResponse.setResponseMessage(e.getMessage());
        }
        context.getLogger().log("Response : " + lambdaResponse);
        return lambdaResponse;
    }


}
