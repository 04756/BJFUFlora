package bean;

import db.Neo4jDB;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;

import java.util.ArrayList;
import java.util.List;

public class Cypther {

    private static Neo4jDB db=new Neo4jDB();

    static{
        Neo4jDB.connectGraphDB();
    }

    //返回传统生物学分类的根节点
    public static List racetraditionalData(){
        long startTime = System.currentTimeMillis();
        List arr = new ArrayList();
//        返回一个TreeNode的数组，js需要小改动
        try (Session session = db.getDriver().session()) {
            StatementResult result = session.run("match(n:Tradition) where apoc.node.degree.out(n,\"isSubClass\")=0 return n.name");
            while (result.hasNext()) {
                Record record = result.next();
                String son = record.get("n.name").asString();
                TreeNode t=new TreeNode(son,son,son);
                arr.add(t);
                System.out.println(son);
            }
        }
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
        return arr;
    }

    public static List plantLocation(String plant){
        long startTime = System.currentTimeMillis();
        List arr = new ArrayList();
        try (Session session = db.getDriver().session()) {
            StatementResult result = session.run("match(n:Plant{name:\""+plant+"\"})-[r:located]->(m) return m.name");
            while (result.hasNext()) {
                Record record = result.next();
                String location = record.get("m.name").asString();
                arr.add(location);
                System.out.println(location);
            }
        }
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
        return arr;
    }

    //返回花叶果根茎
    public static List raceuntraditionalData(){
        long startTime = System.currentTimeMillis();
        List arr = new ArrayList();
//        返回一个TreeNode的数组，js需要小改动
        try (Session session = db.getDriver().session()) {
            StatementResult result = session.run("match(n:Untradition) where apoc.node.degree.out(n,\"isSubClass\")=0 return n.name");
            while (result.hasNext()) {
                Record record = result.next();
                String son = record.get("n.name").asString();
                TreeNode t=new TreeNode(son,son,son);
                arr.add(t);
            }
        }
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
        return arr;
    }

    //返回传入节点的下一层
    public static List unTrachildData(String nodename){

        long startTime = System.currentTimeMillis();
        List arr = new ArrayList();
//        返回一个TreeNode的数组，js需要小改动

        try (Session session = db.getDriver().session()) {
            StatementResult result = session.run("match(n)-[r:isSubClass]->(m{name:\""+nodename+"\"})return n.name");
            while (result.hasNext()) {
                Record record = result.next();
                String son = record.get("n.name").asString();
                TreeNode t=new TreeNode(son,son,nodename);
                arr.add(t);
                System.out.println(son+" "+nodename);
            }
        }
        String str="单复叶,叶基,叶尖,叶序,叶片形状,叶缘,叶脉序,叶裂,叶质地,托叶,毛,颜色,复果,聚合果,肉质果,裂果,闭果,子房着生,排列方式,胎座,胚珠,花冠形态,花序,花药,雄蕊,雌蕊,木本植物,茎,草本植物,藤本植物";

        if(arr.isEmpty()){
            if(str.contains(nodename)){
                System.out.println("是非传统叶子节点,返回形状");
                try (Session session = db.getDriver().session()) {
                    StatementResult result = session.run("match(:Plant)-[r]->(m:Untradition{name:\""+nodename+"\"}) return distinct r.reference");
                    while (result.hasNext()) {
                        Record record = result.next();
                        String son = record.get("r.reference").asString();
                        son+="的"+nodename;
                        son=son.replace("|","或");
                        TreeNode t=new TreeNode(son,son,nodename);
                        arr.add(t);
                        System.out.println(son+" "+nodename);
                    }
                }
            }
            else{
                System.out.println("是形状，返回植物");
                String[] keyword=nodename.split("的");
                String node=keyword[keyword.length-1];
                String reference=keyword[0];
                reference=reference.replace("或","|");
                try (Session session = db.getDriver().session()) {
                    StatementResult result = session.run("match(n:Plant)-[r]->(m:Untradition{name:\""+node+"\"}) where r.reference=\""+reference+"\" and apoc.node.degree.out(n,\"located\")>0 return distinct n.name");
                    while (result.hasNext()) {
                        Record record = result.next();
                        String son = record.get("n.name").asString();
                        TreeNode t=new TreeNode(son,son,nodename);
                        arr.add(t);
                        System.out.println(son+" "+nodename);
                    }
                }
            }

        }

        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
        return arr;
    }

    public static List TraChildData(String nodename){
        long startTime = System.currentTimeMillis();
        List arr = new ArrayList();
//        返回一个TreeNode的数组，js需要小改动

        try (Session session = db.getDriver().session()) {
            StatementResult result = session.run("match(n)-[r:isSubClass]->(m{name:\""+nodename+"\"})return n.name");
            while (result.hasNext()) {
                Record record = result.next();
                String son = record.get("n.name").asString();
                TreeNode t=new TreeNode(son,son,nodename);
                arr.add(t);
                System.out.println(son+" "+nodename);
            }
        }
        if(arr.isEmpty()){
            System.out.println("是传统叶子节点");
            try (Session session = db.getDriver().session()) {
                StatementResult result = session.run("match(n:Plant)-[r:traditionalType]->(m:Tradition{name:\""+nodename+"\"}) where apoc.node.degree.out(n,\"located\")>0 return n.name");
                while (result.hasNext()) {
                    Record record = result.next();
                    String son = record.get("n.name").asString();
                    TreeNode t=new TreeNode(son,son,nodename);
                    arr.add(t);
                    System.out.println(son+" "+nodename);
                }
            }
        }

        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
        return arr;
    }

