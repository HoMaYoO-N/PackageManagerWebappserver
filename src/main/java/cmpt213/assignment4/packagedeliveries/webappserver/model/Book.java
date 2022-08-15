package cmpt213.assignment4.packagedeliveries.webappserver.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;

public class Book extends Package {

    private String authorName;

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }


    @Override
    public String toString() {
        return super.toString() + "Author: " + authorName + '\n';
    }

    @JsonCreator
    public Book(@JsonProperty("name")String name, @JsonProperty("notes")String notes, @JsonProperty("priceInDollar")double priceInDollar, @JsonProperty("weight")double weight, @JsonProperty("expectedDeliveryDate")LocalDateTime expectedDeliveryDate, @JsonProperty("type")String type, @JsonProperty("authorName")String authorName) {
        super(name, notes, priceInDollar, weight, expectedDeliveryDate,type);
        this.authorName = authorName;
    }
}
