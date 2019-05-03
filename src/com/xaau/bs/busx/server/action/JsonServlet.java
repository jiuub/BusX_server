package com.xaau.bs.busx.server.action;

import com.xaau.bs.busx.server.services.JsonService;
import com.xaau.bs.busx.server.util.JsonTools;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "JsonServlet")
public class JsonServlet extends HttpServlet {

  private JsonService service=new JsonService();
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html;charset=utf-8");
    request.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");
    PrintWriter out=response.getWriter();
    String jsonString="";
    String action_flag=request.getParameter("action_flag");
    String buscity=request.getParameter("buscity");
    System.out.println("action_flag: "+action_flag);
    if (action_flag.equals("line_go")){
      String busname=request.getParameter("busname");
      System.out.println(busname+"go");
      jsonString= JsonTools.createJsonString(service.getListBus(busname,"go",buscity));
    }else if (action_flag.equals("line_back")){
      String busname=request.getParameter("busname");
      System.out.println(busname+"back");
      jsonString= JsonTools.createJsonString(service.getListBus(busname,"back",buscity));
    }else if (action_flag.equals("bus")){
      String station=request.getParameter("station");
      System.out.println(station+"的bus");
      jsonString= JsonTools.createJsonString(service.getListStation(station,buscity));
    }else if (action_flag.equals("bus_detail")){
      String busname=request.getParameter("busname");
      jsonString= JsonTools.createJsonString(service.getSimpleBus(busname,buscity));
    }
    out.println(jsonString);
    out.flush();
    out.close();
  }
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request,response);
  }
}
