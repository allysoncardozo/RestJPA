package com.springjpa;

import com.springjpa.domain.Request;
import com.springjpa.domain.RequestStage;
import com.springjpa.domain.Users;
import com.springjpa.domain.enums.eRequestState;
import com.springjpa.domain.enums.eRole;
import com.springjpa.repository.RequestRepository;
import com.springjpa.repository.RequestStageRepository;
import com.springjpa.repository.UserRepository;
import com.springjpa.utils.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class SpringJpaApplication  implements CommandLineRunner {

	@Autowired
	UserRepository repouUsuario;
	@Autowired
	RequestRepository repoRequest;
	@Autowired
	RequestStageRepository repoStage;

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//iniciarUsuario();
		//iniciarRequests();
		//iniciarRequestsStages();
	}

	private void iniciarUsuario() {
		List<Users> users = new ArrayList<>();
		for (int i = 40; i <= 100000; i++){
			Users user = new Users();
			user.setRole(eRole.ADMINISTRATOR);
			user.setEmail("allyson" + i + ".acs@gmail.com");
			user.setName("allyson" + i);
			user.setPassword(HashUtil.getSecureHash("vxh8jkdq"));
			users.add(user);
		}
		repouUsuario.saveAll(users);
	}

	private void iniciarRequests() {
		List<Request> requests = new ArrayList<>();
		for (Long i = 40L; i <= 100000L; i++){
			Users u = repouUsuario.findById(i).orElse(null);
			if (u == null)
				continue;

			Request req = new Request();
			req.setCreationDate(LocalDateTime.now());
			req.setDescription("lapTop automatico do usuario com id: " + i);
			req.setOwner(u);
			req.setState(eRequestState.OPEN);
			req.setSubject("LapTop");
			requests.add(req);
		}
		repoRequest.saveAll(requests);
	}

	private void iniciarRequestsStages() {
		List<RequestStage> requestsStages = new ArrayList<>();

		List<Users> users = repouUsuario.findTop2000ByOrderByIdDesc();

		List<Request> requests = repoRequest.findTop2000By();

		int i = 0;
		for (Users u : users) {
			for (Request r : requests) {
					RequestStage req = new RequestStage();
					req.setState(eRequestState.OPEN);
					req.setDate(LocalDateTime.now());
					req.setDescription("Estágio " + i);
					req.setOwner(u);
					req.setRequest(r);
					requestsStages.add(req);
					i++;
			}
		}
		int pagina = 0;
		int quantidade = 1000;
		List<RequestStage> lowerList = new ArrayList<>();
		do{
			lowerList = requestsStages.stream().skip(pagina).limit(quantidade).collect(Collectors.toList());

			if (lowerList.isEmpty())
				continue;

			repoStage.saveAll(lowerList);
			pagina = pagina+quantidade;
		}while (!lowerList.isEmpty());

	}
}
