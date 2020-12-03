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
    	Pessoa pessoa = gson.fromJson(json, Pessoa.class);
    	
    	context.getLogger().log("Input: ");
        MyLambdaResponse lambdaResponse = new MyLambdaResponse();
        try {
            lambdaResponse.setResponseMessage("Hello " +pessoa.getNome()+" cuja idade é: "+ pessoa.getIdade());
            lambdaResponse.setTransactionID(UUID.randomUUID().toString());
        } catch (Exception e) {
            e.printStackTrace();
            lambdaResponse.setResponseMessage(e.getMessage());
        }
        context.getLogger().log("Response : " + lambdaResponse);
        return lambdaResponse;
    }


}
