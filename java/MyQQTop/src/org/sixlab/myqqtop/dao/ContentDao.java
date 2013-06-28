package org.sixlab.myqqtop.dao;

import java.util.Date;
import java.util.List;

import org.sixlab.myqqtop.bean.Content;

public interface ContentDao
{
	public List<Content> listTopByTime(Date date);
	
	public void saveContent(Content content);
	
	public Content findContentByContentID(String contentID);
}
