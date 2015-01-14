package com.ge.comfin.efs.drool;




import com.ge.comfin.efs.drool.RuleOutputParamInf;
import com.ge.comfin.efs.drool.RuleParameterInf;

import java.util.StringTokenizer;
import java.io.Serializable;

public class CustomRuleParameter extends RuleParameterInf implements Serializable {
	

	String move_direction;
	public String getMove_direction() {
		return move_direction;
	}
	public void setMove_direction(String move_direction) {
		this.move_direction = move_direction;
	}
	public String getInvoiceRoleName() {
		return invoiceRoleName;
	}
	public void setInvoiceRoleName(String invoiceRoleName) {
		this.invoiceRoleName = invoiceRoleName;
	}
	public String getIsLegal() {
		return isLegal;
	}
	public void setIsLegal(String isLegal) {
		this.isLegal = isLegal;
	}
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
	public String getIsClose() {
		return isClose;
	}
	public void setIsClose(String isClose) {
		this.isClose = isClose;
	}
	public String isHasOperation() {
		return hasOperation;
	}
	public void setHasOperation(String hasOperation) {
		this.hasOperation = hasOperation;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	String invoiceRoleName;
	String isLegal;
	String subType;
	String isClose;
	String hasOperation;
	int amount;
	
	public   boolean in(String str,String value){
		StringTokenizer st = new StringTokenizer(str, ",");
		boolean sReturn=false;
		while(st.hasMoreTokens()) {
		if(st.nextToken().equalsIgnoreCase(value))
		{
			sReturn=true;
			break;
		}
		}
		return sReturn;
	}
	
	//--------------Code Added By Manish Shukla---------------
	java.util.ArrayList<RuleOutputParamInf> aList=new java.util.ArrayList<RuleOutputParamInf>();
	ApprovalAction[] act=null;
	public void setData()
	{
		act=new ApprovalAction[aList.size()];        		
		for (int i=0;i<aList.size();i++)
		{
			act[i]=(ApprovalAction)aList.get(i);
		}
	}
	public  void displayData()
	{        		
		//java.util.Arrays.sort(act);
		//System.out.println("Array Sorted");
		for (int i=0;i<act.length;i++)
		{
			System.out.println("Approver1RoleName...: "+act[i].getApprover1RoleName()+"   Approver2RoleName: "+act[i].getApprover2RoleName()+"   InvoiceRoleNameAlias: "+act[i].getInvoiceRoleNameAlias()+"    InvoiceStatusName: "+act[i].getInvoiceStatusName()+"    NextInvoiceRoleName: "+act[i].getNextInvoiceRoleName()+"    AreBothNeeded: "+act[i].getAreBothNeeded());
		}
	}
	public void getCount()
	{
		System.out.println("Final Length  :"+act.length);
	}
	public  void AddApprovalAction(RuleOutputParamInf approvalAction)
	{   
		try{
			aList.add(approvalAction);
		}
		catch(Exception e){System.out.println(e.getMessage());e.printStackTrace();}        		
		//aList.add(new ApprovalAction(Stage,Approver,Group,Treatment,Code));
	}	
	public RuleOutputParamInf[] getApprovalAction()
	{
		return act;
	}
	//--------------Code Added By Manish Shukla---Ends------------

}
