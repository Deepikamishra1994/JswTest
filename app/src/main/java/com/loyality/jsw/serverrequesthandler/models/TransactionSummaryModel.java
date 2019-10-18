package com.loyality.jsw.serverrequesthandler.models;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.List;

public class TransactionSummaryModel implements Serializable {
    private String totalPending;
    private String totalPurchase;
    private String totalApprove;
    private List<TranscationModel> transcationModelList;


    public List<TranscationModel> getTranscationModelList() {
        return transcationModelList;
    }

    public void setTranscationModelList(List<TranscationModel> transcationModelList) {
        this.transcationModelList = transcationModelList;
    }
}
