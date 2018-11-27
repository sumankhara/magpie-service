package com.magpie;

import java.io.StringReader;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.magpie.model.Book;
import com.magpie.model.GoodreadsResponse;
import com.magpie.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	private static final String URI = "https://www.goodreads.com/book/title.xml?key=ZPaZ2ocRaj8cTTfiKv4UDQ&title=";
	
	public List<Book> getAllBooks(){
		List<Book> books = bookRepository.findAll();
		return books;
	}
	
	public List<Book> getBooksByCategory(String category){
		List<Book> books = bookRepository.findByCategory(category);
		return books;
	}
	
	public Book getBook(String id) {
		Book book = bookRepository.findById(id).get();
		return book;
	}
	
	public GoodreadsResponse getBookDetails(String title) {
		String query = getQueryFromTitle(title);
		String url = URI + query;
		
		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.getForObject(url, String.class);
		
		Document doc = convertStringToDocument(response);
		doc.getDocumentElement().normalize();
		
		NodeList nodeList = doc.getElementsByTagName("image_url");
		String imageUrl = nodeList.item(0).getTextContent();
		System.out.println(imageUrl);
		
		nodeList = doc.getElementsByTagName("description");
		String description = nodeList.item(0).getTextContent();
		System.out.println(description);
		
		GoodreadsResponse goodreadsResponse = new GoodreadsResponse(imageUrl, description);
		
		return goodreadsResponse;
	}

	private String getQueryFromTitle(String title) {
		String query = title.replaceAll(" ", "+");
		return query;
	}
	
	private static Document convertStringToDocument(String xmlStr) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
        DocumentBuilder builder;
        try  
        {  
            builder = factory.newDocumentBuilder();  
            Document doc = builder.parse( new InputSource( new StringReader( xmlStr ) ) ); 
            return doc;
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
        return null;
    }
}
