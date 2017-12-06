package cn.sixlab.app.mastercalculator.ft;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cn.sixlab.app.mastercalculator.R;

public class TimeFragment extends Fragment {

    private TextView dateBegin;
    private TextView dateEnd;
    private TextView timeBegin;
    private TextView timeEnd;
    private TextView timeResult;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    private Calendar begin = Calendar.getInstance();
    private Calendar end = Calendar.getInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time, null);
        dateBegin = (TextView) view.findViewById(R.id.time_begin_date);
        dateEnd = (TextView) view.findViewById(R.id.time_end_date);
        timeBegin = (TextView) view.findViewById(R.id.time_begin_time);
        timeEnd = (TextView) view.findViewById(R.id.time_end_time);

        timeResult = (TextView) view.findViewById(R.id.time_result);

        Date date = new Date();
        dateBegin.setText(dateFormat.format(date));
        timeBegin.setText(timeFormat.format(date));
        begin.setTime(date);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 7);
        calendar.set(Calendar.MINUTE, 30);
        dateEnd.setText(dateFormat.format(calendar.getTime()));
        timeEnd.setText(timeFormat.format(calendar.getTime()));
        end=calendar;

        dateBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                String str = String.valueOf(year) + "-";
                                if( month < 9 ){
                                    str += "0";
                                }
                                str += String.valueOf(month+1);
                                str +=  "-" ;
                                if( day < 10 ){
                                    str += "0";
                                }
                                str += String.valueOf(day);
                                dateBegin.setText(str);
                                begin.set(year, month, day);
                                changeTime();
                            }
                        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
                datePickerDialog.show();
            }
        });

        dateEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 1);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                String str = String.valueOf(year) + "-";
                                if( month < 9 ){
                                    str += "0";
                                }
                                str += String.valueOf(month+1);
                                str +=  "-" ;
                                if( day < 10 ){
                                    str += "0";
                                }
                                str += String.valueOf(day);
                                dateEnd.setText(str);
                                end.set(year, month, day);
                                changeTime();
                            }
                        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
                datePickerDialog.show();
            }
        });

        timeBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE,1);
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hour, int minute) {
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
                            }
                        }, 7,30, true);
                timePickerDialog.show();
            }
        });

        timeEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE,1);
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hour, int minute) {
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
                            }
                        }, 7,30, true);
                timePickerDialog.show();
            }
        });
        changeTime();
        return view;
    }

    public String changeTime() {
        Date beginDate = begin.getTime();
        Date endDate = end.getTime();
        long nd = 1000*24*60*60;//一天的毫秒数
        long nh = 1000*60*60;//一小时的毫秒数
        long nm = 1000*60;//一分钟的毫秒数
        long ns = 1000;//一秒钟的毫秒数
        long diff =  endDate.getTime() - beginDate.getTime();

        long day = diff/nd;//计算差多少天
        long hour = diff%nd/nh;//计算差多少小时
        long min = diff%nd%nh/nm;//计算差多少分钟
        long sec = diff%nd%nh%nm/ns;//计算差多少秒//输出结果

        String text =day+"天"+hour+"小时"+min+"分";
        timeResult.setText(text);
        return text;
    }
}
