package com.rightit.taxibook.repository.spec;

import static com.mongodb.client.model.Filters.eq;

import org.bson.conversions.Bson;

public class FindByEmailAddressSpecification implements MongoSpecification {

	private String emailAddress = null;
	
	public FindByEmailAddressSpecification(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	@Override
	public Bson toMongoQuery() {
		return eq("emailAddress", emailAddress);
	}

}
