package com.thgmobi.truckpadweather.helper;

import android.widget.ImageView;

import com.thgmobi.truckpadweather.R;

public abstract class ChangeIconHelper {

    public static void changeIconWeather(ImageView iv, String conditionCode) {

        if (conditionCode.equals("1")) {
            iv.setImageResource(R.drawable.ic_1);
        }else if(conditionCode.equals("3") || conditionCode.equals("4")) {
            iv.setImageResource(R.drawable.ic_3_4);
        }else if(conditionCode.equals("5") || conditionCode.equals("6")|| conditionCode.equals("7")) {
            iv.setImageResource(R.drawable.ic_5_6_7);
        }else if(conditionCode.equals("8") || conditionCode.equals("10")|| conditionCode.equals("17")) {
            iv.setImageResource(R.drawable.ic_8_10_17);
        }else if(conditionCode.equals("9") || conditionCode.equals("11")|| conditionCode.equals("40")) {
            iv.setImageResource(R.drawable.ic_9_11_12_40);
        }else if(conditionCode.equals("13") || conditionCode.equals("16")|| conditionCode.equals("42") || conditionCode.equals("46")) {
            iv.setImageResource(R.drawable.ic_13_16_42_46);
        }else if(conditionCode.equals("14")) {
            iv.setImageResource(R.drawable.ic_14);
        }else if(conditionCode.equals("15") || conditionCode.equals("41")|| conditionCode.equals("43")) {
            iv.setImageResource(R.drawable.ic_15_41_43);
        }else if(conditionCode.equals("18") || conditionCode.equals("35")) {
            iv.setImageResource(R.drawable.ic_18_35);
        }else if(conditionCode.equals("19")) {
            iv.setImageResource(R.drawable.ic_19);
        }else if(conditionCode.equals("20")) {
            iv.setImageResource(R.drawable.ic_20);
        }else if(conditionCode.equals("21")) {
            iv.setImageResource(R.drawable.ic_21);
        }else if(conditionCode.equals("22")) {
            iv.setImageResource(R.drawable.ic_22);
        }else if(conditionCode.equals("23") || conditionCode.equals("24")) {
            iv.setImageResource(R.drawable.ic_23_24);
        }else if(conditionCode.equals("25")) {
            iv.setImageResource(R.drawable.ic_25);
        }else if(conditionCode.equals("26")) {
            iv.setImageResource(R.drawable.ic_26);
        }else if(conditionCode.equals("27") || conditionCode.equals("29")) {
            iv.setImageResource(R.drawable.ic_27_29);
        }else if(conditionCode.equals("28") || conditionCode.equals("30")) {
            iv.setImageResource(R.drawable.ic_28_30);
        }else if(conditionCode.equals("31")) {
            iv.setImageResource(R.drawable.ic_31);
        }else if(conditionCode.equals("32")) {
            iv.setImageResource(R.drawable.ic_32);
        }else if(conditionCode.equals("3200")) {
            iv.setImageResource(R.drawable.ic_3200);
        }else if(conditionCode.equals("33")) {
            iv.setImageResource(R.drawable.ic_33);
        }else if(conditionCode.equals("34")) {
            iv.setImageResource(R.drawable.ic_34);
        }else if(conditionCode.equals("36")) {
            iv.setImageResource(R.drawable.ic_36);
        }else if(conditionCode.equals("37") || conditionCode.equals("38") ||
                conditionCode.equals("39") || conditionCode.equals("45") || conditionCode.equals("47")) {
            iv.setImageResource(R.drawable.ic_37_38_39_45_47);
        }else if(conditionCode.equals("44")) {
            iv.setImageResource(R.drawable.ic_44);
        }
    }
}
