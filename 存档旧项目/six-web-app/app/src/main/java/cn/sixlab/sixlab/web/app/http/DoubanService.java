package cn.sixlab.sixlab.web.app.http;

import com.alibaba.fastjson.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DoubanService {

    @GET("v2/movie/search")
    Call<JSONObject> queryMovie(@Query("q") CharSequence q);

    @GET("v2/movie/subject/{subject}")
    Call<JSONObject> selectMovie(@Path("subject") String subject);
}
