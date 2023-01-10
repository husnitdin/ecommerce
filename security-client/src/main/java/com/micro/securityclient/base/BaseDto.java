package com.micro.securityclient.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Data
public class BaseDto implements Serializable {

    @Setter(onMethod = @__(@JsonIgnore))
    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd'T'HH:mm:ssZ",
            timezone = "UTC")
    private Instant createdDate;

    @Setter(onMethod = @__(@JsonIgnore))
    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd'T'HH:mm:ssZ",
            timezone = "UTC")
    private Instant updatedDate;
}
