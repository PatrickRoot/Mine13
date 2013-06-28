package org.sixlab.myqqtop.service.impl;

import java.util.Date;
import java.util.List;

import org.sixlab.myqqtop.bean.Content;
import org.sixlab.myqqtop.dao.ContentDao;
import org.sixlab.myqqtop.service.ListService;

public class ListServiceImpl implements ListService
{
	private ContentDao	dao;
	
	public ContentDao getDao()
	{
		return dao;
	}
	
	public void setDao(ContentDao dao)
	{
		this.dao = dao;
	}
	
	public List<Content> listContent(Date date)
	{
		List<Content> list = dao.listTopByTime(date);
		return list;
	}
	
}
