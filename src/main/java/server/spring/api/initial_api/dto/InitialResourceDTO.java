package server.spring.api.initial_api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import server.spring.api.common.domain.vo.AbstractData;

@EqualsAndHashCode(callSuper = true)
@Data
public class InitialResourceDTO extends AbstractData {

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class InitialResourceRequest extends AbstractData {


    }
}