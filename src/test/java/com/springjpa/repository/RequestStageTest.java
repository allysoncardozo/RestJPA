package com.springjpa.repository;

import com.springjpa.domain.Request;
import com.springjpa.domain.RequestStage;
import com.springjpa.domain.Users;
import com.springjpa.domain.enums.eRequestState;
import com.springjpa.domain.enums.eRole;
import com.springjpa.utils.HashUtil;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RequestStageTest {

//    @Spy
//    @InjectMocks
//    private ElegibilidadeImpl service;
//
//    @Mock
//    private PedidosMapper mapper;

    @Autowired
    private RequestStageRepository repo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private RequestRepository requestRepo;
    @Test
    public void saveTest(){
        Optional<RequestStage> result = repo.findById(1L);
        Users user = userRepo.findAllUsersById(1l).orElse(null);
        if (user == null){
            user = new Users();
            user.setRole(eRole.ADMINISTRATOR);
            user.setEmail("allyson2.acs@gmail.com");
            user.setName("Allyson");
            user.setPassword(HashUtil.getSecureHash("gdqd87it"));
            userRepo.save(user);
        }
        Request request = requestRepo.findById(1L).orElse(null);
        if (request == null){
            request = new Request();
            request.setCreationDate(LocalDateTime.now());
            request.setDescription("lapTop usado á mais de 6 anos, em bom estado de conservação");
            request.setOwner(user);
            request.setState(eRequestState.OPEN);
            request.setSubject("Notebook Dell");
            requestRepo.save(request);
        }

        RequestStage req = result.orElse(null);
        if (req == null) {
            req = new RequestStage();
            req.setState(eRequestState.OPEN);
            req.setDate(LocalDateTime.now());
            req.setDescription("Estagio do Pedido 1");
            req.setOwner(user);
            req.setRequest(request);
        }
        RequestStage r = repo.save(req);
        Assertions.assertThat(r.getId()).isPositive();
    }
}
