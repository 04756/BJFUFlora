package controller;

import bean.Image;
import bean.Planet;
import bean.SearchResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
    public String planetPage(@PathVariable String planetName, Model model, HttpServletRequest request) throws IOException {
//        所属类群，一个SearchResult的list
        List race = new ArrayList();
        race.add(new SearchResult("是服务范围",""));
        race.add(new SearchResult("是服务范围",""));
        race.add(new SearchResult("是服务范围",""));
        race.add(new SearchResult("是服务范围",""));
        race.add(new SearchResult("是服务范围",""));
        model.addAttribute("race",race);
        model.addAttribute("imgList", new Image().getPlanetImgLinks(planetName, request));
//        植物的详细信息
        model.addAttribute("planet",new Planet("六道木属","AAAAAAA ssss","迥旺建瓯评价我怕v经常我怕v紧哦碰我的"));

        return "detail";
    }

    @RequestMapping(path = "/planetMap", method = RequestMethod.GET)
    public String mapPage(Model model){
        return "map";
    }

}
