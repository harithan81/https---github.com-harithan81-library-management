package com.lm.service;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.lm.domain.gen.Book;
import com.lm.repository.BookRepository;

@Service
public class DOMXmlParser {
	@Autowired
	private BookRepository bookRepository;

	public Book parseXml(Book book) throws Exception {
		return bookRepository.saveAndFlush(book);
	}

	public void parseXml(File bookFile) throws Exception {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(bookFile);
		doc.getDocumentElement().normalize();
		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		NodeList list = doc.getElementsByTagName("Book");
		System.out.println("----------------------------");

		for (int i = 0; i < list.getLength(); i++) {
			Node node = list.item(i);
			System.out.println("\nCurrent Element :" + node.getNodeName());

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Book book = new Book();
				Element element = (Element) node;
				book.setBookName(element.getElementsByTagName("bookName").item(0).getTextContent());
				book.setAuthorName(element.getElementsByTagName("authorName").item(0).getTextContent());
				book.setIsbn(element.getElementsByTagName("isbn").item(0).getTextContent());
				book.setBookCount(Integer.parseInt(element.getElementsByTagName("bookCount").item(0).getTextContent()));
				bookRepository.saveAndFlush(book);

			}
		}
	}

}