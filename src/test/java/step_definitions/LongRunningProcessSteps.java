package step_definitions;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import util.CsvFileWriter;
import util.LoadProperties;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LongRunningProcessSteps {

	static String responce;
	static Path currentRelativePath = Paths.get("");
	static String currentPath = currentRelativePath.toAbsolutePath().toString();
	static SOAPMessage soapResponse;
	static String createVersionxml;
	static String clientId;
	static String editorialVersionId;
	static Properties prop;

	public LongRunningProcessSteps() {
		// load properties file
		prop = LoadProperties.getInstance().loadProperties();
	}

	@Given("^I have data for Longrunning workflow request$")
	public void getBPMRequestData() throws IOException {
		createVersionxml = readFile(currentPath
				+ "/src/test/resources/testdata/createUpdateAssetBpm.xml",
				StandardCharsets.UTF_8);
	}

	@When("^I trigger the process request$")
	public void callCreateUpdateBPMRequest() throws Exception {

		// Send SOAP Message to SOAP Server
		String url = prop.getProperty("Cordys_Url");
		soapResponse = makeSoapRequestCall(url, createVersionxml);
		responce = printSOAPResponse(soapResponse);

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new InputSource(new StringReader(
				responce)));

		XPath xPath = XPathFactory.newInstance().newXPath();
		String expression = "//instance_id";

		// read a string value
		String instanceId = xPath.compile(expression).evaluate(document);

		CsvFileWriter.writeCsvFile(prop.getProperty("csv_file_location"), instanceId);

		System.out.println("\nselect element name........" + instanceId);
	}

	@Then("^I should get response with instance id$")
	public void verifyCreateUpdateAction() {

		Assert.assertTrue(responce
				.contains("<CreateOrUpdateAssetOutput xmlns=\"http://schemas.redbeemedia.com/asa/1.0\"/>"));

	}

	public static SOAPMessage getSoapMessageFromString(String xml)
			throws Exception {
		MessageFactory factory = MessageFactory.newInstance();
		MimeHeaders hd = new MimeHeaders();

		String authorization = Base64.encodeBase64String(prop.getProperty("Authorization").getBytes(Charset.forName("UTF-8")));
		hd.addHeader("Authorization", "Basic " + authorization);
		SOAPMessage message = factory
				.createMessage(
						hd,
						new ByteArrayInputStream(xml.getBytes(Charset
								.forName("UTF-8"))));
		return message;
	}

	private static SOAPMessage makeSoapRequestCall(String url, String xml)
			throws Exception {
		SOAPConnectionFactory soapConnectionFactory;
		SOAPMessage soapResponse;
		soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory
				.createConnection();

		// Send SOAP Message to SOAP Server
		soapResponse = soapConnection.call(getSoapMessageFromString(xml), url);
		return soapResponse;
	}

	/**
	 * Method used to print the SOAP Response
	 */
	private static String printSOAPResponse(SOAPMessage soapResponse)
			throws Exception {
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		Source sourceContent = soapResponse.getSOAPPart().getContent();
		StreamResult result = new StreamResult(System.out);
		transformer.transform(sourceContent, result);

		StringWriter writer = new StringWriter();
		StreamResult result1 = new StreamResult(writer);
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer1 = tFactory.newTransformer();
		transformer1.transform(sourceContent, result1);
		return writer.toString();
	}

	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

}
