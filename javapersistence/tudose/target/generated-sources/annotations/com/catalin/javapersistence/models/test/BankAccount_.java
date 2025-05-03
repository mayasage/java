package com.catalin.javapersistence.models.test;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(BankAccount.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class BankAccount_ extends com.catalin.javapersistence.models.test.BillingDetails_ {

	public static final String ACC_NUMBER = "accNumber";
	public static final String BANK_NAME = "bankName";
	public static final String SWIFT = "swift";

	
	/**
	 * @see com.catalin.javapersistence.models.test.BankAccount#accNumber
	 **/
	public static volatile SingularAttribute<BankAccount, String> accNumber;
	
	/**
	 * @see com.catalin.javapersistence.models.test.BankAccount#bankName
	 **/
	public static volatile SingularAttribute<BankAccount, String> bankName;
	
	/**
	 * @see com.catalin.javapersistence.models.test.BankAccount
	 **/
	public static volatile EntityType<BankAccount> class_;
	
	/**
	 * @see com.catalin.javapersistence.models.test.BankAccount#swift
	 **/
	public static volatile SingularAttribute<BankAccount, String> swift;

}

