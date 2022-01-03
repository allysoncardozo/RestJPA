package com.springjpa.resource;

import com.springjpa.domain.RequestStage;
import com.springjpa.model.PageModel;
import com.springjpa.model.PageRequestModel;
import com.springjpa.service.RequestStageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/requestStages")
public class RequestStageResource {

    private final RequestStageService service;

    @GetMapping
    public ResponseEntity<PageModel<RequestStage>> listAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                                           @RequestParam(value = "size", defaultValue = "10") int size){
        PageRequestModel pr = new PageRequestModel();
        pr.setPage(page);
        pr.setSize(size);

        PageModel<RequestStage> pm = service.listAll(pr);

        return ResponseEntity.ok(pm);
    }
    @PostMapping
    public ResponseEntity<RequestStage> save(@RequestBody @Valid RequestStage requestStage){
        RequestStage created = service.save(requestStage);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestStage> getById(@PathVariable(value = "id")Long id){
        RequestStage requestStage = service.getbyId(id);
        return ResponseEntity.ok(requestStage);
    }
}
