
package com.example.halcyonjuly7.amazonmaterialweekend.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SeriesInfo {

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("bookDisplayNumber")
    @Expose
    private String bookDisplayNumber;
    @SerializedName("volumeSeries")
    @Expose
    private List<VolumeSeries> volumeSeries = null;
    @SerializedName("shortSeriesBookTitle")
    @Expose
    private String shortSeriesBookTitle;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getBookDisplayNumber() {
        return bookDisplayNumber;
    }

    public void setBookDisplayNumber(String bookDisplayNumber) {
        this.bookDisplayNumber = bookDisplayNumber;
    }

    public List<VolumeSeries> getVolumeSeries() {
        return volumeSeries;
    }

    public void setVolumeSeries(List<VolumeSeries> volumeSeries) {
        this.volumeSeries = volumeSeries;
    }

    public String getShortSeriesBookTitle() {
        return shortSeriesBookTitle;
    }

    public void setShortSeriesBookTitle(String shortSeriesBookTitle) {
        this.shortSeriesBookTitle = shortSeriesBookTitle;
    }

}
