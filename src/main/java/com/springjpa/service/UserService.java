package com.springjpa.service;

import com.springjpa.domain.Users;
import com.springjpa.exception.NotFoundException;
import com.springjpa.model.PageModel;
import com.springjpa.model.PageRequestModel;
import com.springjpa.repository.UserRepository;
import com.springjpa.utils.HashUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repo;

    public Users save(Users user){
        String hash = HashUtil.getSecureHash(user.getPassword());
        user.setPassword(hash);
        return repo.save(user);
    }

    public Users update(Users user){
        return repo.save(user);
    }

    public Users getById(Long id){
        return repo.findAllUsersById(id).orElseThrow(() -> new NotFoundException("Usuário não encontrado com o id: " + id.toString()));
    }

    public List<Users> getList(){
        return repo.findAll();
    }

    public PageModel<Users> listAllOnLazyLoading(PageRequestModel pr){
        PageRequest pageable = PageRequest.of(pr.getPage(), pr.getSize());

        Page<Users> page = repo.findAll(pageable);

        PageModel<Users> pm = new PageModel();
        pm.montarObjetoPelaPagina(page);

        return pm;
    }

    public Users login(String email, String pass){
        pass = HashUtil.getSecureHash(pass);
        return repo.getUsersByEmailAndPassword(email, pass).orElse(null);
    }


}
