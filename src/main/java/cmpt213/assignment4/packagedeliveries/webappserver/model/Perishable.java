package cmpt213.assignment4.packagedeliveries.webappserver.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Perishable extends Package {
    @JsonFormat(shape = JsonFormat.Shape.OBJECT, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime expiryDate;

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return super.toString() + "Expiry Date: " + expiryDate.format(formatObj) + '\n';
    }

    public Perishable(@JsonProperty("name") String name, @JsonProperty("notes") String notes, @JsonProperty("priceInDollar") double priceInDollar, @JsonProperty("weight") double weight, @JsonProperty("expectedDeliveryDate") LocalDateTime expectedDeliveryDate, @JsonProperty("type") String type, @JsonProperty("expiryDate") LocalDateTime expiryDate) {
        super(name, notes, priceInDollar, weight, expectedDeliveryDate,type);
        this.expiryDate = expiryDate;
    }
}
