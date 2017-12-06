package cn.sixlab.sixlab.web.app.ft;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import cn.sixlab.sixlab.web.app.R;
import cn.sixlab.sixlab.web.app.bean.DoubanMan;
import cn.sixlab.sixlab.web.app.bean.DoubanSearchMovie;
import cn.sixlab.sixlab.web.app.bean.DoubanSingleMovie;
import cn.sixlab.sixlab.web.app.bean.SixlabMovie;
import cn.sixlab.sixlab.web.app.http.DoubanService;
import cn.sixlab.sixlab.web.app.http.SixlabService;
import cn.sixlab.sixlab.web.app.util.BaseFragment;
import cn.sixlab.sixlab.web.app.util.Util;
import cn.sixlab.sixlab.web.app.util.retrofit.JsonConverterFactory;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MovieFragment extends BaseFragment {

    //界面初始元素
    @Bind(R.id.add_movie_text) TextView addMovieText;
    @Bind(R.id.movie_container) LinearLayout movieContainer;

    //添加时候的元素
    private TextView movieYear;
    private TextView movieDirector;
    private TextView movieRemark;
    private TextView movieViewDate;
    private TextView movieDouban;

    private boolean init = false;
    private String doubanId = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, null);

        ButterKnife.bind(this,view);

        fabClick();

        return view;
    }

    @OnClick(R.id.query_movie_btn)
    public void queryMovieClick() {
        movieContainer.removeAllViews();
        init = false;

        CharSequence movieName = addMovieText.getText();

        if(null!=movieName && movieName.length()!=0){
            final SweetAlertDialog pDialog = new SweetAlertDialog(getContext(),
                    SweetAlertDialog.PROGRESS_TYPE);
            pDialog.getProgressHelper().setBarColor(R.color.sweetAlertColor);
            pDialog.setTitleText("Loading");
            pDialog.setCancelable(false);
            pDialog.show();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Util.sixlab)
                    .addConverterFactory(JsonConverterFactory.create())
                    .build();//在这里可以添加 Gson转换器等;

            final SixlabService service = retrofit.create(SixlabService.class);//使用上面声明的接口创建

            Call<JSONObject> call = service.queryMovie(movieName.toString());//获取一个Call,才可以执行请求

            call.enqueue(new Callback<JSONObject>() {
                @Override
                public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                    pDialog.hide();
                    Log.e("sixlab", "isSuccessful:" + response.isSuccessful());
                    if(response.isSuccessful()){
                        JSONObject jsonObject = response.body();
                        Log.e("sixlab", "response:" + jsonObject);
                        if(jsonObject.getIntValue("num")>0){
//                        if(jsonObject.getBoolean("success") && jsonObject.getIntValue("num")>0){
                            LayoutInflater inflater = LayoutInflater.from(getContext());
                            JSONArray jsonArray = jsonObject.getJSONArray("movies");
                            for (Object object : jsonArray) {
                                JSONObject o = (JSONObject) object;
                                final SixlabMovie movie = JSONObject.toJavaObject(o,SixlabMovie.class);
                                View view = inflater.inflate(R.layout.movie_search_result, null);

                                TextView textView = (TextView) view.findViewById(R.id.result_name);
                                textView.setOnClickListener(v -> viewMovie(movie));
                                textView.setText(Html.fromHtml("<u>" + movie.getMovieName()+ "</u>"));

                                movieContainer.addView(view);
                            }
                        }else{
                            new SweetAlertDialog(getContext())
                                    .setTitleText("提示")
                                    .setContentText("未查询到结果")
                                    .show();
                        }
                    }else{
                        int status = response.code();
                        ResponseBody responseBody = response.errorBody();
                        Log.e("sixlab", "status:"+status);
                        Log.e("sixlab", "error:"+responseBody);
                    }
                }

                @Override
                public void onFailure(Call<JSONObject> call, Throwable t) {
                    pDialog.hide();
                    Log.e("sixlab", "error" + t.getMessage());
                }
            });
        }else{
            Toast.makeText(getContext(),"未输入电影名称",Toast.LENGTH_SHORT).show();
        }
    }

    private void viewMovie(SixlabMovie movie)  {
        String tips = movie.getMovieName()+"("+movie.getId()+")";

        if(!TextUtils.isEmpty(movie.getProduceYear())){
            tips += ("-"+movie.getProduceYear());
        }

        Log.e("sixlab", "view Date:"+movie.getViewDate());
        tips += ("\n日期："+ Util.formatDate(movie.getViewDate()));

        if(!TextUtils.isEmpty(movie.getDirector())){
            tips += ("\n导演："+movie.getDirector());
        }

        if(!TextUtils.isEmpty(movie.getRemark())){
            tips += ("\n备注："+movie.getRemark());
        }

        new SweetAlertDialog(getContext())
                .setTitleText("观影信息")
                .setContentText(tips)
                .show();
    }

    @Override
    public void fabClick() {
        if(!init){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View view = inflater.inflate(R.layout.movie_add, null);
            movieContainer.removeAllViews();
            movieContainer.addView(view);

            movieYear = (TextView) view.findViewById(R.id.movie_year);
            movieDirector = (TextView) view.findViewById(R.id.movie_director);
            movieRemark = (TextView) view.findViewById(R.id.movie_remark);
            movieViewDate = (TextView) view.findViewById(R.id.movie_view_date);
            movieDouban = (TextView) view.findViewById(R.id.movie_douban_score);

            Button doubanBtn = (Button) view.findViewById(R.id.movie_douban_btn);
            Button addBtn = (Button) view.findViewById(R.id.add_movie_confirm_btn);

            Date date = new Date();
            movieViewDate.setText(Util.dateFormat.format(date));

            movieViewDate.setOnFocusChangeListener((v, hasFocus) -> dateFocusChange(hasFocus));

            doubanBtn.setOnClickListener(v->doubanClick());
            addBtn.setOnClickListener(v->addClick());

            init = true;
        }
    }

    private void doubanClick () {
        CharSequence movieName = addMovieText.getText();

        if(null!=movieName && movieName.length()!=0) {
            final SweetAlertDialog pDialog = new SweetAlertDialog(getContext(),
                    SweetAlertDialog.PROGRESS_TYPE);
            pDialog.getProgressHelper().setBarColor(R.color.sweetAlertColor);
            pDialog.setTitleText("Loading");
            pDialog.setCancelable(false);
            pDialog.show();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Util.douban)
                    .addConverterFactory(JsonConverterFactory.create())
                    .build();//在这里可以添加 Gson转换器等;

            final DoubanService service = retrofit.create(DoubanService.class);//使用上面声明的接口创建

            Call<JSONObject> call = service.queryMovie(movieName);//获取一个Call,才可以执行请求

            call.enqueue(new Callback<JSONObject>() {
                @Override
                public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                    pDialog.hide();
                    Log.e("sixlab", "isSuccessful:" + response.isSuccessful());
                    if(response.isSuccessful()){
                        JSONObject jsonObject = response.body();
                        Log.e("sixlab", "jsonObject:" + jsonObject);
                        final JSONArray subjects = jsonObject.getJSONArray("subjects");
                        String[] items = new String[subjects.size()];
                        int i =0;
                        for (Object object : subjects) {
                            JSONObject o = (JSONObject) object;
                            final DoubanSearchMovie movie = JSONObject.toJavaObject(o,DoubanSearchMovie.class);
                            items[i++] = movie.getYear() + ":" + movie.getTitle();
                        }

                        new AlertDialog.Builder(getContext())
                                .setTitle("单选框")
                                .setItems(items, (dialog, which) -> {
                                    selectMovie((JSONObject) subjects.get(which));
                                })
                                .setNegativeButton("取消", null)
                                .show();
                    }else{
                        int status = response.code();
                        ResponseBody responseBody = response.errorBody();
                        Log.e("sixlab", "status:"+status);
                        Log.e("sixlab", "error:"+responseBody);
                    }
                }

                @Override
                public void onFailure(Call<JSONObject> call, Throwable t) {
                    pDialog.hide();
                    Log.e("sixlab", "error" + t.getMessage());
                }
            });
        }
    }

    private void selectMovie(JSONObject subject) {
        String id = subject.getString("id");
        doubanId = id;

        final SweetAlertDialog pDialog = new SweetAlertDialog(getContext(),
                SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(R.color.sweetAlertColor);
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Util.douban)
                .addConverterFactory(JsonConverterFactory.create())
                .build();//在这里可以添加 Gson转换器等;

        final DoubanService service = retrofit.create(DoubanService.class);//使用上面声明的接口创建

        Call<JSONObject> call = service.selectMovie(id);//获取一个Call,才可以执行请求

        call.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                pDialog.hide();
                Log.e("sixlab", "isSuccessful:" + response.isSuccessful());
                if(response.isSuccessful()){
                    JSONObject jsonObject = response.body();
                    Log.e("sixlab", "jsonObject:" + jsonObject);
                    DoubanSingleMovie movie = JSONObject.toJavaObject(jsonObject, DoubanSingleMovie.class);
                    String movieName = movie.getTitle();
                    if(!TextUtils.isEmpty(movieName)){
                        addMovieText.setText(movieName);
                    }

                    String year = movie.getYear();
                    if(!TextUtils.isEmpty(year)){
                        movieYear.setText(year);
                    }

                    List<DoubanMan> directors = movie.getDirectors();
                    if(null!=directors && directors.size()>0){
                        String str = "";
                        for (DoubanMan director : directors) {
                            str+=director.getName();
                            str+=",";
                        }
                        if(str.length()>0){
                            str=str.substring(0,str.length()-1);
                        }
                        movieDirector.setText(str);
                    }

                    if(null!=movie.getRating()){
                        movieDouban.setText(movie.getRating().getAverage()+"");
                    }

                    String remark = "";
                    String oTitle = movie.getOriginalTitle();
                    if(!TextUtils.isEmpty(oTitle)){
                        remark = "<原始标题>："+oTitle+"。";
                    }

                    String countryTitle = "";
                    String[] countries = movie.getCountries();
                    if(null!=countries && countries.length>0){
                        for (String country : countries) {
                            countryTitle+=country;
                            countryTitle+=",";
                        }
                    }
                    if(countryTitle.length()>0){
                        countryTitle="<国家>："+countryTitle.substring(0,countryTitle.length()-1);
                    }
                    remark += countryTitle;

                    String akaTitle = "";
                    String[] akaArray = movie.getAka();
                    if(null!=akaArray && akaArray.length>0){
                        for (String aka : akaArray) {
                            akaTitle+=aka;
                            akaTitle+=",";
                        }
                    }
                    if(akaTitle.length()>0){
                        akaTitle="<别名>："+akaTitle.substring(0,akaTitle.length()-1);
                    }
                    remark += akaTitle;
                    movieRemark.setText(remark);
                }else{
                    Log.e("sixlab", "errorBody:" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                pDialog.hide();
                Log.e("sixlab", "error:" + t.getMessage());
            }
        });
    }

    private void addClick () {
        SixlabMovie movie = new SixlabMovie();
        CharSequence text = addMovieText.getText();
        if(!TextUtils.isEmpty(text)){
            movie.setMovieName(text.toString());
        }

        CharSequence year = movieYear.getText();
        if(!TextUtils.isEmpty(year)){
            movie.setProduceYear(year.toString());
        }

        CharSequence director = movieDirector.getText();
        if(!TextUtils.isEmpty(director)){
            movie.setDirector(director.toString());
        }

        CharSequence douan = movieDouban.getText();
        if(!TextUtils.isEmpty(douan) ){
            movie.setDoubanScore(Double.valueOf(douan.toString()));
        }

        CharSequence remark = movieRemark.getText();
        if(!TextUtils.isEmpty(remark)){
            movie.setRemark(remark.toString());
        }

        movie.setDoubanKey(doubanId);
        doubanId=null;

        CharSequence date = movieViewDate.getText();
        if(!TextUtils.isEmpty(date)){
            movie.setViewDate(date.toString());
        }

        final SweetAlertDialog pDialog = new SweetAlertDialog(getContext(),
                SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(R.color.sweetAlertColor);
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Util.sixlab)
                .addConverterFactory(JsonConverterFactory.create())
                .build();//在这里可以添加 Gson转换器等;

        final SixlabService service = retrofit.create(SixlabService.class);//使用上面声明的接口创建

        Call<JSONObject> call = service.addMovie(movie.getMovieName(),movie.getProduceYear(),
                movie.getDirector(),movie.getRemark(),movie.getViewDate(),
                movie.getDoubanScore(),movie.getDoubanKey());//获取一个Call,才可以执行请求

        call.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                pDialog.hide();
                Log.e("sixlab", "isSuccessful:" + response.isSuccessful());
                if(response.isSuccessful()){
                    Log.e("sixlab", "body:" + response.body());
                    new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Good job!")
                            .setContentText("You clicked the button!").show();
                }else{
                    Log.e("sixlab", "errorBody:" + response.errorBody());
                    new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oh NO!")
                            .setContentText("You have a error!")
                            .show();
                }
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                pDialog.hide();
                Log.e("sixlab", "error:" + t.getMessage());
            }
        });
    }

    private void dateFocusChange(boolean  hasFocus) {
        if(hasFocus){
            Calendar cal = Calendar.getInstance();
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                    (view, year, month, day) -> {
                        String str = String.valueOf(year) + "-";
                        if (month < 9) {
                            str += "0";
                        }
                        str += String.valueOf(month + 1);
                        str += "-";
                        if (day < 10) {
                            str += "0";
                        }
                        str += String.valueOf(day);
                        movieViewDate.setText(str);
                    }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
            datePickerDialog.show();
        }
    }
}