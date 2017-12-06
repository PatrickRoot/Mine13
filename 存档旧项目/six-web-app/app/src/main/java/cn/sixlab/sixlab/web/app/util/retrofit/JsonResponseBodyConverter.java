package cn.sixlab.sixlab.web.app.util.retrofit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class JsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    @Override
    public T convert(ResponseBody value) throws IOException {
        JSONObject jsonObject = JSON.parseObject(value.string());
        return (T) jsonObject;
    }
}