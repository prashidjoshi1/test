package com.ge.comfin.efs.drool;

import com.ge.comfin.efs.drool.RuleOutputParamInf;

import java.io.Serializable;

public class ApprovalAction extends RuleOutputParamInf implements Serializable {
	
	public String getInvoiceStatusName() {
		return invoiceStatusName;
	}
	public void setInvoiceStatusName(String invoiceStatusName) {
		this.invoiceStatusName = invoiceStatusName;
	}
	public String getNextInvoiceRoleName() {
		return nextInvoiceRoleName;
	}
	public void setNextInvoiceRoleName(String nextInvoiceRoleName) {
		this.nextInvoiceRoleName = nextInvoiceRoleName;
	}
	public String getInvoiceRoleNameAlias() {
		return invoiceRoleNameAlias;
	}
	public void setInvoiceRoleNameAlias(String invoiceRoleNameAlias) {
		this.invoiceRoleNameAlias = invoiceRoleNameAlias;
	}	
	public Boolean getAreBothNeeded() {
		return areBothNeeded;
	}
	public void setAreBothNeeded(Boolean areBothNeeded) {
		this.areBothNeeded = areBothNeeded;
	}
	private String nextInvoiceRoleName;
	private String invoiceRoleNameAlias;	
	private String approver1RoleName;
	public String getApprover1RoleName() {
		return approver1RoleName;
	}
	public void setApprover1RoleName(String approver1RoleName) {
		this.approver1RoleName = approver1RoleName;
	}
	public String getApprover2RoleName() {
		return approver2RoleName;
	}
	public void setApprover2RoleName(String approver2RoleName) {
		this.approver2RoleName = approver2RoleName;
	}
	private String approver2RoleName;
	private Boolean areBothNeeded;
	private String invoiceStatusName;	
}
