package com.lm.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;

import com.github.springtestdbunit.annotation.DatabaseSetup;

public class XmlParserTest extends BaseTest {
	@Autowired
	private DOMXmlParser xmlParser;

	@Test
	@DatabaseSetup(value = { "StaticTypes.xml", "Book.xml" })
	// @ExpectedDatabase(value = "ParseXmlTest.xml", assertionMode =
	// DatabaseAssertionMode.NON_STRICT)
	public void parseXmlTest() throws Exception {
		Resource xmlFile = appContext.getResource("classpath:com/lm/service/Books.xml");
		System.out.println(">>>" + xmlFile);
		System.out.println(">>>" + xmlFile.exists());
		System.out.println(">>>" + xmlFile.getFile());
		xmlParser.parseXml(xmlFile.getFile());
	}
}
