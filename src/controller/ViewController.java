package controller;

import bean.CommonSearch;
import bean.Planet;
import bean.Search;
import bean.SearchResult;
import com.google.gson.Gson;
import com.sun.deploy.net.HttpResponse;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.cors.CorsConfiguration;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ViewController {

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String hello(){
        return "index";
    }

    @RequestMapping(value = "searchResult", method = RequestMethod.GET)
    public String searchHtml(){
        return "searchResult";
    }

    @RequestMapping(path = "/planet/{planetName}", method = RequestMethod.GET)
    public String planetPage(@PathVariable String planetName, Model model, HttpServletResponse response){
//        所属类群，一个SearchResult的list
        List race = new ArrayList();
        race.add(new SearchResult("是服务范围",""));
        race.add(new SearchResult("是服务范围",""));
        race.add(new SearchResult("是服务范围",""));
        race.add(new SearchResult("是服务范围",""));
        race.add(new SearchResult("是服务范围",""));
        model.addAttribute("race",race);
//        植物的详细信息
        model.addAttribute("planet",new Planet("六道木属","AAAAAAA ssss","迥旺建瓯评价我怕v经常我怕v紧哦碰我的"));
        // * 表示允许任何域名跨域访问
        response.setHeader("Access-Control-Allow-Origin", "*");
        CorsConfiguration corsConfiguration =new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 1
        corsConfiguration.addAllowedHeader("*"); // 2
        corsConfiguration.addAllowedMethod("*"); // 3

        return "detail";
    }

    @RequestMapping(path = "/planetMap", method = RequestMethod.GET)
    public String mapPage(Model model){
        return "map";
    }

}
