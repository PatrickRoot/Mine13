package cn.sixlab.web.api.mapper;

import cn.sixlab.web.api.beans.HisMovie;

public interface HisMovieMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HisMovie record);

    int insertSelective(HisMovie record);

    HisMovie selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HisMovie record);

    int updateByPrimaryKey(HisMovie record);
}