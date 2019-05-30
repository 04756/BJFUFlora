package controller;

import bean.Cypther;
import bean.Plant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class ViewController {

    @RequestMapping("/index") public String homepage(Model model){
        System.out.println("this is homepage!!!");
        return "index";
    }


    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String hello(){
        return "index";
    }

    @RequestMapping(value = "searchResult", method = RequestMethod.GET)
    public String searchHtml(){
        return "searchResult";
    }

    @RequestMapping(path = "/plant/{plantName}", method = RequestMethod.GET)
    public String planetPage(@PathVariable String plantName, Model model, HttpServletRequest request) throws IOException {
//        所属类群，一个SearchResult的list
        Plant plant =Cypther.getPlantDetail(plantName);
        model.addAttribute("race", plant.getRace());
        model.addAttribute("imgList", plant.getImglist());
//        植物的详细信息
        model.addAttribute("plant", plant);

        return "detail";
    }

    @RequestMapping(path = "/plantMap", method = RequestMethod.GET)
    public String mapPage(Model model){
        return "map";
    }

}
