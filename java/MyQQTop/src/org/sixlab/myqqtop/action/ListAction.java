package org.sixlab.myqqtop.action;

import java.util.Date;
import java.util.List;

import org.sixlab.myqqtop.bean.Content;
import org.sixlab.myqqtop.service.ListService;

import com.opensymphony.xwork2.ActionSupport;

public class ListAction extends ActionSupport
{
	private Date			theDate;
	private Date			theTime;
	private ListService		service;
	private List<Content>	topContent;
	
	public List<Content> getTopContent()
	{
		return topContent;
	}
	
	public Date getTheTime()
	{
		return theTime;
	}
	
	public void setTheTime(Date theTime)
	{
		this.theTime = theTime;
	}
	
	public Date getTheDate()
	{
		return theDate;
	}
	
	public void setTheDate(Date theDate)
	{
		this.theDate = theDate;
	}
	
	public ListService getService()
	{
		return service;
	}
	
	public void setService(ListService service)
	{
		this.service = service;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public String execute() throws Exception
	{
		Date newDate = new Date(theDate.getYear(), theDate.getMonth(),
				theDate.getDate(), theTime.getHours(), 0);
		
		topContent = service.listContent(newDate);
		return SUCCESS;
	}
}
