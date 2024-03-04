package server.spring.api.initial_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Parameter;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.spring.api.common.dto.Response;
import server.spring.api.common.enums.ResponseCode;
import server.spring.api.common.util.BaseUtils;
import server.spring.api.initial_api.domain.InitialResource;
import server.spring.api.initial_api.domain.QInitialResource;
import server.spring.api.initial_api.dto.InitialResourceDTO;
import server.spring.api.initial_api.dto.InitialResourceDTO.InitialResourceRequest;
import server.spring.api.initial_api.service.InitialResourceService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/initialResource")
public class InitialResourceController {

    private final ModelMapper mapper;

    private final InitialResourceService service;
    @GetMapping("{id}")
    public Response<InitialResourceDTO> get(@PathVariable Long id) {

        log.debug("Get InitialResource : id = " + id);
       // InitialResourceDTO data = service.getData(id);
        return new Response<>(null, ResponseCode.OK);
    }

    @PostMapping("")
    public Response<InitialResourceDTO> get(@RequestBody InitialResourceDTO request) {

        System.out.println(request.toString());
        return new Response<>(null, ResponseCode.OK);
    }
}
