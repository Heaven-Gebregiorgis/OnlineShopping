package com.solvd.OnlineShopping.model;

import com.solvd.OnlineShopping.model.enums.TransactionStatus;

public class Transaction {

	
	private int id;
	private Order order;
	private Wallet wallet;
	private TransactionStatus status;
	
	
	
	public Transaction() {}
	public Transaction(int id, Order order, Wallet wallet, TransactionStatus status) {
		super();
		this.id = id;
		this.order = order;
		this.wallet = wallet;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Wallet getWallet() {
		return wallet;
	}
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	public TransactionStatus getStatus() {
		return status;
	}
	public void setStatus(TransactionStatus status) {
		this.status = status;
	}
	
	
}
