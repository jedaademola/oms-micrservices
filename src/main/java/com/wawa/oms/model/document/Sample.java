package com.wawa.oms.model.document;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@Document(collection = "sample")
@ApiModel(description = "Class representing a Sample Document.")
public class Sample {
    @Id
    @ApiModelProperty(example = "5aecef5b6d55754834124df3")
    public String _id;
    @Size(min = 2, max = 10, message = "User Id required")
    @ApiModelProperty(example = "U1", position = 1)
    private String userId;
    @ApiModelProperty(example = "Brandon Smith", position = 2)
    @Size(min = 1, max = 50, message = "Name must have at least 1 character")
    @NotBlank(message = "Name must not be blank!")
    private String name;
    private Date creationDate = new Date();
    private Map<String, String> userSettings = new HashMap<>();

    // Constructors
    public Sample() {
    }

    public Sample(String _id, String userId, String name, Date creationDate, Map<String, String> userSettings) {
        this._id = _id;
        this.userId = userId;
        this.name = name;
        this.creationDate = creationDate;
        this.userSettings = userSettings;
    }

}
