package com.springjpa;

import com.springjpa.domain.Users;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class SpringJpaApplicationTests {

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

//	@InjectMocks
//	RecordService recordService;
//
//	@Mock
//	DatabaseDAO databaseMock;
//
//	@Mock
//	NetworkDAO networkMock;
//
//	@Test
//	public void saveTest()
//	{
//		boolean saved = recordService.save("temp.txt");
//		assertEquals(true, saved);
//
//		verify(databaseMock, times(1)).save("temp.txt");
//		verify(networkMock, times(1)).save("temp.txt");
//	}

	@Test
	void contextLoads() {
        Users user = new Users();
	}

}
