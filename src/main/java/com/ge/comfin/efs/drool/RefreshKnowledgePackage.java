package com.ge.comfin.efs.drool;

import java.io.InputStream;

import java.util.ArrayList;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.drools.builder.DecisionTableConfiguration;
import org.drools.builder.DecisionTableInputType;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.compiler.DecisionTableFactory;
import org.drools.io.Resource;
import org.drools.io.ResourceFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.util.Collection;
import java.util.Iterator;

import org.drools.KnowledgeBase;

public class RefreshKnowledgePackage {
	static KnowledgeBase kbase = KnowledgeBaseLoader.createKnowledgeBase();
	/*public static boolean refreshPackage(String sPackageName){
		boolean status=false;
		synchronized (KnowledgeBaseLoader.class) {
			if(removePackage(sPackageName))
			status=addPackages(sPackageName);
		}
		return status; 
	}*/
	public static boolean removePackageFromKnowledgeBase(String sPackageName){
		boolean status=false;
		synchronized (KnowledgeBaseLoader.class) {
			Collection c=kbase.getKnowledgePackages();		
			Iterator itr=c.iterator();		
			while(itr.hasNext())
			{				
				org.drools.definitions.impl.KnowledgePackageImp knowledgePackageImp=
					(org.drools.definitions.impl.KnowledgePackageImp)itr.next();
				if(knowledgePackageImp.getName().equalsIgnoreCase(sPackageName))
				{
					System.out.println("----Removing Package :"+sPackageName);
				kbase.removeKnowledgePackage(sPackageName);
				status=true;
				}
			}
		}
		return status;
	}
	public static void removePackages(String ruleConfigFileName){		
		try{ synchronized (KnowledgeBaseLoader.class) {
			ArrayList <KnowledgePackage> knowledgePackageAL= LoadConfig(ruleConfigFileName);
			KnowledgePackage knowledgePackage;
			for (int i=0;i<knowledgePackageAL.size();i++){
				knowledgePackage=(KnowledgePackage)knowledgePackageAL.get(i);
				removePackageFromKnowledgeBase(knowledgePackage.getPackageName());
			}
		}}
		catch(Exception e){e.printStackTrace();}
		
	}
	
