package org.sixlab.myqqtop.action;

import org.sixlab.myqqtop.service.UpdateDBService;

import com.opensymphony.xwork2.ActionSupport;

public class UpdateDBAction extends ActionSupport
{
	public UpdateDBService service;

	public UpdateDBService getService()
	{
		return service;
	}

	public void setService(UpdateDBService service)
	{
		this.service = service;
	}
	
	@Override
	public String execute() throws Exception
	{
		service.updateDB();
		System.out.println("------------done----------------");
		return SUCCESS;
	}
}
