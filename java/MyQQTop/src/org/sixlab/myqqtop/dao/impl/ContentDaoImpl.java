package org.sixlab.myqqtop.dao.impl;

import java.util.Date;
import java.util.List;

import org.sixlab.myqqtop.bean.Content;
import org.sixlab.myqqtop.dao.ContentDao;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ContentDaoImpl extends HibernateDaoSupport implements ContentDao
{
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Content> listTopByTime(Date date)
	{
		String queryString = "from Content as c where c.posttime = '"
				+ date.toLocaleString() + "'";
		List<Content> list = this.getHibernateTemplate().find(queryString);
		return list;
	}
	
	public void saveContent(Content content)
	{
		this.getHibernateTemplate().saveOrUpdate(content);
	}
	
	@SuppressWarnings("unchecked")
	public Content findContentByContentID(String contentID)
	{
		String queryString = "from Content as c where c.contentid=" + contentID;
		List<Content> list = this.getHibernateTemplate().find(queryString);
		if (list == null | list.size() == 0)
		{
			return null;
		} else
		{
			return list.get(0);
		}
	}
}
