package com.culqi.tokens;

import com.culqi.tokens.model.payload.request.TokensRequest;

import com.culqi.tokens.util.CommonUtilities;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class TokensApplicationTests {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private MockMvc mockMvc;

	/**
	 *
	 * @throws Exception
	 */
	@Test
	public void testSendDataToEndpoint() throws Exception {
		TokensRequest tokensRequest = new TokensRequest("4444333322221111", 2019, 1);
		String tokenJsonString = CommonUtilities.ObjectToJsonString(tokensRequest);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/api/tokens")
				.accept(MediaType.APPLICATION_JSON).content(tokenJsonString)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
		byte[] bytesResponse = response.getContentAsByteArray();
		assertEquals(
				CommonUtilities
						.BytesToJsonObject(bytesResponse)
						.getJSONObject("data")
						.getString("token"),
				"tkn_live_4444333322221111-2019-01"
		);
		assertEquals(
				CommonUtilities
						.BytesToJsonObject(bytesResponse)
						.getJSONObject("data")
						.getString("brand"),
				"visa"
		);
		assertEquals(
				CommonUtilities
						.BytesToJsonObject(bytesResponse)
						.getBoolean("success"),
				true
		);


		mockMvc.perform(post("/api/tokens").content(tokenJsonString)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.token", is("tkn_live_4444333322221111-2019-01")))
				.andExpect(jsonPath("$.data.brand", is("visa")))
				.andExpect(jsonPath("$.success", is(true)));
	}

}

