package com.agesun.mybatis.bean;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public  class JsonDateValueProcessor implements JsonValueProcessor{

    private String pattern = "yyyy-MM-dd HH:mm:ss";

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }


    //定义两个构造函数，通过第二个构造函数可以自定义时间格式化字符串

    public JsonDateValueProcessor() {

        super();

    }

    public JsonDateValueProcessor(String format) {

        this.pattern= format;

    }

    @Override
    public Object processArrayValue(Object arg0, JsonConfig arg1) {
        return process(arg0);
    }

    @Override
    public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {

        return process(arg1);
    }


    private Object process(Object val){

        if(val instanceof Date && val!=null){

            SimpleDateFormat sdf=new SimpleDateFormat(this.pattern, Locale.CHINESE);

            return sdf.format(val);

        }else

        return val==null?"":val.toString();



    }
}
