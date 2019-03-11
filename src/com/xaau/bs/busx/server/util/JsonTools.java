package com.xaau.bs.busx.server.util;

import com.google.gson.Gson;

public class JsonTools {
  public JsonTools(){

  }

  /**
   * 将对象转化json 或者 把list 转化成json
   * @param obj
   * @param <T>
   * @return
   */
  public static <T> String createJsonString(T obj){
    Gson gson=new Gson();
    return gson.toJson(obj);
  }
}
