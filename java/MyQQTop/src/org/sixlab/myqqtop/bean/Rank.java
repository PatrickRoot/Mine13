package org.sixlab.myqqtop.bean;

import java.util.Date;

public class Rank
{
	private int		id;
	private Date	gettime;
	private Content	content;
	
	public Content getContent()
	{
		return content;
	}
	
	public void setContent(Content content)
	{
		this.content = content;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public Date getGettime()
	{
		return gettime;
	}
	
	public void setGettime(Date gettime)
	{
		this.gettime = gettime;
	}
	
}
