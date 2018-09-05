package com.example.walmartchallenge.data.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ItemPojo(

    @SerializedName("itemId")
    @Expose
    var itemId: Int? = null,

    @SerializedName("parentItemId")
    @Expose
    var parentItemId: Int? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("msrp")
    @Expose
    var msrp: Double? = null,

    @SerializedName("salePrice")
    @Expose
    var salePrice: Double? = null,

    @SerializedName("upc")
    @Expose
    var upc: String? = null,

    @SerializedName("categoryPath")
    @Expose
    var categoryPath: String? = null,

    @SerializedName("shortDescription")
    @Expose
    var shortDescription: String? = null,

    @SerializedName("longDescription")
    @Expose
    var longDescription: String? = null,

    @SerializedName("thumbnailImage")
    @Expose
    var thumbnailImage: String? = null,

    @SerializedName("mediumImage")
    @Expose
    var mediumImage: String? = null,

    @SerializedName("largeImage")
    @Expose
    var largeImage: String? = null,

    @SerializedName("productTrackingUrl")
    @Expose
    var productTrackingUrl: String? = null,

    @SerializedName("standardShipRate")
    @Expose
    var standardShipRate: Double? = null,

    @SerializedName("marketplace")
    @Expose
    var marketplace: Boolean? = null,

    @SerializedName("modelNumber")
    @Expose
    var modelNumber: String? = null,

    @SerializedName("sellerInfo")
    @Expose
    var sellerInfo: String? = null,

    @SerializedName("productUrl")
    @Expose
    var productUrl: String? = null,

    @SerializedName("customerRating")
    @Expose
    var customerRating: String? = null,

    @SerializedName("numReviews")
    @Expose
    var numReviews: Int? = null,

    @SerializedName("customerRatingImage")
    @Expose
    var customerRatingImage: String? = null,

    @SerializedName("categoryNode")
    @Expose
    var categoryNode: String? = null,

    @SerializedName("rhid")
    @Expose
    var rhid: String? = null,

    @SerializedName("bundle")
    @Expose
    var bundle: Boolean? = null,

    @SerializedName("stock")
    @Expose
    var stock: String? = null,

    @SerializedName("addToCartUrl")
    @Expose
    var addToCartUrl: String? = null,

    @SerializedName("affiliateAddToCartUrl")
    @Expose
    var affiliateAddToCartUrl: String? = null,

    @SerializedName("freeShippingOver35Dollars")
    @Expose
    var freeShippingOver35Dollars: Boolean? = null,

    @SerializedName("imageEntities")
    @Expose
    var imageEntities: List<ImageEntity>? = null,

    @SerializedName("offerType")
    @Expose
    var offerType: String? = null,

    @SerializedName("availableOnline")
    @Expose
    var availableOnline: Boolean? = null
)
