package cmpt213.assignment4.packagedeliveries.webappserver.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the package information such as name, notes, price, weight , delivery status and expected delivery date.
 * It implements comparable interface by defining the sorting mechanism to be on delivery date of objects.
 *
 * @author Homayoun
 */

public class Package implements Comparable<Package> {

    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) { this.name = name; }


    private String notes;
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    private double priceInDollar;
    public double getPriceInDollar() { return priceInDollar; }
    public void setPriceInDollar(double priceInDollar) { this.priceInDollar = priceInDollar; }


    private double weight;
    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }



    @JsonFormat(shape = JsonFormat.Shape.OBJECT, pattern = "yyyy-MM-dd HH:mm")
    //@JsonDeserialize(using = LocalDateTimeDeserializer.class)
    //@JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime expectedDeliveryDate;

    public LocalDateTime getExpectedDeliveryDate() { return expectedDeliveryDate; }
    public void setExpectedDeliveryDate(LocalDateTime expectedDeliveryDate) { this.expectedDeliveryDate = expectedDeliveryDate; }


    private boolean isDelivered;
    public boolean getIsDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }

    private String type;
    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }


    public Package(String name, String notes, double priceInDollar, double weight, LocalDateTime expectedDeliveryDate, String type) {
        this.name = name;
        this.notes = notes;
        this.priceInDollar = priceInDollar;
        this.weight = weight;
        this.expectedDeliveryDate = expectedDeliveryDate;
        this.isDelivered = false;
        this.type = type;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String isDeliveredtoPrintYesNo = "No";
        if (isDelivered == true)
            isDeliveredtoPrintYesNo = "Yes";

        return "Name: " + name + '\n' +
                "Notes: " + notes + '\n' +
                "Price: $" + priceInDollar + '\n' +
                "Weight: " + weight + "kg" + '\n' +
                "Expected Delivery Date: " + expectedDeliveryDate.format(formatObj) + '\n'+
                "Delivered? " + isDeliveredtoPrintYesNo + '\n'+
                "Type: " + type + '\n';
    }

    @Override
    public int compareTo(Package other) {
        LocalDateTime a = this.getExpectedDeliveryDate();
        LocalDateTime b = other.getExpectedDeliveryDate();
        return a.compareTo(b);
    }
}
