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
        return Cypther.TraChildData(node);
    }


    //    植物种属非传统型层级数据
//    花草叶维度层级java何时关闭neo4j连接
    @RequestMapping(value = "getUntranChildsRaceJson", method = RequestMethod.GET)
    @ResponseBody
    public List getUntranChildsRaceJson(@RequestParam("node")String node){
        return Cypther.unTrachildData(node);
    }

    //    植物生长地数据
    @RequestMapping(value = "/planetGrowing", method = RequestMethod.GET)
    @ResponseBody
    public List getGrow(@RequestParam("planet")String planet){
        return Cypther.plantLocation(planet);
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
    public List graphData(@RequestBody Planet planet){
        return Cypther.graphData(planet.getcName());
    }
}
