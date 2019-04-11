package controller;

import bean.Line;
import bean.Planet;
import bean.Search;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import bean.TreeNode;

@Controller
public class DataController {

    //    植物种属层级数据
    @RequestMapping(value = "raceJson", method = RequestMethod.GET)
    @ResponseBody
    public List raceJsonData(){
//        返回一个TreeNode的数组，js需要小改动
        List arr = new ArrayList();
        TreeNode t1 = new TreeNode("2546","AMOUSSSSS","");
        TreeNode t2 = new TreeNode("2547","BJIOSJDPOS","");
        TreeNode t3 = new TreeNode("2548","小叶铮铮铮","2546");
        TreeNode t4 = new TreeNode("2549","六道木","2547");
        TreeNode t5 = new TreeNode("2550","多佛尔就","2548");
        arr.add(t1);
        arr.add(t2);
        arr.add(t3);
        arr.add(t4);
        arr.add(t5);
        return arr;
    }

    //    植物生长地数据
    @RequestMapping(value = "/planetGrowing", method = RequestMethod.POST)
    public List getGrow(@RequestParam("planet")String planet, Model model){
        return Planet.getGrowing(planet);
    }

    //    搜索结果数据
    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ResponseBody
//    @Autowired
//    自动装配有问题，暂未解决，暂时不重要，因为页面效果不一定。。再说吧
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

    //    植物图谱数据
    @RequestMapping(value = "graph", method = RequestMethod.GET)
    @ResponseBody
    public String graphData(){
        List lines = new ArrayList<Line>();
        List nodes = new ArrayList<Planet>();
        List result = new ArrayList();
        result.add(lines);
        result.add(nodes);
        //返回一个lines和nodes的集合，解析为json为类似下面的json字符串
        return "{\n" +
                "\t\"nodes\":[\n" +
                "\t\t{\n" +
                "\t\t\t\"name\" : \"给微软\",\n" +
                "\t\t\t\"e-name\" : \"dccd\",\n" +
                "\t\t\t\"status\" : 0,\n" +
                "\t\t\t\"r\" : 32\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"name\" : \"上的v二\",\n" +
                "\t\t\t\"e-name\" : \"fever\",\n" +
                "\t\t\t\"status\" : 0,\n" +
                "\t\t\t\"r\" : 38\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"name\" : \"fweweg\",\n" +
                "\t\t\t\"e-name\" : \"vger\",\n" +
                "\t\t\t\"status\" : 0,\n" +
                "\t\t\t\"r\" : 38\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"name\" : \"围观我\",\n" +
                "\t\t\t\"e-name\" : \"dsffever\",\n" +
                "\t\t\t\"status\" : 0,\n" +
                "\t\t\t\"r\" : 32\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\"lines\":[\n" +
                "\t\t{\n" +
                "\t\t\t\"source\": \"给微软\",\n" +
                "\t\t\t\"target\": \"围观我\",\n" +
                "\t\t\t\"text\" : \"11111\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"source\": \"围观我\",\n" +
                "\t\t\t\"target\": \"fweweg\",\n" +
                "\t\t\t\"text\" : \"22222\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"source\": \"上的v二\",\n" +
                "\t\t\t\"target\": \"fweweg\",\n" +
                "\t\t\t\"text\" : \"333\"\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";
    }
}
