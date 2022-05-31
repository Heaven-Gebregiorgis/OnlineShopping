package com.solvd.OnlineShopping.fileparser;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.solvd.OnlineShopping.model.Address;
import com.solvd.OnlineShopping.model.Vendor;


public class DOMParsing {
	
	private static final Logger LOGGER = LogManager.getLogger(DOMParsing.class);
		   public static void main(String[] args) 
		   {
		      //Get Document Builder
		      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		      DocumentBuilder builder;
			try {
				builder = factory.newDocumentBuilder();
				Document document = builder.parse(new File("/Users/apple/Desktop/Solvd automation/OnlineShopping/src/main/resources/DOMInput.xml"));
			      //Normalize the XML Structure
			      document.getDocumentElement().normalize();
			       
			      //Here comes the root node
			      Element root = document.getDocumentElement();
			      LOGGER.info(root.getNodeName());
			       
			      //Get all vendors
			      NodeList nList = document.getElementsByTagName("vendor");
			      LOGGER.info("============================");
			       
			      visitChildNodes(nList);
			} catch (ParserConfigurationException e) {
				LOGGER.error(e.getMessage());
			} catch (SAXException e) {
				LOGGER.error(e.getMessage());
			} catch (IOException e) {
				LOGGER.error(e.getMessage());
			}
			
		
			
			//transforming java object to xml file
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
    		Address address = new Address(6, 6754, "Broadway", "A11", "Seattle", "WA", "98112", "USA");
  		Vendor vendor1 = new Vendor(5, "Apple store", "Brandon", "email1123@email.com", "800-000-0002", address);          
		          try {
		   
		        	  DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
		        	  Document document = documentBuilder.newDocument();
		              // root element
		              Element root = document.createElement("vendors");
		              document.appendChild(root);
		   
		              // vendor element
		              Element vendor = document.createElement("vendor");
		   
		              root.appendChild(vendor);
		   
		              // set an attribute to vendor element
		              Attr attr = document.createAttribute("id");
		              attr.setValue("10");
		              vendor.setAttributeNode(attr);
		   
		              // vendorname element
		              Element vendorName = document.createElement("vendorname");
		              vendorName.appendChild(document.createTextNode(vendor1.getVendorName()));
		              vendor.appendChild(vendorName);
		   
		              // contactname element
		              Element contactName = document.createElement("contactname");
		              contactName.appendChild(document.createTextNode(vendor1.getContactName()));
		              vendor.appendChild(contactName);
		   
		              // email element
		              Element email = document.createElement("email");
		              email.appendChild(document.createTextNode(vendor1.getEmail()));
		              vendor.appendChild(email);
		   
		              // phone elements
		              Element phone = document.createElement("phone");
		              phone.appendChild(document.createTextNode(vendor1.getPhone()));
		              vendor.appendChild(phone);
		              
		           // address elements
		              Element addres = document.createElement("address");
		              vendor.appendChild(addres);
		              Attr attri = document.createAttribute("id");
		              attri.setValue("1");
		              addres.setAttributeNode(attri);
		              
		              Element street = document.createElement("street");
		              street.appendChild(document.createTextNode(address.getStreet()));
		              addres.appendChild(street);
		              
		              Element city = document.createElement("city");
		              city.appendChild(document.createTextNode(address.getCity()));
		              addres.appendChild(city);
		              
		              Element state = document.createElement("state");
		              state.appendChild(document.createTextNode(address.getState()));
		              addres.appendChild(state);
		              
		              Element postalcode = document.createElement("zipCode");
		              postalcode.appendChild(document.createTextNode(address.getPostalCode()));
		              addres.appendChild(postalcode);
		              
		              Element country = document.createElement("country");
		              country.appendChild(document.createTextNode(address.getCountry()));
		              addres.appendChild(country);
		   
		              // create the xml file
		              //transform the DOM Object to an XML File
		              TransformerFactory transformerFactory = TransformerFactory.newInstance();
		              Transformer transformer = transformerFactory.newTransformer();
		              transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		              DOMSource domSource = new DOMSource(document);
		              StreamResult streamResult = new StreamResult(new File("/Users/apple/Desktop/Solvd automation/OnlineShopping/src/main/resources/DOMOutput.xml"));

		   
		              transformer.transform(domSource, streamResult);
		   
		              LOGGER.info("Done creating XML File");
		   
		          } catch (ParserConfigurationException e) {
		              LOGGER.error(e.getMessage());
		          } catch (TransformerException e) {
		        	  LOGGER.error(e.getMessage());
		          }
		      }
  
		 
		 
		   private static void visitChildNodes(NodeList nList)
		   {
		      for (int temp = 0; temp < nList.getLength(); temp++)
		      {
		         Node node = nList.item(temp);
		         if (node.getNodeType() == Node.ELEMENT_NODE)
		         {
		            LOGGER.info("Node Name = " + node.getNodeName() + "; Value = " + node.getTextContent());
		            //Check all attributes
		            if (node.hasAttributes()) {
		               // get attributes names and values
		               NamedNodeMap nodeMap = node.getAttributes();
		               for (int i = 0; i < nodeMap.getLength(); i++)
		               {
		                   Node tempNode = nodeMap.item(i);
		                   LOGGER.info("Attr name : " + tempNode.getNodeName()+ "; Value = " + tempNode.getNodeValue());
		               }
		               if (node.hasChildNodes()) {
		                
		                  visitChildNodes(node.getChildNodes());
		               }
		           }
		         }
		      }
		      
		    
		      
		     
		      
		   }	   

		   
}