	public static void addPackages(String ruleConfigFileName){		
		try{ synchronized (KnowledgeBaseLoader.class) {
			ArrayList <KnowledgePackage> knowledgePackageAL= LoadConfig(ruleConfigFileName);
			KnowledgePackage knowledgePackage;
			for (int i=0;i<knowledgePackageAL.size();i++){
				knowledgePackage=(KnowledgePackage)knowledgePackageAL.get(i);
				System.out.println("----Adding Package :"+knowledgePackage.getKnowledgeBaseResourceName());
				AddPackageToKnowledgeBase(knowledgePackage.getKnowledgeBaseResourceType(),knowledgePackage.getKnowledgeBaseResourceName());
			}
		}}
		catch(Exception e){e.printStackTrace();}		
	}
	private static ArrayList <KnowledgePackage> LoadConfig(String ruleConfigFileName) throws SAXException,IOException,ParserConfigurationException {
		
		ArrayList <KnowledgePackage> knowledgePackageAL=new ArrayList();
		KnowledgePackage knowledgePackage;		
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		DocumentBuilder db=dbf.newDocumentBuilder();		
		Document document= db.parse(ruleConfigFileName);
		NodeList nodes_i = document.getDocumentElement().getChildNodes();
		String sKnowledgeBaseResourceType=null;
		String sKnowledgeBaseResourceName=null;		
		for (int i = 0; i < nodes_i.getLength(); i++) {			
			Node node_i = nodes_i.item(i);
			if (node_i.getNodeType() == Node.ELEMENT_NODE ) {				
				Element RuleKnowledgeBaseConfig = (Element) node_i;
				//System.out.println("-------------------------------------------------------------------------------------");
				//System.out.println(RuleKnowledgeBaseConfig.getNodeName()+":		"+RuleKnowledgeBaseConfig.getAttribute("name"));
				NodeList nodes_j = RuleKnowledgeBaseConfig.getChildNodes();
				for (int j = 0; j < nodes_j.getLength(); j++) {
					Node node_j = nodes_j.item(j);
					if (node_j.getNodeType() == Node.ELEMENT_NODE) {
						Element pieces = (Element) node_j;						
						NodeList nodes_k = pieces.getChildNodes();
						for (int k = 0; k < nodes_k.getLength(); k++) {
							Node node_k = nodes_k.item(k);							
							if (node_k.getNodeType() == Node.ELEMENT_NODE) {
								knowledgePackage=new KnowledgePackage();
								Element pieces1 = (Element) node_k;
								//System.out.println("----------------------");
								String sPkgName=pieces1.getAttribute("name");
								//System.out.println(pieces1.getNodeName()+":		"+pieces1.getAttribute("name"));
								NodeList nodes_l = pieces1.getChildNodes();
								for (int l = 0; l < nodes_l.getLength(); l++) {
									Node node_l = nodes_l.item(l);
									if (node_l.getNodeType() == Node.ELEMENT_NODE) {
										Element piece2 = (Element) node_l;
										if(piece2.getNodeName().equals("KnowledgeBaseResourceType")){
											sKnowledgeBaseResourceType=piece2.getTextContent();
										}
										if(piece2.getNodeName().equals("KnowledgeBaseResourceName")){
											sKnowledgeBaseResourceName=piece2.getTextContent();
										}												
									}
								}
								//System.out.println("sKnowledgeBaseResourceType: "+sKnowledgeBaseResourceType+"   sKnowledgeBaseResourceName:	"+sKnowledgeBaseResourceName);
								//if(sPackageName.equalsIgnoreCase(sPkgName)){
								//removePackage(sPkgName);
								//AddPackageToKnowledgeBase(kbase,sKnowledgeBaseResourceType,sKnowledgeBaseResourceName);								
								knowledgePackage.setKnowledgeBaseResourceName(sKnowledgeBaseResourceName);
								knowledgePackage.setKnowledgeBaseResourceType(sKnowledgeBaseResourceType);
								knowledgePackage.setPackageName(sPkgName);
								knowledgePackageAL.add(knowledgePackage);
	       }
	      }
	     }
	    }
	  }
	}		
		return knowledgePackageAL;
   }
	private static void AddPackageToKnowledgeBase(String sKnowledgeBaseResourceType,String sKnowledgeBaseResourceName){
		if(sKnowledgeBaseResourceType.equalsIgnoreCase("DecisionTable")){
			DecisionTableConfiguration dtableconfiguration =KnowledgeBuilderFactory.newDecisionTableConfiguration();
			dtableconfiguration.setInputType( DecisionTableInputType.XLS );
			try{
				String drlString = DecisionTableFactory.loadFromInputStream(ResourceFactory
						.newFileResource(sKnowledgeBaseResourceName)
						.getInputStream(), dtableconfiguration);
			    	String sFileName=sKnowledgeBaseResourceName.substring(0, sKnowledgeBaseResourceName.lastIndexOf("."))+".drl";
					//System.out.println("sFileName:"+sFileName);
			    	File aFile=new File(sFileName);
				    Writer output = new BufferedWriter(new FileWriter(aFile));
				    try {		     
				      output.write( drlString );
				    }
				    finally {
				      output.close();
				    }
						
				}
				catch(Exception e){
					e.printStackTrace();
				}			

			KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
			Resource xlsRes2 = ResourceFactory.newFileResource(sKnowledgeBaseResourceName);        
			kbuilder.add( xlsRes2,ResourceType.DTABLE,dtableconfiguration );			
			KnowledgeBuilderErrors errors = kbuilder.getErrors();
				//System.out.println("MAnish Shukla errors.size():"+errors.size());
				if (errors.size() > 0) {
					for (KnowledgeBuilderError error: errors) {
						System.out.println("MAnish Shukla Error:"+error);
					}
					throw new IllegalArgumentException("Could not parse knowledge.");
				}			
			kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
			
			try{
			String drlString = DecisionTableFactory.loadFromInputStream(ResourceFactory
					.newFileResource(sKnowledgeBaseResourceName)
					.getInputStream(), dtableconfiguration);
		    	String sFileName=sKnowledgeBaseResourceName.substring(0, sKnowledgeBaseResourceName.lastIndexOf("."))+".drl";
				//System.out.println("sFileName:"+sFileName);
		    	File aFile=new File(sFileName);
			    Writer output = new BufferedWriter(new FileWriter(aFile));
			    try {		     
			      output.write( drlString );
			    }
			    finally {
			      output.close();
			    }	
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		else{
			KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
			kbuilder.add(ResourceFactory.newClassPathResource(sKnowledgeBaseResourceName), ResourceType.DRL);

			KnowledgeBuilderErrors errors = kbuilder.getErrors();
			if (errors.size() > 0) {
			for (KnowledgeBuilderError error: errors) {
				System.out.println("MAnish Shukla Error:"+error);
				}
				throw new IllegalArgumentException("Could not parse knowledge.");
			}			
			kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());			
		}
	}	

}
