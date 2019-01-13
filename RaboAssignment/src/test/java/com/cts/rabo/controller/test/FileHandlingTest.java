package com.cts.rabo.controller.test;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Assert;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

import com.cts.rabo.basetest.BaseTest;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class FileHandlingTest extends BaseTest {

	private DefaultMockMvcBuilder mockMvcBuilder;

	private MockMultipartFile mockMultipartFile;
	
	private String filetype;

	private ScenarioContext scenarioContext;
	
	@Before
	public void setup() {
		mockMvcBuilder = MockMvcBuilders.webAppContextSetup(webApplicationContext);
	}

	@Given("^I visit the file upload page$")
	public void i_visit_the_file_upload_page() throws Throwable {
		mockmvc = mockMvcBuilder.build();
		scenarioContext=new ScenarioContext();
	}

	@When("^I upload an XML file$")
	public void i_upload_an_XML_file() throws Throwable {
		File file = new File("src/test/resources/mockdata/records.xml");
		InputStream in = new FileInputStream(file);
		filetype = "XML";
		mockMultipartFile = new MockMultipartFile("file", "records.xml", "multipart/form-data", in);
	}

	@When("^I upload an CSV file$")
	public void i_upload_an_CSV_file() throws Throwable {
		File file = new File("src/test/resources/mockdata/records.csv");
		InputStream in = new FileInputStream(file);
		filetype="CSV";
		mockMultipartFile = new MockMultipartFile("file", "records.csv", "multipart/form-data", in);
	}

	@When("^I upload an empty file$")
	public void i_upload_an_empty_file() throws Throwable {
		File file = new File("src/test/resources/mockdata/empty.xml");
		InputStream in = new FileInputStream(file);
		filetype="XML";
		mockMultipartFile = new MockMultipartFile("file", "records.xml", "multipart/form-data", in);
	}

	@When("^I upload an file with no Failed transaction$")
	public void i_upload_an_file_with_no_Failed_transaction() throws Throwable {
		File file = new File("src/test/resources/mockdata/record_without_failed_transaction.csv");
		InputStream in = new FileInputStream(file);
		filetype="CSV";
		mockMultipartFile = new MockMultipartFile("file", "records.csv", "multipart/form-data", in);
	}

	@Then("^Process the file data$")
	public void process_the_file_data() throws Throwable {

		MvcResult result = mockmvc
							.perform(MockMvcRequestBuilders.fileUpload("/upload")
							.file(mockMultipartFile)
							.param("filetype", filetype)
							.contentType(MediaType.MULTIPART_FORM_DATA))
							.andDo(print())
							.andReturn();
		
		scenarioContext.setContext("result", result);
	}
	
	@And("^I should able to get Failed transaction report$")
	public void i_should_able_to_get_Failed_transaction_report() throws Throwable {
		MvcResult result= (MvcResult) scenarioContext.getContext("result");
		//System.out.println(result.toString());
		Assert.assertEquals(200, result.getResponse().getStatus());
	}
	
	
	@Then("^Process the file data and throw ERROR$")
	public void process_the_file_data_and_throw_ERROR() throws Throwable {
		MvcResult result = mockmvc
				.perform(MockMvcRequestBuilders.fileUpload("/upload")
				.file(mockMultipartFile)
				.param("filetype", filetype)
				.contentType(MediaType.MULTIPART_FORM_DATA))
				.andDo(print())
				.andReturn();
		Assert.assertEquals(500, result.getResponse().getStatus());
		ModelAndViewAssert.assertViewName(result.getModelAndView(), "error");
	}

	@Then("^Process the file data and get report$")
	public void process_the_file_data_and_get_report() throws Throwable {
		MvcResult result = mockmvc
				.perform(MockMvcRequestBuilders.fileUpload("/upload")
				.file(mockMultipartFile)
				.param("filetype", filetype)
				.contentType(MediaType.MULTIPART_FORM_DATA))
				.andDo(print())
				.andReturn();
		Assert.assertEquals(200, result.getResponse().getStatus());
		Assert.assertNotNull(result.getResponse().getContentAsString());
	}

}
