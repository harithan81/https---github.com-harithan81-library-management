package com.lm.service;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.lm.domain.gen.Book;
import com.lm.repository.BookRepository;
@Service
public class SaxXmlParser {
	@Autowired
	BookRepository bookRepository;

	public void parser(File file) throws Exception {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		DefaultHandler handler = new DefaultHandler() {
			boolean bookName = false;
			boolean authorName = false;

			public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

				System.out.println("Start Element :" + qName);

				if (qName.equalsIgnoreCase("BOOKNAME")) {
					bookName = true;
				}

				if (qName.equalsIgnoreCase("AUTHORNAME")) {
					authorName = true;
				}

			}

			public void endElement(String uri, String localName, String qName) throws SAXException {

				System.out.println("End Element :" + qName);

			}

			public void characters(char ch[], int start, int length) throws SAXException {

				if (bookName) {
					System.out.println("Book Name : " + new String(ch, start, length));
					bookName = false;
				}

				if (authorName) {
					System.out.println("AUthor Name : " + new String(ch, start, length));
					authorName = false;
				}
			}

		};

		saxParser.parse(file, handler);
		Book b=new Book();
		b.setBookName((String)saxParser.getProperty("bookName"));
		
		

	}
}
