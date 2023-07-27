package uk.gav.pun;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import uk.gav.pun.entity.Pun;
import uk.gav.pun.repository.PunRepository;

@DataJpaTest
@TestPropertySource(properties = { "spring.config.location=classpath:test-application.yaml" })
@Sql({ "/data/pun-ddl.sql", "/data/import-puns-tests.sql" })
class PunGeneratorRepositoryTest {

	@Autowired
	private PunRepository punRepository;

	@Test
	@DisplayName("Verify that count method is exposed by default")
	@Order(1)
	void getCountTest() {
		assertEquals(3, punRepository.count());
	}

	@Test
	@DisplayName("Verify that we can save a pun and then find it on repository")
	@Order(2)
	void addAndGetTest() {
		Pun p = new Pun("This should be pun number 4");
		//Save it first
		assertDoesNotThrow(() -> punRepository.save(p), "Pun " + p.getPun() + " could not be saved");

		//Count again to see we have extra 1
		assertEquals(4, punRepository.count());

		//Now get it back and check
		assertTrue(this.punEquals(4, p.getPun()), "Pun 4 cannot be located");
	}

	private boolean punEquals(long id, String pun) {
		Pun foundPun = this.punRepository.getReferenceById(id);

		return foundPun!=null && foundPun.getPun().equals(pun);
	}

}
