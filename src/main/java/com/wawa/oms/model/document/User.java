package com.wawa.oms.model.document;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Document(collection = "user")
@ApiModel(description = "Class representing a User Document.")
public class User {

    // Constructors
    public User() {}

    public User(String _id, String userId, String name, Date creationDate, Map<String, String> userSettings) {
        this._id = _id;
        this.userId = userId;
        this.name = name;
        this.creationDate = creationDate;
        this.userSettings = userSettings;
    }

    @Id
    @ApiModelProperty(example = "5aecef5b6d55754834124df3")
    public String _id;
    //@Indexed
    @Size(min = 2, max = 10, message = "User Id required")
    @ApiModelProperty(example = "U1", position = 1)
    private String userId;
    @ApiModelProperty(example = "Brandon Smith", position = 2)
    @Size(min = 1, max = 50, message = "Name must have at least 1 character")
    @NotBlank(message = "Name must not be blank!")
    private String name;
    //@ApiModelProperty(example = "2019-11-18 00:00:00", position = 2)
    //@Field("createdDate")
    private Date creationDate = new Date();
   /* @ApiModelProperty(example =
            "{"
             + "gender:" + "Female,"
             + "age:" + "23"
             + "}", position = 3)*/
    private Map<String, String> userSettings = new HashMap<>();

   // @DBRef(db = "address")
    //private List<Address> addresses = new ArrayList<>();

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Map<String, String> getUserSettings() {
        return userSettings;
    }

    public void setUserSettings(Map<String, String> userSettings) {
        this.userSettings = userSettings;
    }

    public String get_id() {
        return _id;
    }

    //@JsonProperty
    public void set_id(String _id) {
        this._id = _id;
    }

   /* public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }*/
}
