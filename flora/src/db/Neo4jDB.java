package db;


import org.neo4j.driver.v1.*;


public class Neo4jDB {

    private static Driver driver;

    public static void connectGraphDB(){
        String uri = "bolt://39.96.176.150:7687";
        //String uri = "bolt://localhost:7687";
        driver = GraphDatabase.driver(uri, AuthTokens.basic("neo4j", "1234"));

    }

    public static void close(){
        driver.close();
    }

    public Driver getDriver(){
        return driver;
    }

    public static void LoadPlant(){
        Neo4jDB.connectGraphDB();
        Neo4jDB db=new Neo4jDB();
        try (Session session = db.getDriver().session()) {
            for (int i=1;i<=35;i++)
            {
                if(i==1)
                {
                    session.run("create index on :Plant(name)");
                    System.out.println("索引创建成功");
                }
                //调用是 将根茎 部分 改为 叶子 果 花 执行，导入所有植物，运行较慢
               // String cypher="load csv with headers from \"file:///植物与分类关系//叶子//"+i+"千组.csv\" as line merge(:Plant{name:line.PlantName,indexname:line.indexName})";
                //String cypher="load csv with headers from \"file:///植物与分类关系//果//"+i+"千组.csv\" as line merge(:Plant{name:line.PlantName,indexname:line.indexName})";
               // String cypher="load csv with headers from \"file:///植物与分类关系//花//"+i+"千组.csv\" as line merge(:Plant{name:line.PlantName,indexname:line.indexName})";
                String cypher="load csv with headers from \"file:///植物与分类关系//根茎//"+i+"千组.csv\" as line merge(:Plant{name:line.PlantName,indexname:line.indexName})";
                session.run(cypher);

                System.out.println(i);
            }


        }
        Neo4jDB.close();
    }

    public static void LoadTradition(){
        Neo4jDB.connectGraphDB();
        Neo4jDB db=new Neo4jDB();
        try (Session session = db.getDriver().session()) {
            for (int i=1;i<=35;i++)
            {
                if(i==1)
                {
                    session.run("create index on :Tradition(name)");
                    System.out.println("索引创建成功");
                }
                System.out.println(i);
                //导入所有生物学分类的界门纲目科属种
                String cypher="load csv with headers from \"file:///界门纲目科属种//"+i+".0千组.csv\" as line merge(:Tradition{name:line.father})";
                session.run(cypher);

            }
            for(int i=1;i<=35;i++){
                String cypher="load csv with headers from \"file:///界门纲目科属种//"+i+".0千组.csv\" as line  match (n:Tradition{name:line.father}) set n.engname=line.fatherEnglish";
                session.run(cypher);
                System.out.println(i);
            }


        }
        Neo4jDB.close();
    }

    public static void LoadTraditionRelationship(){
        Neo4jDB.connectGraphDB();
        Neo4jDB db=new Neo4jDB();
        try (Session session = db.getDriver().session()) {
            for (int i=1;i<=35;i++)
            {

                //导入所有生物学分类的界门纲目科属种关系
                String cypher="load csv with headers from \"file:///界门纲目科属种//"+i+".0千组.csv\" as line match(father:Tradition{name:line.father}),(son:Tradition{name:line.son}) merge (father)<-[r:isSubClass]-(son)";
                session.run(cypher);

                System.out.println(i);
            }


        }
        Neo4jDB.close();

    }

    public static void LoadPlantRelationship(){
        Neo4jDB.connectGraphDB();
        Neo4jDB db=new Neo4jDB();
        try (Session session = db.getDriver().session()) {
            for (int i=1;i<=35;i++)
            {
                //导入所有植物属性关系
              //  String cypher="load csv with headers from \"file:///植物与分类关系//叶子//"+i+"千组.csv\" as line match(father:Leave{name:line.DivName}),(son:Plant{name:line.PlantName}) merge(father)<-[r:hasLeave{reference:line.reference}]-(son)";
                //String cypher="load csv with headers from \"file:///植物与分类关系//花//"+i+"千组.csv\" as line match(father:Flower{name:line.DivName}),(son:Plant{name:line.PlantName}) merge(father)<-[r:hasFlower{reference:line.reference}]-(son)";
              //String cypher="load csv with headers from \"file:///植物与分类关系//果//"+i+"千组.csv\" as line match(father:Guo{name:line.DivName}),(son:Plant{name:line.PlantName}) merge(father)<-[r:hasGuo{reference:line.reference}]-(son)";
               String cypher="load csv with headers from \"file:///植物与分类关系//根茎//"+i+"千组.csv\" as line match(father:Rhizome{name:line.DivName}),(son:Plant{name:line.PlantName}) merge(father)<-[r:hasRhizome{reference:line.reference}]-(son)";
                session.run(cypher);
                System.out.println(i);
            }


        }
        Neo4jDB.close();
    }

    public static void LoadPlantTraditionalType(){
        Neo4jDB.connectGraphDB();
        Neo4jDB db=new Neo4jDB();
        try (Session session = db.getDriver().session()) {
            for (int i=1;i<=35;i++)
            {
                if(i==1)
                {
                    session.run("create index on :Plant(indexname)");
                    System.out.println("索引创建成功");
                }
                //导入所有植物的生物学分类的界门纲目科属种关系
                String cypher="load csv with headers from \"file:///界门纲目科属种//"+i+".0千组.csv\" as line match(father:Tradition{name:line.father}),(son:Plant{indexname:line.son}) merge (father)<-[r:traditionalType]-(son)";
                session.run(cypher);
                System.out.println(i);
            }


        }
        Neo4jDB.close();
    }

