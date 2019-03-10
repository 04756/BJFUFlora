package controller;

import bean.Search;
import bean.SearchResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ViewController {

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String hello(){
        return "index";
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public List<SearchResult> search(Search s){
        return s.search();
    }
}
