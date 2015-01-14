package com.ge.comfin.efs.drool;


import org.drools.KnowledgeBaseFactory;
import org.drools.KnowledgeBase;

public class KnowledgeBaseLoader {
	
	public static KnowledgeBase kbase ;///make this as Private
	static{System.out.println("static block of ConfigParser called");}
	private KnowledgeBaseLoader(){}
	public static synchronized KnowledgeBase createKnowledgeBase(){
		if(kbase!=null){
			System.out.println("-----returning Same KnowledgeBase---");
			return kbase;
		}
		else {
			System.out.println("-----returning New KnowledgeBase---");
			kbase = KnowledgeBaseFactory.newKnowledgeBase();
			return kbase;
		}
	}
}
