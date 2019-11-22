package com.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
public class EmployeeJUnitWithMokitoApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	//@Test
	public void testSaveEmployee() throws Exception {
		MockHttpServletResponse res=mockMvc.perform(post("/employee/save")
				.header("Content-Type", "application/json")
				.header("Accept", "application/json")
				.content("{\"name\":\"vasu\",\"sal\":99000.00,\"adress\":\"Parachivara\"}"))
		.andReturn().getResponse();
		
		assertEquals(200, res.getStatus());
		if(!(res.getContentAsString().contains("sucessfully"))) {
			fail();
		}
	}

	//@Test
	public void testGetById() throws Exception {
		MockHttpServletResponse res= mockMvc.perform(get("/employee/one/8"))
		.andReturn()
		.getResponse();
		//its for record found
		//assertEquals(200, res.getStatus());
		//assertNotNull(res.getContentAsString());
		
		//if record not found
		assertEquals(HttpStatus.NO_CONTENT.value()
				, res.getStatus());
		assertEquals("id was not found", res.getContentAsString());
		assertNotNull(res.getContentAsString());
	
	}
	
	//@Test
	public void testGetAll() throws Exception {
		MockHttpServletResponse res= mockMvc.perform(get("/employee/all"))
		.andReturn()
		.getResponse();
		
		assertEquals(200, res.getStatus());
		assertEquals("application/json;charset=UTF-8", res.getContentType());
		assertNotNull(res.getContentAsString());
	}
	
	//@Test
	public void testDeleteById() throws Exception {
		MockHttpServletResponse res=mockMvc.perform(delete("/employee/delete/2"))
		.andReturn().getResponse();
		// if record is available
		/*assertEquals(200, res.getStatus());
		assertEquals("Record deleted sucessfully", res.getContentAsString());
		assertNotNull(res.getContentAsString());*/
		
		//if the record not available
		assertEquals(HttpStatus.BAD_REQUEST.value(), res.getStatus());
		assertEquals("product not found", res.getContentAsString());
		assertNotNull(res.getContentAsString());
	}
	
	@Test
	public void testUpdateRecord() throws Exception{
		MockHttpServletResponse res= mockMvc.perform(put("/employee/update")
				.header("Content-Type", "application/json")
				.header("Accept", "appliation/json")
				.content("{\"id\":9,\"name\":\"haribabu\",\"sal\":89000.00,\"adress\":\"Parachivara\"}"))
		.andReturn().getResponse();
		//if record exist
		/*assertEquals(200, res.getStatus());
		assertEquals("record updated sucessfully", res.getContentAsString());
		assertNotNull(res.getContentAsString());*/
		
		assertEquals(400, res.getStatus());
		assertEquals("No data found", res.getContentAsString());
		assertNotNull(res.getContentAsString());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
