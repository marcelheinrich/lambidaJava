package com.amazonaws.lambda.demo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import com.google.gson.Gson;
import java.util.UUID;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class LambdaFunctionHandler implements RequestHandler<Object, MyLambdaResponse> {
	
    @Override
    public MyLambdaResponse handleRequest(Object input, Context context) {
    	Gson gson = new Gson();
    	String json = gson.toJson(input);
    	Pessoa pessoa = gson.fromJson(json, Pessoa.class);
    	FileWriter file = null;
    	String key_name = "teste.json";
    	String bucket_name = "marsau-image";
    			
        try {

        	System.out.format("Uploading %s to S3 bucket %s...\n", key_name, bucket_name);
        	AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.SA_EAST_1).build();
        	s3Client.putObject(bucket_name, key_name, json);
		}catch (AmazonServiceException e) {
        	System.err.println(e.getErrorMessage());
        	System.exit(1);
    	}
    	
    	context.getLogger().log("Input: ");
        MyLambdaResponse lambdaResponse = new MyLambdaResponse();
        try {
            lambdaResponse.setResponseMessage("Hello " +pessoa.getNome()+" cuja idade é: "+ pessoa.getIdade()
            +" separando a lista dos guri"+pessoa.getLista());
            lambdaResponse.setTransactionID(UUID.randomUUID().toString());
        } catch (Exception e) {
            e.printStackTrace();
            lambdaResponse.setResponseMessage(e.getMessage());
      }
        context.getLogger().log("Response : " + lambdaResponse);
        return lambdaResponse;
    }


}
