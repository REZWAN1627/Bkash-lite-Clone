package com.rex.bkashliteclone.Model.Inbox;

public class TransactionModel {
    //    private Uri image;
    private String transactionProcessName;
    private String transactionOwnerNumber;
    private String transactionID;
    private String transactionDate;
    private String transactionAmount;

    public String getTransactionProcessName() {
        return transactionProcessName;
    }

    public void setTransactionProcessName(String transactionProcessName) {
        this.transactionProcessName = transactionProcessName;
    }

    public String getTransactionOwnerNumber() {
        return transactionOwnerNumber;
    }

    public void setTransactionOwnerNumber(String transactionOwnerNumber) {
        this.transactionOwnerNumber = transactionOwnerNumber;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public TransactionModel(String transactionProcessName, String transactionOwnerNumber, String transactionID, String transactionDate, String transactionAmount) {
        this.transactionProcessName = transactionProcessName;
        this.transactionOwnerNumber = transactionOwnerNumber;
        this.transactionID = transactionID;
        this.transactionDate = transactionDate;
        this.transactionAmount = transactionAmount;
    }
}
