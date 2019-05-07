package controller;

import bean.*;
import com.google.gson.Gson;
import db.Neo4jDB;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DataController {

    Neo4jDB db=new Neo4jDB();
    //    植物种属层级数据
    @RequestMapping(value = "raceJson", method = RequestMethod.GET)
    @ResponseBody
    public List raceJsonData(){
        return Cypther.racetraditionalData();
    }

    //    植物种属非传统型层级数据
//    花草叶维度层级
    @RequestMapping(value = "untraditionalRaceJson", method = RequestMethod.GET)
    @ResponseBody
    public List raceUnTraditionalJsonData(){
//        返回一个TreeNode的数组，js需要小改动
        return Cypther.raceuntraditionalData();
    }

    //    植物种属传统型层级数据
//    传统维度层级java何时关闭neo4j连接
    @RequestMapping(value = "getChildsRaceJson", method = RequestMethod.GET)
    @ResponseBody
    public List raceChildsJsonData(@RequestParam("node")String node){
        return Cypther.unTraChildData(node);
    }


    //    植物种属非传统型层级数据
//    花草叶维度层级java何时关闭neo4j连接
    @RequestMapping(value = "getUntranChildsRaceJson", method = RequestMethod.GET)
    @ResponseBody
    public List getUntranChildsRaceJson(@RequestParam("node")String node){
        return Cypther.childData(node);
    }

    //    植物生长地数据
    @RequestMapping(value = "/planetGrowing", method = RequestMethod.GET)
    @ResponseBody
    public List getGrow(@RequestParam("planet")String planet){
        return Planet.getGrowing(planet);
    }

    //    搜索结果数据
    @RequestMapping(value = "/search", method = RequestMethod.POST)
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
//    根据前台传来的植物名获取图谱数据返回list
    //如果更改返回参数为list，将@ResponseBody的注释取消
    @RequestMapping(value = "/graph", method = RequestMethod.POST)
    @ResponseBody
    public String graphData(@RequestBody Planet planet){
        System.out.println(planet.getcName());
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
