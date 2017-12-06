package cn.sixlab.sixlab.web.app.ft;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sixlab.sixlab.web.app.R;
import cn.sixlab.sixlab.web.app.util.BaseFragment;
import cn.sixlab.sixlab.web.app.util.Util;

public class TimeFragment extends BaseFragment {
    @Bind(R.id.time_begin_date) TextView dateBegin;
    @Bind(R.id.time_end_date) TextView dateEnd;
    @Bind(R.id.time_begin_time) TextView timeBegin;
    @Bind(R.id.time_end_time) TextView timeEnd;
    @Bind(R.id.time_result) TextView timeResult;

    private Calendar begin = Calendar.getInstance();
    private Calendar end = Calendar.getInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time, null);

        ButterKnife.bind(this,view);

        Date date = new Date();
        dateBegin.setText(Util.dateFormat.format(date));
        timeBegin.setText(Util.timeFormat.format(date));
        begin.setTime(date);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 7);
        calendar.set(Calendar.MINUTE, 30);
        dateEnd.setText(Util.dateFormat.format(calendar.getTime()));
        timeEnd.setText(Util.timeFormat.format(calendar.getTime()));
        end=calendar;

        changeTime();

        return view;
    }

    @OnClick(R.id.time_begin_date)
    public void dateBeginClick () {
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
                    dateBegin.setText(str);
                    begin.set(year, month, day);
                    changeTime();
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
        datePickerDialog.show();
    }

    @OnClick(R.id.time_begin_date)
    public void dateEndClick () {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
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
                    dateEnd.setText(str);
                    end.set(year, month, day);
                    changeTime();
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
        datePickerDialog.show();
    }

    @OnClick(R.id.time_begin_time)
    public void timeBeginClick () {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,1);
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                (view, hour, minute) -> {
                    String str = "";
                    if(hour<10){
                        str+="0";
                    }
                    str+=String.valueOf(hour);
                    str+=":";
                    if(minute<10){
                        str+="0";
                    }
                    str+=String.valueOf(minute) ;
                    timeBegin.setText(str);
                    begin.set(Calendar.HOUR_OF_DAY, hour);
                    begin.set(Calendar.MINUTE,minute);
                    changeTime();
                }, 7,30, true);
        timePickerDialog.show();
    }

    @OnClick(R.id.time_end_time)
    public void timeEndClick ()  {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,1);
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                (view, hour, minute) -> {
                    String str = "";
                    if(hour<10){
                        str+="0";
                    }
                    str+=String.valueOf(hour);
                    str+=":";
                    if(minute<10){
                        str+="0";
                    }
                    str+=String.valueOf(minute) ;
                    timeEnd.setText(str);
                    end.set(Calendar.HOUR_OF_DAY, hour);
                    end.set(Calendar.MINUTE, minute);
                    changeTime();
                }, 7,30, true);
        timePickerDialog.show();
    }

    public String changeTime() {
        Date beginDate = begin.getTime();
        Date endDate = end.getTime();
        long nd = 1000*24*60*60;//一天的毫秒数
        long nh = 1000*60*60;//一小时的毫秒数
        long nm = 1000*60;//一分钟的毫秒数
//        long ns = 1000;//一秒钟的毫秒数
        long diff =  endDate.getTime() - beginDate.getTime();

        long day = diff/nd;//计算差多少天
        long hour = diff%nd/nh;//计算差多少小时
        long min = diff%nd%nh/nm;//计算差多少分钟
//        long sec = diff%nd%nh%nm/ns;//计算差多少秒//输出结果

        String text =day+"天"+hour+"小时"+min+"分";
        timeResult.setText(text);
        return text;
    }

    @Override
    public void fabClick() {
        String time = changeTime();
        if(null!=time){
            Snackbar.make(getView(), time, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
}
