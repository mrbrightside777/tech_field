package com.example.walmartchallenge.data.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SearchPojo(@SerializedName("query")
                      @Expose
                      var query: String? = null,

                      @SerializedName("sort")
                      @Expose
                      var sort: String? = null,

                      @SerializedName("responseGroup")
                      @Expose
                      var responseGroup: String? = null,

                      @SerializedName("totalResults")
                      @Expose
                      var totalResults: Int? = null,

                      @SerializedName("start")
                      @Expose
                      var start: Int? = null,

                      @SerializedName("numItems")
                      @Expose
                      var numItems: Int? = null,

                      @SerializedName("items")
                      @Expose
                      var items: List<ItemPojo>? = null): Serializable
