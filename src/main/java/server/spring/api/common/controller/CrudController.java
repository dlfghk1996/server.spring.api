package server.spring.api.common.controller;


import java.util.Optional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import server.spring.api.common.dto.BaseDTO;
import server.spring.api.common.dto.Response;
import server.spring.api.common.enums.ResponseCode;
import server.spring.api.common.service.CrudService;


public abstract class CrudController<T extends BaseDTO> {

    private CrudService<T> service;

    public CrudController(CrudService<T> crudService){
        this.service = crudService;
    }

    @PostMapping("/")
    //@ApiOperation(value = "Create a new one")
    public Response<T> save(@RequestBody T body){

        return new Response<>(service.save(body), ResponseCode.OK);
    }


    @GetMapping("/")
    //@ApiOperation(value = "List all")
    public Response<T> getAll(){
        return new Response<>(service.findAll(), ResponseCode.OK);
    }

    @GetMapping("{id}")
    //@ApiOperation(value = "Get by Id")
    public Response<T> getById(@PathVariable Long id){
        Optional<T> optionalT = service.findById(id);
        return optionalT.map(T ->
                        new Response<>(T, ResponseCode.OK))
                .orElse(new Response<>(null, ResponseCode.OK));
    }


    @DeleteMapping("/{id}")
    //@ApiOperation(value = "Delete by Id")
    public Response<String> delete(@PathVariable Long id){
        Optional<T> optional = service.findById(id);
        return optional.map(T ->
                        new Response<>("Object with the id " + id + " was deleted.", ResponseCode.OK))
                .orElse(new Response<>(ResponseCode.ERROR_NO_MATCH_REQUEST_PARAM.toString(), ResponseCode.OK));
    }
}
