package server.spring.api.initial_api.service;

import server.spring.api.common.service.AbstractRevisionService;
import server.spring.api.initial_api.domain.InitialResource;
import server.spring.api.initial_api.dto.InitialResourceDTO;
import server.spring.api.initial_api.dto.InitialResourceDTO.InitialResourceRequest;
import server.spring.api.initial_api.repository.InitialResourceRepository;

///**
// * E : entity
// * P : Primary Key
// * R : Repository
// * N : Revision Number
// * RO : request
// * DO : DTO
// * */
public interface InitialResourceService
    extends
    AbstractRevisionService<InitialResource, Long, InitialResourceRepository, Long, InitialResourceRequest, InitialResourceDTO> {

    InitialResourceDTO disableData(InitialResourceRequest request);
}
