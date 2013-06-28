package org.sixlab.myqqtop.service;

import java.util.Date;
import java.util.List;

import org.sixlab.myqqtop.bean.Content;

public interface ListService
{
	public List<Content> listContent(Date date);
}
