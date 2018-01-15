package com.weix.commons;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CharsetUtils {
    private static final Pattern utf8Pattern = Pattern.compile("^([\\x01-\\x7f]|[\\xc0-\\xdf][\\x80-\\xbf]|[\\xe0-\\xef][\\x80-\\xbf]{2}|[\\xf0-\\xf7][\\x80-\\xbf]{3}|[\\xf8-\\xfb][\\x80-\\xbf]{4}|[\\xfc-\\xfd][\\x80-\\xbf]{5})+$");
    private static final Pattern publicPattern = Pattern.compile("^([\\x01-\\x7f]|[\\xc0-\\xdf][\\x80-\\xbf])+$");

    public static String getCharSet(String text){
        Matcher publicMatcher = publicPattern.matcher(text);
        if(publicMatcher.matches()){
            return "GBK";
        }
        Matcher matcher = utf8Pattern.matcher(text);
        if(matcher.matches()){
            return "UTF-8";
        }else{
            return "GBK";
        }
    }
}
