package com.amazonaws.lambda.demo;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DeleteItemOutcome;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;

public class Pessoa {
	
	long codigo_marcel;
	String nome;
	String idade;
	List<String> lista = new ArrayList<>();
	DynamoDB dynamoDB;
	
	public String DYNAMO_DB_TABLE_NAME = "testeMarcel";
	
	private void initDynamoDbClient() {
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.SA_EAST_1).build();
    	 this.dynamoDB = new DynamoDB(client);	
		 }
	
	//POST
	public PutItemOutcome persistData(Pessoa pessoa) {
		initDynamoDbClient();  
		Table table = dynamoDB.getTable(DYNAMO_DB_TABLE_NAME);
		  PutItemOutcome outcome = table.putItem(new PutItemSpec().withItem(
		    new Item().withLong("codigo_marcel", pessoa.getId())
		               .withString("nome", pessoa.getNome())
		               .withString("idade", pessoa.getIdade())));
		  return outcome;
		 }
	
	//GET
	public String getItem(Pessoa pessoa) {
		initDynamoDbClient(); 
		Table table = dynamoDB.getTable(DYNAMO_DB_TABLE_NAME);
		GetItemSpec spec = new GetItemSpec()
			    .withPrimaryKey("codigo_marcel", pessoa.getId())
			    .withProjectionExpression("codigo_marcel, nome, idade")
			    .withConsistentRead(true);
		Item item = table.getItem(spec);
		return item.toJSON();
	}
	
	//DELETE
	public void deleteItem(Pessoa pessoa) {
		initDynamoDbClient();
		Table table = dynamoDB.getTable("DYNAMO_DB_TABLE_NAME");
		DeleteItemOutcome outcome = table.deleteItem("codigo_marcel", pessoa.getId());
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getIdade() {
		return idade;
	}
	public void setIdade(String idade) {
		this.idade = idade;
	}
	
	public List<String> getLista() {
		return lista;
	}
	
	public void setLista(List<String> lista) {
		this.lista = lista;
	}

	public long getId() {
		return codigo_marcel;
	}

	public void setId(long id) {
		this.codigo_marcel = id;
	}
	
	
	
}
