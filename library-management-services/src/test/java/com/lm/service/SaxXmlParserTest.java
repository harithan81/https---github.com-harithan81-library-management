package com.lm.service;

import java.io.File;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;

public class SaxXmlParserTest extends BaseTest {
	@Autowired
	private SaxXmlParser saxXmlParser;

	@Ignore
	@Test
	@DatabaseSetup(value = { "StaticTypes.xml", "Book.xml" })
	public void parserTest() throws Exception {
		File file = new File("/home/subhash/Books.xml");
		saxXmlParser.parser(file);
	}

}
