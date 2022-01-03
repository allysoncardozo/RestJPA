package com.springjpa.service;

import com.springjpa.domain.Request;
import com.springjpa.domain.RequestStage;
import com.springjpa.domain.abstratas.ABaseDomain;
import com.springjpa.domain.enums.eRequestState;
import com.springjpa.exception.NotFoundException;
import com.springjpa.model.PageModel;
import com.springjpa.model.PageRequestModel;
import com.springjpa.repository.RequestRepository;
import com.springjpa.repository.RequestStageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RequestStageService {

    private final RequestStageRepository repo;

    private final RequestRepository reqRepo;

    @Transactional
    public RequestStage save(RequestStage stage){
        stage.setState(eRequestState.OPEN);
        RequestStage created = repo.save(stage);

        Long requestId = created.getRequest().getId();
        reqRepo.updateRequestState(requestId, stage.getState().getValor());

        return created;
    }

    public RequestStage update(RequestStage stage){
        stage.setState(eRequestState.IN_PROGRESS);
        return repo.save(stage);
    }

    public RequestStage getbyId(Long id){
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Request Stage não encontrado com o id: " + id.toString()));
    }

    public PageModel<RequestStage> listAllByRequestId(Long requestId, PageRequestModel pr){
        Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize());

        Page<RequestStage> page = repo.findAllByRequestId(requestId, pageable);

        PageModel<RequestStage> pm = new PageModel();
        pm.montarObjetoPelaPagina(page);

        return pm;
    }

    public PageModel<RequestStage> listAll(PageRequestModel pr) {
        Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize());
        Page<RequestStage> page = repo.findAll(pageable);

        PageModel<RequestStage> pm = new PageModel();
        pm.montarObjetoPelaPagina(page);
        return pm;
    }
}
