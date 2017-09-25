package com.sun3toline.mybakingapps.Model;

/**
 * Created by coldware on 9/24/17.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Bahan {
    @SerializedName("quantity")
    @Expose
    private Double quantity;
    @SerializedName("measure")
    @Expose
    private String measure;
    @SerializedName("ingredient")
    @Expose
    private String ingredient;

    public Bahan(Double quantity, String measure, String ingredient){
        super();
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }
    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
}
