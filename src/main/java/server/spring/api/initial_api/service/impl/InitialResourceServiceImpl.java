package server.spring.api.initial_api.service.impl;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import server.spring.api.common.enums.ResponseCode;
import server.spring.api.common.exception.CustomException;
import server.spring.api.common.service.AbstractRevisionServiceImpl;
import server.spring.api.initial_api.domain.InitialResource;
import server.spring.api.initial_api.dto.InitialResourceDTO;
import server.spring.api.initial_api.dto.InitialResourceDTO.InitialResourceRequest;
import server.spring.api.initial_api.repository.InitialResourceRepository;
import server.spring.api.initial_api.service.InitialResourceService;

@Slf4j
@Service
//@RequiredArgsConstructor
public class InitialResourceServiceImpl extends AbstractRevisionServiceImpl<
    InitialResource,
    Long,
    InitialResourceRepository,
    Long,
    InitialResourceRequest,
    InitialResourceDTO> implements InitialResourceService {

    public InitialResourceServiceImpl(InitialResourceRepository repository,
        InitialResourceRepository repository1) {
        super(repository);
    }


    @Override
    public InitialResourceDTO disableData(InitialResourceRequest request) {
        InitialResource temp = find(request.getId());
        if (Objects.nonNull(temp)) {
            if (temp.isDisabled()) {
                log.debug("Enable initialResource : " + request);
                temp = enable(temp);
            } else {
                log.debug("Disable initialResource : " + request);
                temp = disable(temp);
            }
            return mapper.map(temp, InitialResourceDTO.class);
        } else {
            throw new CustomException(ResponseCode.RESULT_NOT_FOUND);
        }
    }

}
