
package com.example.googleplacesassignment.Data.Pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AutoCompleteResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("predictions")
    @Expose
    private List<Prediction> predictions = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Prediction> getPredictions() {
        return predictions;
    }

    public void setPredictions(List<Prediction> predictions) {
        this.predictions = predictions;
    }

}
