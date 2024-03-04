package server.spring.api.common.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BaseDTO {
   // @ApiModelProperty(value = "The id of the object")
    private Long id;

   // @ApiModelProperty(value = "The date when the object was created")
    private Date creationDate;
}
