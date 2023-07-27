package uk.gav.pun;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import uk.gav.pun.entity.Pun;
import uk.gav.pun.service.PunService;
import uk.gav.pun.service.Randomiser;

@SpringBootTest
@TestPropertySource(properties = { "spring.config.location=classpath:test-application.yaml" })
@Sql({ "/data/pun-ddl.sql", "/data/import-puns-tests.sql" })
public class PunServiceTest {

    @Autowired
    private PunService punService;

    @MockBean 
    private Randomiser randomiser;
    
	@Test
	@DisplayName("Verify we get a pun back based on a controlled random number")
	void getCountTest() {
        //Force a return of 2 from Randomiser
        doReturn(2l).when(randomiser).getValue(anyLong(), anyLong());

        Pun p = this.punService.getPun();

        assertEquals("This is pun number two", p.getPun());
	}    
}
