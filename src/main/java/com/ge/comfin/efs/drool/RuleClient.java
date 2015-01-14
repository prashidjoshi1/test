package com.ge.comfin.efs.drool;


import java.util.Collection;
import java.util.Iterator;

import com.ge.comfin.efs.drool.CustomRuleParameter;
import org.drools.KnowledgeBase;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.apache.log4j.Logger;

public class RuleClient {	
	//private static Logger logger1 = Logger.getLogger( RuleClient.class );
	
	public static void main(String[] str){
		try {			
			//System.out.println("inside Main App....");
			
			CustomRuleParameter CustomRuleParameter=new CustomRuleParameter();
			CustomRuleParameter.setAmount(10000);
			CustomRuleParameter.setInvoiceRoleName("START");
			CustomRuleParameter.setIsLegal("true");
			CustomRuleParameter.setIsClose("false");
			CustomRuleParameter.setMove_direction("2");
			CustomRuleParameter.setSubType("Technical");
			RuleParameterInf ruleParameterInf=CustomRuleParameter;
			ApprovalAction approvalAction=new ApprovalAction();
			
			System.out.println("---------------Loading All Package--------------:");
			
			KnowledgeBase kbase = KnowledgeBaseLoader.createKnowledgeBase();			
			StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();			
			KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "example1");
			
			
			displayPkgName(kbase);			
			
		
			ksession.insert(approvalAction);
			ksession.insert(ruleParameterInf); 
			ksession.fireAllRules();
			ksession.dispose();
			logger.close();
			

			System.out.println("---------------Removing Package--------------:");
			
			RefreshKnowledgePackage refreshKnowledgePackage=new RefreshKnowledgePackage();
			refreshKnowledgePackage.removePackages("RuleKnowledgeBaseConfig.xml");
			displayPkgName(kbase);
			
			ksession = kbase.newStatefulKnowledgeSession();
			ksession.insert(approvalAction);
			ksession.insert(ruleParameterInf); 
			ksession.fireAllRules();
			ksession.dispose();
			

			System.out.println("---------------Adding Package--------------:");
			
			refreshKnowledgePackage=new RefreshKnowledgePackage();
			refreshKnowledgePackage.addPackages("RuleKnowledgeBaseConfig.xml");
			displayPkgName(kbase); 
			//-------------------------------------------------------------			
			ksession = kbase.newStatefulKnowledgeSession();
			ksession.insert(approvalAction);
			ksession.insert(ruleParameterInf); 
			ksession.fireAllRules();
			ksession.dispose();
		} catch (Throwable t) {
			t.printStackTrace();
		}		
	}
	public static void displayPkgName(KnowledgeBase kbase){
		Collection c=kbase.getKnowledgePackages();			
		Iterator itr=c.iterator();
		String sPkgName;
		while(itr.hasNext())
		{				
			org.drools.definitions.impl.KnowledgePackageImp knowledgePackageImp=
				(org.drools.definitions.impl.KnowledgePackageImp)itr.next();				
			System.out.println("Final data--"+knowledgePackageImp.getName());
			sPkgName=knowledgePackageImp.getName();				
		}
	}
}