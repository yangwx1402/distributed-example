package com.young.distributed.core.utils;

import com.google.common.hash.Hashing;

import java.nio.charset.Charset;

/**
 * Created by yangyong on 17-5-7.
 */
public class CodecUtils {

    private static final String DEFAULT_ENCODE = "utf-8";

    public static String MD5(String source){
        return Hashing.md5().hashString(source, Charset.forName(DEFAULT_ENCODE)).toString();
    }

    public static void main(String[] args){
       System.out.println(CodecUtils.MD5("123123"));
    }
}
