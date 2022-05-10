package com.solvd.OnlineShopping.domparser;

import java.io.File;  
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class DOMParser {

	private static final Logger LOGGER = LogManager.getLogger(DOMParser.class);
	 public static void main(String[] args) throws Exception
     {
		 
try{
   //Get Document Builder
     DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
     DocumentBuilder builder = factory.newDocumentBuilder();

     // Load the input XML document, parse it and return an instance of the
     // Document class.
     Document document = builder.parse(new File("/Users/apple/Desktop/Solvd automation/OnlineShopping/src/main/resources/DOMInput.xml"));

    // List<Vendor> vendors = new ArrayList<Vendor>();
     NodeList nodeList = document.getDocumentElement().getChildNodes();
     for (int i = 0; i < nodeList.getLength(); i++) {
          Node node = nodeList.item(i);

          if (node.getNodeType() == Node.ELEMENT_NODE) {
        	  
          
               Element elem = (Element) node;


               String id = elem.getAttribute("id");
               NodeList nameList = elem.getChildNodes();
               for(int j=0; j<nameList.getLength(); j++) {
            	Node n = nameList.item(j);
            	if(n.getNodeType()==Node.ELEMENT_NODE) {
            		Element name = (Element) n;
            		LOGGER.info("Vendor" + id + ":" + name.getTagName() + "=" + name.getTextContent());
            	}
               }
          }  
          }
     }

     catch(Exception e) {
    	e.printStackTrace(); 
     }
}
}
