package controller;

import bean.Planet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DataController {

//    植物种属层级数据
    @RequestMapping(value = "raceJson", method = RequestMethod.GET)
    @ResponseBody
    public String raceJsonData(){
//        返回一个TreeNode的数组，js需要小改动
        return "{\n" +
                "\t\"nodes\": [{\n" +
                "\t\t\t\"id\": \"2546\",\n" +
                "\t\t\t\"text\": \"无法慰安妇\",\n" +
                "\t\t\t\"parent\": \"2547\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": \"2547\",\n" +
                "\t\t\t\"text\": \"测却\",\n" +
                "\t\t\t\"parent\": \"\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": \"2548\",\n" +
                "\t\t\t\"text\": \"劝我放弃二\",\n" +
                "\t\t\t\"parent\": \"2549\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": \"2549\",\n" +
                "\t\t\t\"text\": \"父亲二分\",\n" +
                "\t\t\t\"parent\": \"\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": \"2550\",\n" +
                "\t\t\t\"text\": \"全五分外国人\",\n" +
                "\t\t\t\"parent\": \"2551\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": \"2551\",\n" +
                "\t\t\t\"text\": \"案说法我如果乳房\",\n" +
                "\t\t\t\"parent\": \"2553\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": \"2552\",\n" +
                "\t\t\t\"text\": \"安抚人生观\",\n" +
                "\t\t\t\"parent\": \"\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": \"2553\",\n" +
                "\t\t\t\"text\": \"按时发生感染\",\n" +
                "\t\t\t\"parent\": \"\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": \"2554\",\n" +
                "\t\t\t\"text\": \"阿三发射点给v\",\n" +
                "\t\t\t\"parent\": \"2555\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": \"2555\",\n" +
                "\t\t\t\"text\": \"胜多负少v\",\n" +
                "\t\t\t\"parent\": \"\"\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";
    }

//    植物生长地数据
    @RequestMapping(value = "/planetGrowing", method = RequestMethod.POST)
    public List getGrow(@RequestParam("planet")String planet, Model model){
        return Planet.getGrowing(planet);
    }
}
