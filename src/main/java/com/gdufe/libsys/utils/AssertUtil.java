package com.gdufe.libsys.utils;


import com.gdufe.libsys.exceptions.ParamsException;

public class AssertUtil {

    //为空则抛出异常
    public  static void isTrue(Boolean flag,String msg){
        if(flag){
            throw new ParamsException(msg);
        }
    }
}
