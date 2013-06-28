package org.sixlab.myqqtop.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;
import org.sixlab.myqqtop.bean.Content;

public class JsonUtil
{
	public static String stream2String(InputStream is)
	{
		InputStreamReader isr = null;
		try
		{
			isr = new InputStreamReader(is, "utf-8");
		} catch (UnsupportedEncodingException e1)
		{
			e1.printStackTrace();
		}
		String theJson = "";
		
		char[] buffer = new char[1024];
		int length = 0;
		try
		{
			while (-1 != (length = isr.read(buffer)))
			{
				theJson += String.valueOf(buffer, 0, length);
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return theJson;
	}
	
	public static Content fromJsonGetContent(JSONObject json)
	{
		Content content = new Content();
		
		content.setContent(fromJsonGetText(json));
		content.setContentid(fromJsonGetTextId(json));
		content.setCount(fromJsonGetCount(json));
		content.setMcount(fromJsonGetMcount(json));
		content.setPosttime(fromJsonGetDate(json));
		content.setUsername(fromJsonGetUsername(json));
		content.setUsernick(fromJsonGetUsernick(json));
		
		return content;
	}
	
	public static JSONArray fromJsonGetInfo(String source)
	{
		JSONObject json = new JSONObject(source);
		JSONArray info = json.getJSONObject("data").getJSONArray("info");
		return info;
	}
	
	private static String fromJsonGetUsernick(JSONObject json)
	{
		String usernick = json.getString("nick");
		
		return usernick;
	}
	
	private static String fromJsonGetUsername(JSONObject json)
	{
		String username = json.getString("name");
		
		return username;
	}
	
	private static String fromJsonGetText(JSONObject json)
	{
		String content = json.getString("origtext");
		return content;
	}
	
	private static String fromJsonGetTextId(JSONObject json)
	{
		String contentId = json.getString("id");
		
		return contentId;
	}
	
	private static int fromJsonGetCount(JSONObject json)
	{
		int count = json.getInt("count");
		
		return count;
	}
	
	private static int fromJsonGetMcount(JSONObject json)
	{
		int mCount = json.getInt("mcount");
		
		return mCount;
	}
	
	private static Date fromJsonGetDate(JSONObject json)
	{
		long time = json.getLong("timestamp");
		Date storeDate = new Date(time * 1000);
		return storeDate;
	}
}
