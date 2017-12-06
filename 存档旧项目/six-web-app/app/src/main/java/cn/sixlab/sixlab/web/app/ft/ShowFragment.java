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

import cn.pedant.SweetAlert.SweetAlertDialog;
import cn.sixlab.sixlab.web.app.R;
import cn.sixlab.sixlab.web.app.bean.DoubanMan;
import cn.sixlab.sixlab.web.app.bean.DoubanSearchMovie;
import cn.sixlab.sixlab.web.app.bean.DoubanSingleMovie;
import cn.sixlab.sixlab.web.app.bean.SixlabShow;
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

public class ShowFragment extends BaseFragment {

    //界面初始元素
    private TextView addShowText;
    private Button queryShowBtn;
    private LinearLayout showContainer;

    private boolean init = false;
    private String doubanId = "";
    private TextView showViewDate;
    private TextView showAddSeason;
    private TextView showAddEpisode;
    private TextView showTv;
    private TextView showRemark;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show, null);

        addShowText = (TextView) view.findViewById(R.id.add_show_text);
        queryShowBtn = (Button) view.findViewById(R.id.query_show_btn);
        showContainer = (LinearLayout) view.findViewById(R.id.show_container);

        queryShowBtn.setOnClickListener(v -> queryMovieClick());

        fabClick();

        return view;
    }

    private void queryMovieClick() {
        showContainer.removeAllViews();
        init = false;

        CharSequence showText = addShowText.getText();

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

        Call<JSONObject> call = service.queryShow(showText.toString());//获取一个Call,才可以执行请求

        call.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                pDialog.hide();
                Log.e("sixlab", "isSuccessful:" + response.isSuccessful());
                if(response.isSuccessful()){
                    JSONObject jsonObject = response.body();
                    Log.e("sixlab", "response:" + jsonObject);
                    if(jsonObject.getIntValue("num")>0){
//                    if(jsonObject.getBoolean("success") && jsonObject.getIntValue("num")>0){
                        LayoutInflater inflater = LayoutInflater.from(getContext());
                        JSONArray jsonArray = jsonObject.getJSONArray("shows");
                        for (Object object : jsonArray) {
                            JSONObject o = (JSONObject) object;
                            final SixlabShow show = JSONObject.toJavaObject(o,SixlabShow.class);
                            View view = inflater.inflate(R.layout.show_search_result, null);

                            TextView textView = (TextView) view.findViewById(R.id.result_name);
                            textView.setOnClickListener(v -> viewShow(show));
                            textView.setText(Html.fromHtml("<u>" + show.getShowName()+ "</u>"));

                            Button resultSeason = (Button) view.findViewById(R.id.result_season);
                            resultSeason.setText("S"+show.getShowSeason());
                            resultSeason.setOnClickListener(v->addSeason(show));

                            Button resultEpisode = (Button) view.findViewById(R.id.result_episode);
                            resultEpisode.setText("S"+show.getShowEpisode());
                            resultEpisode.setOnClickListener(v->addEpisode(show));

                            showContainer.addView(view);
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
    }

    private void addEpisode( SixlabShow show) {
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

        Call<JSONObject> call = service.addEpisode(show.getId());//获取一个Call,才可以执行请求

        call.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                pDialog.hide();
                Log.e("sixlab", "isSuccessful:" + response.isSuccessful());
                if(response.isSuccessful()){
                    JSONObject jsonObject = response.body();
                    Log.e("sixlab", "response:" + jsonObject);
                    try{
                        queryShowBtn.performClick();
                    }catch (Exception e){
                        new SweetAlertDialog(getContext())
                                .setTitleText("提示")
                                .setContentText("服务器处理异常")
                                .show();
                    }
//                    if(jsonObject.getBoolean("success")){
//                        queryShowBtn.performClick();
//                    }else{
//                        new SweetAlertDialog(getContext())
//                                .setTitleText("提示")
//                                .setContentText("服务器处理异常")
//                                .show();
//                    }
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

    private void addSeason(SixlabShow show) {
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

        Call<JSONObject> call = service.addSeason(show.getId());//获取一个Call,才可以执行请求

        call.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                pDialog.hide();
                Log.e("sixlab", "isSuccessful:" + response.isSuccessful());
                if(response.isSuccessful()){
                    JSONObject jsonObject = response.body();
                    Log.e("sixlab", "response:" + jsonObject);
//                    if(jsonObject.getBoolean("success")){
                        queryShowBtn.performClick();
//                    }else{
//                        new SweetAlertDialog(getContext())
//                                .setTitleText("提示")
//                                .setContentText("服务器处理异常")
//                                .show();
//                    }
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

    private void viewShow(SixlabShow show) {
        String tips = show.getShowName()+"("+show.getId()+")";

        if(!TextUtils.isEmpty(show.getTv())){
            tips += ("-"+show.getTv());
        }

        String beginDate = "";
        if(null!=show.getBeginDate() && !"".equals(show.getBeginDate())){
            beginDate = show.getBeginDate().substring(0,10);
        }
        tips += ("\n开始日期："+ beginDate);

        if(!TextUtils.isEmpty(show.getRemark())){
            tips += ("\n备注："+show.getRemark());
        }

        new SweetAlertDialog(getContext())
                .setTitleText("剧集信息")
                .setContentText(tips)
                .show();
    }

    @Override
    public void fabClick() {
        if(!init){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View view = inflater.inflate(R.layout.show_add, null);
            showContainer.removeAllViews();
            showContainer.addView(view);

            showAddSeason = (TextView) view.findViewById(R.id.show_add_season);
            showAddSeason.setText("1");

            showAddEpisode = (TextView) view.findViewById(R.id.show_add_episode);
            showAddEpisode.setText("1");

            showTv = (TextView) view.findViewById(R.id.show_add_tv);
            showRemark = (TextView) view.findViewById(R.id.show_add_remark);

            showViewDate = (TextView) view.findViewById(R.id.show_add_view);
            Date date = new Date();
            showViewDate.setText(Util.dateFormat.format(date));
            showViewDate.setOnFocusChangeListener((v, hasFocus) -> dateFocusChange(hasFocus));

            Button doubanBtn = (Button) view.findViewById(R.id.show_douban_btn);
            doubanBtn.setOnClickListener(v->doubanClick());

            Button addBtn = (Button) view.findViewById(R.id.add_show_confirm_btn);
            addBtn.setOnClickListener(v->addClick());

            init = true;
        }
    }

    private void doubanClick () {
        CharSequence showName = addShowText.getText();

        if(!TextUtils.isEmpty(showName)) {
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

            Call<JSONObject> call = service.queryMovie(showName);//获取一个Call,才可以执行请求

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
                        addShowText.setText(movieName);
                    }

                    String remark = "";
                    List<DoubanMan> directors = movie.getDirectors();
                    if(null!=directors && directors.size()>0){
                        String str = "";
                        for (DoubanMan director : directors) {
                            str+=director.getName();
                            str+=",";
                        }
                        if(str.length()>0){
                            str=str.substring(0,str.length()-1);
                            remark = "导演："+str+";";
                        }
                    }

                    String oTitle = movie.getOriginalTitle();
                    if(!TextUtils.isEmpty(oTitle)){
                        remark += "<原始标题>："+oTitle+"。";
                    }

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
                    showRemark.setText(remark);
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
        SixlabShow show = new SixlabShow();
        CharSequence text = addShowText.getText();
        if(!TextUtils.isEmpty(text)){
            show.setShowName(text.toString());
        }

        CharSequence season = showAddSeason.getText();
        if(!TextUtils.isEmpty(season) && TextUtils.isDigitsOnly(season)){
            show.setShowSeason(Integer.valueOf(season.toString()));
        }else{
            show.setShowSeason(1);
        }

        CharSequence episode = showAddEpisode.getText();
        if(!TextUtils.isEmpty(episode) && TextUtils.isDigitsOnly(episode)){
            show.setShowEpisode(Integer.valueOf(episode.toString()));
        }else{
            show.setShowEpisode(1);
        }

        CharSequence date = showViewDate.getText();
        if(!TextUtils.isEmpty(date)){
            show.setBeginDate(date.toString());
        }

        CharSequence remark = showRemark.getText();
        if(!TextUtils.isEmpty(remark)){
            show.setRemark(remark.toString());
        }

        CharSequence tv = showTv.getText();
        if(!TextUtils.isEmpty(tv)){
            show.setTv(tv.toString());
        }

        show.setDoubanKey(doubanId);
        doubanId=null;

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

        Call<JSONObject> call = service.addShow(show.getShowName(),show.getShowSeason(),
                show.getShowEpisode(),show.getBeginDate(),show.getRemark(),show.getTv(),
                show.getDoubanKey());//获取一个Call,才可以执行请求

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

    private void dateFocusChange  (boolean  hasFocus) {
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
                        showViewDate.setText(str);
                    }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
            datePickerDialog.show();
        }
    }
}
