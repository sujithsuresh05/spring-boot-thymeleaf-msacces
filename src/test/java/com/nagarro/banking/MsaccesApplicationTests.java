package com.nagarro.banking;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class MsaccesApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	public void accessUnprotected() throws Exception {
		this.mockMvc.perform(get("/login"))
				.andExpect(status().isOk());
	}

	@Test
	public void loginUser() throws Exception {
		// @formatter:off
		this.mockMvc.perform(formLogin().user("admin").password("admin"))
				.andExpect(authenticated());
		// @formatter:on
	}

	@Test
	public void accessProtectedRedirectsToLogin() throws Exception {

		MvcResult mvcResult = this.mockMvc.perform(get("/bank/dashborad"))
				.andExpect(status().is3xxRedirection())
				.andReturn();

		assertThat(mvcResult.getResponse().getRedirectedUrl()).endsWith("/login");
	}

	@Test
	public void loginInvalidUser() throws Exception {
		// @formatter:off
		this.mockMvc.perform(formLogin().user("invalid").password("invalid"))
				.andExpect(unauthenticated())
				.andExpect(status().is3xxRedirection());
		// @formatter:on
	}

	@Test
	public void loginUserAccessProtected() throws Exception {
		// @formatter:off
		MvcResult mvcResult = this.mockMvc.perform(formLogin().user("user").password("user"))
				.andExpect(authenticated()).andReturn();
		// @formatter:on

		MockHttpSession httpSession = (MockHttpSession) mvcResult.getRequest().getSession(false);

		// @formatter:off
		this.mockMvc.perform(get("/bank/dashboard").session(httpSession))
				.andExpect(status().isOk());
		// @formatter:on
	}
}
