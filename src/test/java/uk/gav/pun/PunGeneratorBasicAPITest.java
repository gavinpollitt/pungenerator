package uk.gav.pun;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import uk.gav.pun.controller.PunController;
import uk.gav.pun.entity.Pun;
import uk.gav.pun.service.PunService;

@WebMvcTest(PunController.class)
class PunGeneratorBasicAPITest {

	@Autowired
	private MockMvc mockMVC;

	@MockBean
	private PunService punService;

	@Captor
	private ArgumentCaptor<Pun> arg = ArgumentCaptor.forClass(Pun.class);

	@Test
	@DisplayName("Verify that a pun is received correctly")
	void getPunTest() {
		doReturn(new Pun("This is the PUN returned")).when(punService).getPun();
		assertDoesNotThrow(() -> this.mockMVC.perform(get("/punme")).andExpect(status().isOk())
				.andExpect(jsonPath("$.pun").value("This is the PUN returned")));
	}

	@Test
	@DisplayName("Verify that a pun is added without a problemo")
	void addPunTest() {
		String request = "{\"pun\": \"This is the pun sent\"}";
		assertDoesNotThrow(() -> this.mockMVC.perform(post("/punme").contentType(MediaType.APPLICATION_JSON).content(request)).andExpect(status().isCreated()));
		verify(punService).addPun(arg.capture());

		assertEquals("This is the pun sent",arg.getValue().getPun());
	}
}
