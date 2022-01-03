package com.springjpa.resource;

import com.springjpa.domain.Request;
import com.springjpa.domain.RequestStage;
import com.springjpa.model.PageModel;
import com.springjpa.model.PageRequestModel;
import com.springjpa.service.RequestService;
import com.springjpa.service.RequestStageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Pageable;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/requests")
public class RequestResource {
    private final RequestService service;
    private final RequestStageService requestStageService;

    @PostMapping
    public ResponseEntity<Request> save(@RequestBody @Valid Request request){
        Request createdRequest = service.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRequest);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Request> update(@PathVariable(name = "id") Long id, @RequestBody @Valid Request request){
        request.setId(id);
        Request updated = service.update(request);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Request> getById(@PathVariable(name = "id") Long id){
        Request request = service.findById(id);
        return ResponseEntity.ok(request);
    }
    @GetMapping
    public  ResponseEntity<PageModel<Request>> listAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                                       @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequestModel pr = new PageRequestModel();
        pr.setSize(size);
        pr.setPage(page);

        PageModel<Request> pm = service.listAll(pr);
        return ResponseEntity.ok(pm);
    }

    @GetMapping("/{id}/resquestStages")
    public ResponseEntity<PageModel<RequestStage>> listAllRequestStagesByRequestId(@PathVariable(value = "id")Long id,
                                                                                  @RequestParam(value = "page") int page,
                                                                                  @RequestParam(value = "size") int size){
        PageRequestModel pr = new PageRequestModel();
        pr.setPage(page);
        pr.setSize(size);

        PageModel<RequestStage> pm = requestStageService.listAllByRequestId(id, pr);

        return ResponseEntity.ok(pm);
    }
}
