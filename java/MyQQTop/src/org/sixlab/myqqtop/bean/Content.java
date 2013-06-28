package org.sixlab.myqqtop.bean;

import java.util.Date;
import java.util.Set;

public class Content
{
	private int			id;
	private String		usernick;
	private String		username;
	private String		content;
	private String		contentid;
	private int			count;
	private int			mcount;
	private Date		posttime;
	private Set<Rank>	rank;
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getUsernick()
	{
		return usernick;
	}
	
	public void setUsernick(String usernick)
	{
		this.usernick = usernick;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public String getContent()
	{
		return content;
	}
	
	public void setContent(String content)
	{
		this.content = content;
	}
	
	public String getContentid()
	{
		return contentid;
	}
	
	public void setContentid(String contentid)
	{
		this.contentid = contentid;
	}
	
	public int getCount()
	{
		return count;
	}
	
	public void setCount(int count)
	{
		this.count = count;
	}
	
	public int getMcount()
	{
		return mcount;
	}
	
	public void setMcount(int mcount)
	{
		this.mcount = mcount;
	}
	
	public Date getPosttime()
	{
		return posttime;
	}
	
	public void setPosttime(Date posttime)
	{
		this.posttime = posttime;
	}
	
	public Set<Rank> getRank()
	{
		return rank;
	}
	
	public void setRank(Set<Rank> rank)
	{
		this.rank = rank;
	}
	
}
