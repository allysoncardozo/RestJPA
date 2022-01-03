package com.springjpa.repository;

import com.springjpa.domain.Request;
import com.springjpa.domain.Users;
import com.springjpa.domain.enums.eRequestState;
import com.springjpa.domain.enums.eRole;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RequestRepositoryTest {

//    @Spy
//    @InjectMocks
//    private ElegibilidadeImpl service;
//
//    @Mock
//    private PedidosMapper mapper;

    @Autowired
    private RequestRepository repo;
    @Autowired
    private UserRepository userRepo;

    @Test
    public void saveTest(){
        Optional<Request> result = repo.findById(1L);
        Users user = userRepo.findAllUsersById(1l).orElse(null);
        Request req = result.orElse(null);
        if (req == null) {
            req = new Request();
            req.setCreationDate(LocalDateTime.now());
            req.setDescription("lapTop usado á mais de 6 anos, em bom estado de conservação");
            req.setOwner(user);
            req.setState(eRequestState.OPEN);
            req.setSubject("Notebook Dell");
        }
        Request r = repo.save(req);
        Assertions.assertThat(r.getId()).isPositive();
    }

    @Test
    public void updateTest() {
        Optional<Request> result = repo.findById(1L);
        Users user = userRepo.findAllUsersById(1l).orElse(null);
        Request req = result.orElse(null);
        req.setDescription("NoteBook pessoal usado á mais de 6 anos, em bom estado de conservação");
        req.setOwner(user);

        Request r = repo.save(req);
        Assertions.assertThat(r.getId()).isPositive();
    }

    @Test
    public void findAllByOwnerTest() {
        Users user = userRepo.findAllUsersById(1l).orElse(null);
        List<Request> result = repo.findAllByOwner(user);
        Assertions.assertThat(result.isEmpty()).isFalse();
    }
}
