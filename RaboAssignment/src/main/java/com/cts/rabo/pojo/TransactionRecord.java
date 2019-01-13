package com.cts.rabo.pojo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * @author 550072
 *
 */
public class TransactionRecord {

	public int referenceId;
	
	public String accountNumber;
	
	public String description;
	
	public double startBalance;
	
	public double mutation;
	
	public double endBalance;
	
	public TransactionStatus transactionStatus;
	
	public List<TransactionRecord> duplicateRecords;
	

	/**
	 * @return the referenceId
	 */
	public int getReferenceId() {
		return referenceId;
	}

	/**
	 * @return the duplicateRecords
	 */
	public List<TransactionRecord> getDuplicateRecords() {
		return duplicateRecords;
	}

	/**
	 * @param duplicateRecords the duplicateRecords to set
	 */
	public void setDuplicateRecords(List<TransactionRecord> duplicateRecords) {
		this.duplicateRecords = duplicateRecords;
	}

	/**
	 * @param referenceId the referenceId to set
	 */
	public void setReferenceId(int referenceId) {
		this.referenceId = referenceId;
	}

	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the startBalance
	 */
	public double getStartBalance() {
		return startBalance;
	}

	/**
	 * @param startBalance the startBalance to set
	 */
	public void setStartBalance(double startBalance) {
		this.startBalance = startBalance;
	}

	/**
	 * @return the mutation
	 */
	public double getMutation() {
		return mutation;
	}

	/**
	 * @param mutation the mutation to set
	 */
	public void setMutation(double mutation) {
		this.mutation = mutation;
	}

	/**
	 * @return the endBalance
	 */
	public double getEndBalance() {
		return endBalance;
	}

	/**
	 * @param endBalance the endBalance to set
	 */
	public void setEndBalance(double endBalance) {
		this.endBalance = endBalance;
	}
	

	/**
	 * @return the transactionStatus
	 */
	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}

	/**
	 * @param transactionStatus the transactionStatus to set
	 */
	public void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	/**
	 * @return
	 * 
	 */
	public String toCsvRow() {
	    return Stream.of(this.referenceId, this.description, this.transactionStatus,this.duplicateRecords !=null ?this.duplicateRecords.size():"-")
	            .map(value -> String.valueOf(value).replaceAll("\"", "\"\""))
	            .map(value -> Stream.of("\"", ",").anyMatch(value::contains) ? "\"" + value + "\"" : value)
	            .collect(Collectors.joining(","));
	}
	
}
