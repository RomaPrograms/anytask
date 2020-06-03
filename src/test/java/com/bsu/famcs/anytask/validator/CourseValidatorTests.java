package com.bsu.famcs.anytask.validator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("username")
@Sql(value = "/sql/create-user-before", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/sql/clean-user-after", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@ActiveProfiles("test")
public class CourseValidatorTests {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private CourseValidator courseValidator;

	@Test
	public void testCourseAdding() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/course/courseCreate"))
				.andExpect(authenticated())
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("/WEB-INF/jsp/courseCreate.jsp"));
	}
}
