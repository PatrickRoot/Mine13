package cn.sixlab.apptest;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class TwoFragment extends BaseFragment {

    @Bind(R.id.mainB1)
    Button btn1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, null);
        ButterKnife.bind(this,view);

//        Button btn1 = (Button) view.findViewById(R.id.mainB1);
//        Button btn2 = (Button) view.findViewById(R.id.mainB2);
        Button btn3 = (Button) view.findViewById(R.id.mainB3);
        Button btn4 = (Button) view.findViewById(R.id.mainB4);
        Button btn5 = (Button) view.findViewById(R.id.mainB5);
        Button btn6 = (Button) view.findViewById(R.id.mainB6);
        Button btn7 = (Button) view.findViewById(R.id.mainB7);
        Button btn8 = (Button) view.findViewById(R.id.mainB8);
        Button btn9 = (Button) view.findViewById(R.id.mainB9);
        Button btn10 = (Button) view.findViewById(R.id.mainB10);

        btn1.setOnClickListener(v->{
            new SweetAlertDialog(getContext())
                    .setTitleText("Here's a message!")
                    .show();
        });
//        btn2.setOnClickListener(v->{
//            new SweetAlertDialog(getContext())
//                    .setTitleText("Here's a message!")
//                    .setContentText("It's pretty, isn't it?")
//                    .show();
//        });
        btn3.setOnClickListener(v->{
            new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops...")
                    .setContentText("Something went wrong!")
                    .show();
        });
        btn4.setOnClickListener(v->{
            new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Are you sure?")
                    .setContentText("Won't be able to recover this file!")
                    .setConfirmText("Yes,delete it!")
                    .show();
        });
        btn5.setOnClickListener(v->{
            new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Good job!")
                    .setContentText("You clicked the button!")
                    .show();
        });
        btn6.setOnClickListener(v->{
            new SweetAlertDialog(getContext(), SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                    .setTitleText("Sweet!")
                    .setContentText("Here's a custom image.")
                    .setCustomImage(R.drawable.ic_launcher)
                    .show();
        });
        btn7.setOnClickListener(v->{
            new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Are you sure?")
                    .setContentText("Won't be able to recover this file!")
                    .setConfirmText("Yes,delete it!")
                    .setConfirmClickListener(sDialog -> sDialog.dismissWithAnimation())
                    .show();
        });
        btn8.setOnClickListener(v->{
            new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Are you sure?")
                    .setContentText("Won't be able to recover this file!")
                    .setCancelText("No,cancel plx!")
                    .setConfirmText("Yes,delete it!")
                    .showCancelButton(true)
                    .setCancelClickListener(sDialog -> sDialog.cancel())
                    .show();
        });
        btn9.setOnClickListener(v->{
            new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Are you sure?")
                    .setContentText("Won't be able to recover this file!")
                    .setConfirmText("Yes,delete it!")
                    .setConfirmClickListener(sDialog -> sDialog
                            .setTitleText("Deleted!")
                            .setContentText("Your imaginary file has been deleted!")
                            .setConfirmText("OK")
                            .setConfirmClickListener(null)
                            .changeAlertType(SweetAlertDialog.SUCCESS_TYPE))
                    .show();
        });
        btn10.setOnClickListener(v->{
            SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.PROGRESS_TYPE);
            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            pDialog.setTitleText("Loading");
            pDialog.setCancelable(false);
            pDialog.show();
        });

        view.findViewById(R.id.basic_test).setOnClickListener(v-> doSth(v));
        view.findViewById(R.id.under_text_test).setOnClickListener(v->doSth(v));
        view.findViewById(R.id.error_text_test).setOnClickListener(v->doSth(v));
        view.findViewById(R.id.success_text_test).setOnClickListener(v->doSth(v));
        view.findViewById(R.id.warning_confirm_test).setOnClickListener(v->doSth(v));
        view.findViewById(R.id.warning_cancel_test).setOnClickListener(v->doSth(v));
        view.findViewById(R.id.custom_img_test).setOnClickListener(v->doSth(v));
        view.findViewById(R.id.progress_dialog).setOnClickListener(v->doSth(v));

        return view;
    }

    @OnClick(R.id.mainB2)
    public void test(View view){
        new SweetAlertDialog(getContext())
                .setTitleText("Here's a message!")
                .setContentText("It's pretty, isn't it?")
                .show();
    }

    private int i = -1;
    private void doSth(View v) {
        switch (v.getId()) {
            case R.id.basic_test:
                // default title "Here's a message!"
                SweetAlertDialog sd = new SweetAlertDialog(getContext());
                sd.setCancelable(true);
                sd.setCanceledOnTouchOutside(true);
                sd.show();
                break;
            case R.id.under_text_test:
                new SweetAlertDialog(getContext())
                        .setContentText("It's pretty, isn't it?")
                        .show();
                break;
            case R.id.error_text_test:
                new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Oops...")
                        .setContentText("Something went wrong!")
                        .show();
                break;
            case R.id.success_text_test:
                new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Good job!")
                        .setContentText("You clicked the button!")
                        .show();
                break;
            case R.id.warning_confirm_test:
                new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Are you sure?")
                        .setContentText("Won't be able to recover this file!")
                        .setConfirmText("Yes,delete it!")
                        .setConfirmClickListener(sDialog -> {
                            // reuse previous dialog instance
                            sDialog.setTitleText("Deleted!")
                                    .setContentText("Your imaginary file has been deleted!")
                                    .setConfirmText("OK")
                                    .setConfirmClickListener(null)
                                    .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        })
                        .show();
                break;
            case R.id.warning_cancel_test:
                new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Are you sure?")
                        .setContentText("Won't be able to recover this file!")
                        .setCancelText("No,cancel plx!")
                        .setConfirmText("Yes,delete it!")
                        .showCancelButton(true)
                        .setCancelClickListener(sDialog -> {
                            // reuse previous dialog instance, keep widget user state, reset them if you need
                            sDialog.setTitleText("Cancelled!")
                                    .setContentText("Your imaginary file is safe :)")
                                    .setConfirmText("OK")
                                    .showCancelButton(false)
                                    .setCancelClickListener(null)
                                    .setConfirmClickListener(null)
                                    .changeAlertType(SweetAlertDialog.ERROR_TYPE);

                            // or you can new a SweetAlertDialog to show
                           /* sDialog.dismiss();
                            new SweetAlertDialog(SampleActivity.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Cancelled!")
                                    .setContentText("Your imaginary file is safe :)")
                                    .setConfirmText("OK")
                                    .show();*/
                        })
                        .setConfirmClickListener(sDialog -> sDialog.setTitleText("Deleted!")
                                .setContentText("Your imaginary file has been deleted!")
                                .setConfirmText("OK")
                                .showCancelButton(false)
                                .setCancelClickListener(null)
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE))
                        .show();
                break;
            case R.id.custom_img_test:
                new SweetAlertDialog(getContext(), SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                        .setTitleText("Sweet!")
                        .setContentText("Here's a custom image.")
                        .setCustomImage(R.drawable.ic_launcher)
                        .show();
                break;
            case R.id.progress_dialog:
                final SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.PROGRESS_TYPE)
                        .setTitleText("Loading");
                pDialog.show();
                pDialog.setCancelable(false);
                new CountDownTimer(800 * 7, 800) {
                    public void onTick(long millisUntilFinished) {
                        // you can change the progress bar color by ProgressHelper every 800 millis
                        i++;
                        switch (i) {
                            case 0:
                                pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.blue_btn_bg_color));
                                break;
                            case 1:
                                pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_deep_teal_50));
                                break;
                            case 2:
                                pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.success_stroke_color));
                                break;
                            case 3:
                                pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_deep_teal_20));
                                break;
                            case 4:
                                pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_blue_grey_80));
                                break;
                            case 5:
                                pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.warning_stroke_color));
                                break;
                            case 6:
                                pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.success_stroke_color));
                                break;
                        }
                    }

                    public void onFinish() {
                        i = -1;
                        pDialog.setTitleText("Success!")
                                .setConfirmText("OK")
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                    }
                }.start();
                break;
        }
    }
}
