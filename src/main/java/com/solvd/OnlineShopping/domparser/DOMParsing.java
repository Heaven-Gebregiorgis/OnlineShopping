package com.solvd.OnlineShopping.domparser;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;


public class DOMParsing {
	
	public static void main(String[] args) {
    try {
       File inputFile = new File("/Users/apple/Desktop/Solvd automation/OnlineShopping/src/main/resources/DOMInput.xml");
       DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
       DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
       Document doc = dBuilder.parse(inputFile);
       doc.getDocumentElement().normalize();
       System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
       NodeList nList = doc.getElementsByTagName("Vendors");
       System.out.println("----------------------------");
       
       for (int temp = 0; temp < nList.getLength(); temp++) {
          Node nNode = nList.item(temp);
          System.out.println("\nCurrent Element :" + nNode.getNodeName());
          
          if (nNode.getNodeType() == Node.ELEMENT_NODE) {
             Element eElement = (Element) nNode;
             System.out.println("Vendor Id " 
            		 + eElement.getAttribute("id"));
             System.out.println("Vendor Name : " 
                + eElement
                .getElementsByTagName("vendorName")
                .item(0)
                .getTextContent());
             System.out.println("Contact Name : " 
                + eElement
                .getElementsByTagName("contactName")
                .item(0)
                .getTextContent());
             System.out.println("Email : " 
                + eElement
                .getElementsByTagName("email")
                .item(0)
                .getTextContent());
             System.out.println("Phone : " 
                + eElement
                .getElementsByTagName("phone")
                .item(0)
                .getTextContent());
             for (int j = 0; j < nList.getLength(); j++) {
                 Node nNode1 = nList.item(j);
                 if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                     Element eElement1 = (Element) nNode1;
                     System.out.println("Address Id " 
                    		 + eElement1.getAttribute("id"));
                     System.out.println("House Number : " 
                        + eElement1
                        .getElementsByTagName("houseNumber")
                        .item(0)
                        .getTextContent());
                     System.out.println("Street : " 
                        + eElement1
                        .getElementsByTagName("street")
                        .item(0)
                        .getTextContent());
                     System.out.println("Apartment Number : " 
                        + eElement1
                        .getElementsByTagName("apartmentNumber")
                        .item(0)
                        .getTextContent());
                     System.out.println("City : " 
                        + eElement1
                        .getElementsByTagName("city")
                        .item(0)
                        .getTextContent());
                     System.out.println("State : " 
                             + eElement1
                             .getElementsByTagName("state")
                             .item(0)
                             .getTextContent());
                          System.out.println("Zip Code : " 
                             + eElement1
                             .getElementsByTagName("postalCode")
                             .item(0)
                             .getTextContent());
                          System.out.println("Country : " 
                             + eElement1
                             .getElementsByTagName("country")
                             .item(0)
                             .getTextContent());
          
       }
             }
          }
    }
    }catch (Exception e) {
       e.printStackTrace();
    }
 }
}


