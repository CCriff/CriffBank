package com.criff.models;

public class Account {
	private int acctID;
	private String type;
	private String currency;
	private float balance;
	private boolean acctStatus = false;
	
	

	public boolean isAcctStatus() {
		return acctStatus;
	}
	public void setAcctStatus(boolean acctStatus) {
		this.acctStatus = acctStatus;
	}
	public int getAcctID() {
		return acctID;
	}
	public void setAcctID(int acctID) {
		this.acctID = acctID;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}

	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "Account [acctID=" + acctID + ", balance=" + balance + ", currency=" + currency + ", type=" + type
				+ ", acctStatus=" + acctStatus + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + acctID;
		result = prime * result + (acctStatus ? 1231 : 1237);
		long temp;
		temp = Float.floatToIntBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (acctID != other.acctID)
			return false;
		if (acctStatus != other.acctStatus)
			return false;
		if (Float.floatToIntBits(balance) != Float.floatToIntBits(other.balance))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Account(int acctID, String type, String currency, float balance, boolean acctStatus) {
		super();
		this.acctID = acctID;
		this.type = type;
		this.currency = currency;
		this.balance = balance;
		this.acctStatus = acctStatus;
	}
	
	
}
