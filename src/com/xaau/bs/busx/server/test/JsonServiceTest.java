package com.xaau.bs.busx.server.test;

import com.xaau.bs.busx.server.services.JsonService;
import com.xaau.bs.busx.server.util.JsonTools;
import org.junit.Test;

public class JsonServiceTest {

  @Test
  public void getListBus() {
    String msg="";
    JsonService service=new JsonService();
    msg= JsonTools.createJsonString(service.getListStation("土门"));
    System.out.println(msg);
  }
}