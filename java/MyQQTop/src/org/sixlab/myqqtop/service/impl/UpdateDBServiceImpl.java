package org.sixlab.myqqtop.service.impl;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashSet;

import org.json.JSONArray;
import org.json.JSONObject;
import org.sixlab.myqqtop.bean.Content;
import org.sixlab.myqqtop.bean.Rank;
import org.sixlab.myqqtop.dao.ContentDao;
import org.sixlab.myqqtop.service.UpdateDBService;
import org.sixlab.myqqtop.util.JsonUtil;
import org.sixlab.myqqtop.util.UrlUtil;

public class UpdateDBServiceImpl implements UpdateDBService
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
	
	@SuppressWarnings("deprecation")
	public void updateDB()
	{
		String jsonString = fromQQGetData();
		
		JSONArray jsonArray = JsonUtil.fromJsonGetInfo(jsonString);
		Date nowDate = new Date((new Date().getTime()/1000)*1000);
		nowDate.setSeconds(0);
		nowDate.setMinutes(0);
		
		for (int i = 0; i < jsonArray.length(); i++)
		{
			JSONObject eachContent = jsonArray.getJSONObject(i);
			Content content = JsonUtil.fromJsonGetContent(eachContent);
			Rank rank = new Rank();
			rank.setGettime(nowDate);
			Content content2 = dao.findContentByContentID(content
					.getContentid());
			if (content2 == null)
			{
				content.setRank(new HashSet<Rank>());
				content.getRank().add(rank);
				rank.setContent(content);
				dao.saveContent(content);
			} else
			{
				content2.getRank().add(rank);
				rank.setContent(content2);
				dao.saveContent(content2);
			}
		}
	}
	
	private String fromQQGetData()
	{
		String theJson = "";
		
		String urlString = UrlUtil.getUrl();
		URL url;
		URLConnection urlConn;
		InputStream is;
		try
		{
			url = new URL(urlString.toString());
			urlConn = url.openConnection();
			is = urlConn.getInputStream();
			theJson = JsonUtil.stream2String(is);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return theJson;
	}
}
