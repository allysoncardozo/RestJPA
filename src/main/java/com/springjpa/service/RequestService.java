package com.springjpa.service;

import com.springjpa.domain.Request;
import com.springjpa.domain.RequestStage;
import com.springjpa.domain.Users;
import com.springjpa.domain.abstratas.ABaseDomain;
import com.springjpa.domain.enums.eRequestState;
import com.springjpa.exception.NotFoundException;
import com.springjpa.model.PageModel;
import com.springjpa.model.PageRequestModel;
import com.springjpa.repository.RequestRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RequestService {

    private final RequestRepository repo;

    public Request save(Request request){
        request.setState(eRequestState.OPEN);
        request.setCreationDate(LocalDateTime.now());

        return repo.save(request);
    }
    public Request update(Request request){
        return repo.save(request);
    }
    public Request findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Request não encontrado com o id: " + id.toString()));
    }

    public PageModel<Request> listAll(PageRequestModel pr){
        PageRequest pageabLe = PageRequest.of(pr.getPage(),pr.getSize());
        Page<Request> page = repo.findAll(pageabLe);

        PageModel<Request> pm = new PageModel();
        pm.montarObjetoPelaPagina(page);

        return pm;
    }

    public List<Request> listAllByOwnerId(Long ownerId){
        return repo.findAllByOwnerId(ownerId);
    }

    public PageModel<Request> listAllByOwnerIdOnLazyModel(Long ownerId, PageRequestModel pr){
        PageRequest pageabLe = PageRequest.of(pr.getPage(),pr.getSize());

        Page<Request> page = repo.findAllByOwnerId(ownerId, pageabLe);

        PageModel<Request> pm = new PageModel();
        pm.montarObjetoPelaPagina(page);

        return pm;
    }

    public List<Request> listAllByOwner(Users owner){
        return repo.findAllByOwner(owner);
    }
}
