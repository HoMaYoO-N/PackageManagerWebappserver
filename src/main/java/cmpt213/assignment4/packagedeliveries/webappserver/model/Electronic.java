package cmpt213.assignment4.packagedeliveries.webappserver.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class Electronic extends Package {

    double environmentalHandlingFee;

    public double getEnvironmentalHandlingFee() {
        return environmentalHandlingFee;
    }

    public void setEnvironmentalHandlingFee(double environmentalHandlingFee) {
        this.environmentalHandlingFee = environmentalHandlingFee;
    }


    @Override
    public String toString() {
        return super.toString() + "Environmental Handling Fee: " + "$" + environmentalHandlingFee + '\n';
    }

    @JsonCreator
    public Electronic(@JsonProperty("name") String name, @JsonProperty("notes") String notes, @JsonProperty("priceInDollar")double priceInDollar, @JsonProperty("weight") double weight, @JsonProperty("expectedDeliveryDate")LocalDateTime expectedDeliveryDate, @JsonProperty("type") String type,@JsonProperty("environmentalHandlingFee") double environmentalHandlingFee) {
        super(name, notes, priceInDollar, weight, expectedDeliveryDate,type);
        this.environmentalHandlingFee = environmentalHandlingFee;
    }
}
