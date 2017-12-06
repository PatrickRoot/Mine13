package cn.sixlab.sixlab.web.app.http;


import com.alibaba.fastjson.JSONObject;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SixlabService {

    //    @GET("tool/movie/search/{keyword}")
    //    SixlabMovies searchMovie(@Path("keyword") String keyword);
    //    SixlabMovies searchMovie(@Query("keyword") String keyword);
    //    Call<ResponseBody> get();

    //    @POST("tool/movie/search")
    //    Call<ResponseBody> queryMovie(@Query("keyword")String keyword);

    @POST("tool/movie/search")
    Call<JSONObject> queryMovie(@Query("keyword") String keyword);

    @POST("tool/movie/add")
    Call<JSONObject> addMovie(@Query("movieName") String movieName,
                              @Query("produceYear") String produceYear,
                              @Query("director") String director,
                              @Query("remark") String remark,
                              @Query("viewDate") String viewDate,
                              @Query("doubanScore") double doubanScore,
                              @Query("doubanKey") String doubanKey);

    @POST("tool/show/search")
    Call<JSONObject> queryShow(@Query("keyword") String keyword);

    @POST("tool/show/season/add")
    Call<JSONObject> addSeason(@Query("id") int id);

    @POST("tool/show/episode/add")
    Call<JSONObject> addEpisode(@Query("id") int id);

    @POST("tool/show/end")
    Call<JSONObject> endShow(@Query("id") int id);

    @POST("tool/show/finish")
    Call<JSONObject> finishShow(@Query("id") int id);

    @POST("tool/show/add")
    Call<JSONObject> addShow(@Query("showName") String showName,
                             @Query("showSeason") int showSeason,
                             @Query("showEpisode") int showEpisode,
                             @Query("beginDate") String beginDate,
                             @Query("remark") String remark,
                             @Query("tv") String tv,
                             @Query("doubanKey") String doubanKey);
}