    public static void LoadLocation(){
        Neo4jDB.connectGraphDB();
        Neo4jDB db=new Neo4jDB();
        try (Session session = db.getDriver().session()) {
            for (int i=1;i<=35;i++)
            {
                if(i==1)
                {
                    session.run("create index on :Province(name)");
                    System.out.println("索引创建成功");
                }
                //导入所有省份
                String cypher="load csv with headers from \"file:///植物分布//"+i+".0千组.csv\" as line merge(:Province{name:line.Location})";
                session.run(cypher);
                System.out.println(i);
            }


        }
        Neo4jDB.close();

    }

    public static void LoadLocationRelationship(){
        Neo4jDB.connectGraphDB();
        Neo4jDB db=new Neo4jDB();
        try (Session session = db.getDriver().session()) {
            for (int i=1;i<=35;i++)
            {
                //导入所有植物生长地关系
                String cypher="load csv with headers from \"file:///植物分布//"+i+".0千组.csv\" as line match(son:Plant{name:line.name}),(father:Province{name:line.Location}) merge(son)-[r:located]->(father)";
                session.run(cypher);
                System.out.println(i);
            }


        }
        Neo4jDB.close();

    }

    public static void LoadFlowerColor(){
        Neo4jDB.connectGraphDB();
        Neo4jDB db=new Neo4jDB();
        try (Session session = db.getDriver().session()) {
            for (int i=1;i<=35;i++)
            {
                //导入所有花的相关颜色
                String cypher="load csv with headers from \"file:///花颜色//"+i+".0千组.csv\" as line match(n:Plant{name:line.Plantname}),(m:Flower{name:line.Divname}) merge (n)-[r:hasFlower{reference:line.reference}]->(m)";
                session.run(cypher);
                System.out.println(i);
            }


        }
        Neo4jDB.close();

    }

    public static void LoadDescribe(){
        Neo4jDB.connectGraphDB();
        Neo4jDB db=new Neo4jDB();
        try (Session session = db.getDriver().session()) {
            for (int i=1;i<=35;i++)
            {
                //导入所有植物描述
                String cypher="load csv with headers from \"file:///植物描述//"+i+".0千组.csv\" as line match(n:Plant{name:line.Plantname}) set n.describe=line.Describe";
                session.run(cypher);
                System.out.println(i);
            }


        }
        Neo4jDB.close();
    }

    public static void LoadPlantEngname(){
        Neo4jDB.connectGraphDB();
        Neo4jDB db=new Neo4jDB();
        try (Session session = db.getDriver().session()) {
            for (int i=1;i<=35;i++)
            {
                //导入所有植物英文名
                String cypher="load csv with headers from \"file:///界门纲目科属种//"+i+".0千组.csv\" as line match(n:Plant{indexname:line.son}) set n.engname=line.sonEnglish";
                session.run(cypher);
                System.out.println(i);
            }


        }
        Neo4jDB.close();
    }

    public static void ChangePlantName(){
        Neo4jDB.connectGraphDB();
        Neo4jDB db=new Neo4jDB();
        try (Session session = db.getDriver().session()) {
            for (int i=1;i<=35;i++)
            {
                //优化植物名字
                String cypher="load csv with headers from \"file:///植物名字优化//"+i+".0千组.csv\" as line match(n:Plant{name:line.Plantname}) set n.name=line.FixName";
                session.run(cypher);
                System.out.println(i);
            }


        }
        Neo4jDB.close();
    }


    public static void BaikeProvince(){
        Neo4jDB.connectGraphDB();
        Neo4jDB db=new Neo4jDB();
        try (Session session = db.getDriver().session()) {
            for (int i=1;i<=32;i++)
            {
                //添加百科生长地
                String cypher="load csv with headers from \"file:///百度百科数据//"+i+".0千组.csv\" as line match(father:Plant{indexname:line.植物名}),(son:Province{name:line.节点}) merge(father)-[r:located]->(son)";
                session.run(cypher);
                System.out.println(i);
            }


        }
        Neo4jDB.close();
    }

    public static void BaikeProperty(){
        Neo4jDB.connectGraphDB();
        Neo4jDB db=new Neo4jDB();
        try (Session session = db.getDriver().session()) {
            for (int i=1;i<=32;i++)
            {
                //添加百科生长环境
                String cypher="load csv with headers from \"file:///百度百科数据//"+i+".0千组.csv\" as line match(father:Plant{indexname:line.植物名}) where line.节点 = \"生长环境\" set father.growingenvironment=line.内容";
                //添加主要价值
                //String cypher="load csv with headers from \"file:///百度百科数据//"+i+".0千组.csv\" as line match(father:Plant{indexname:line.植物名}) where line.节点 = \"主要价值\" set father.mainvalue=line.内容";
                //添加栽培技术
                //String cypher="load csv with headers from \"file:///百度百科数据//"+i+".0千组.csv\" as line match(father:Plant{indexname:line.植物名}) where line.节点 = \"栽培技术\" set father.cultivationtechnique=line.内容";
                //添加繁殖方法
                //String cypher="load csv with headers from \"file:///百度百科数据//"+i+".0千组.csv\" as line match(father:Plant{indexname:line.植物名}) where line.节点 = \"繁殖方法\" set father.breedingmethod=line.内容";
                //添加医药用途
                //String cypher="load csv with headers from \"file:///百度百科数据//"+i+".0千组.csv\" as line match(father:Plant{indexname:line.植物名}) where line.节点 = \"医药用途\" set father.medicaluse=line.内容";
                //添加生长习性
                //String cypher="load csv with headers from \"file:///百度百科数据//"+i+".0千组.csv\" as line match(father:Plant{indexname:line.植物名}) where line.节点 = \"生长习性\" set father.growthhabit=line.内容";


                session.run(cypher);
                System.out.println(i);
            }


        }
        Neo4jDB.close();
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Neo4jDB.BaikeProperty();
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间

    }


}
