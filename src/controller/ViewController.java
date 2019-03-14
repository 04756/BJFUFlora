package controller;

import bean.CommonSearch;
import bean.Search;
import com.google.gson.Gson;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ResponseBody
//    @Autowired
//    自动装配有问题，未解决
    public List search(@RequestBody String s){
        try{
            Search search = new Gson().fromJson(s, Search.class);
        return search.search();
       }
        catch (Exception e){
           e.printStackTrace();
           return null;
        }
    }

    @RequestMapping(path = "/planet/{planetName}", method = RequestMethod.GET)
    public String planetPage(@PathVariable String planetName, Model model){
        return "detail";
    }

    @RequestMapping(path = "/planetMap", method = RequestMethod.GET)
    public String mapPage(Model model){
        return "map";
    }

}