    public static List graphData(String nodename){

        List lines = new ArrayList<Line>();
        List nodes = new ArrayList<Node>();
        List graphresult = new ArrayList();
//        返回一个TreeNode的数组，js需要小改动
        String node1="";
        try (Session session = db.getDriver().session()) {
            StatementResult result = session.run("match(n:Plant{name:\""+nodename+"\"})-[r]->(m) return n.indexname,r.reference,m.name");
            while (result.hasNext()) {
                Record record = result.next();
                 node1= record.get("n.indexname").asString();
                String node2=record.get("m.name").asString();
                String text=record.get("r.reference").asString();
                Line line=new Line();
                line.setSource(node1);
                line.setTarget(node2);
                line.setText(text);
                lines.add(line);
                Node node=new Node(node2);
                nodes.add(node);

            }
            Node plant=new Node(node1);
            nodes.add(plant);
            graphresult.add(nodes);
            graphresult.add(lines);
            return graphresult;
        }catch (Exception e){
            e.printStackTrace();
        }
        return graphresult;
    }

    public static List<SearchResult> commonsearch(String keyword){
        long startTime = System.currentTimeMillis();

        List<SearchResult> temp = new ArrayList<SearchResult>();
//        植物名字的数组
        try (Session session = db.getDriver().session()) {
            StatementResult result = session.run("match(n:Plant) where n.name contains \"" + keyword + "\" return n.name,n.pic1");
            while (result.hasNext()) {
                Record record = result.next();
                String plantname = record.get("n.name").asString();
                String imglink = record.get("n.pic1").asString();
                temp.add(new SearchResult(plantname, "planet/"+plantname,imglink));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
        return temp;
    }

    public static Planet getPlantDetail(String name){
        long startTime = System.currentTimeMillis();
        Planet planet=new Planet();
        planet.setcName(name);
        try (Session session = db.getDriver().session()) {
            StatementResult result = session.run("match(n:Plant{name:\""+name+"\"})-[r]->(m:Tradition) return m.name,n.engname,n.describe,n.pic1,n.pic2,n.pic3,n.pic4");
            if (result.hasNext()) {
                Record record = result.next();
                String race=record.get("m.name").asString();
                String describe=record.get("n.describe").asString();
                describe=describe.replace("\n","<br/>");
                String engname=record.get("n.engname").asString();
                for(int j=1;j<=4;j++){
                    String imglink=record.get("n.pic"+j).asString();
                    planet.getImglist().add(imglink);
                }
                planet.setContent(describe);
                planet.seteName(engname);

                planet.getRace().add(0,new SearchResult(race,race));

                StatementResult result2 = session.run("match(n:Tradition{name:\""+race+"\"})-[r:isSubClass]->(m:Tradition) return m.name");
                while(result2.hasNext())
                {
                    Record record2=result2.next();
                    race=record2.get("m.name").asString();
                    planet.getRace().add(0,new SearchResult(race,race));
                    result2 = session.run("match(n:Tradition{name:\""+race+"\"})-[r:isSubClass]->(m:Tradition) return m.name");
                }
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间

        return planet;
    }

    public static List graphSearch(String keyword){

        //在此处调用python 分析keyword

        List<SearchResult> temp = new ArrayList<SearchResult>();
        String result="红色的花\r\n" +
                "#红色#花 - 花\r\n" +
                "红色 - None\r\n" +
                "花 - None";
        String[] results=result.split("\r\n");
        try (Session session = db.getDriver().session()){
            if(result.contains("#")){
                int pos=0;
                for(int i=0;i<results.length;i++){
                    if(results[i].contains("#"))
                    {
                        pos=i;
                        String[] str=results[pos].split(" - ");
                        String[] str2=str[0].split("#");
                        String cypher;
                        if(str2[0].equals("")){
                            cypher="match(n:Plant)-[r:hasFlower]->(m:Flower) where r.reference contains \""+str2[1]+"\"";
                            for(int j=2;j<str2.length;j++){
                                cypher+=" and r.reference contains \""+str2[j]+"\"";
                            }
                        }
                        else{
                            cypher="match(n:Plant)-[r:hasFlower]->(m:Flower) where r.reference contains \""+str2[0]+"\"";
                            for(int j=1;j<str2.length;j++){
                                cypher+=" and r.reference contains \""+str2[j]+"\"";
                            }
                        }

                        cypher+=" return n.name,n.pic1";
                        StatementResult cypherresult=session.run(cypher);
                        if(cypherresult.hasNext()){
                            while(cypherresult.hasNext()){
                                Record record=cypherresult.next();
                                String plantname = record.get("n.name").asString();
                                String imglink = record.get("n.pic1").asString();
                                temp.add(new SearchResult(plantname, "planet/"+plantname,imglink));

                            }
                            return temp;
                        }else{

                        }

                    }
                }


            }else{

            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] arge){

        getPlantDetail("醉鱼草状六道木（中国高等植物图鉴）图版30:1-2");
    }
}
