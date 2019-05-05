package db;


import org.neo4j.driver.v1.*;


public class Neo4jDB {

    private static Driver driver;

    public static void connectGraphDB(){
        String uri = "bolt://localhost:7687";
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
                //导入所有生物学分类的界门纲目科属种
                String cypher="load csv with headers from \"file:///界门纲目科属种//"+i+".0千组.csv\" as line merge(:Tradition{name:line.father,engname:line.fatherEnglish})";
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
                String cypher="load csv with headers from \"file:///植物与分类关系//叶子//"+i+"千组.csv\" as line match(father:Leave{name:line.DivName}),(son:Plant{name:line.PlantName}) merge(father)<-[r:hasLeave{reference:line.reference}]-(son)";
                session.run(cypher);
                System.out.println(i);
            }


        }
        Neo4jDB.close();
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Neo4jDB.LoadPlantRelationship();
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间

    }


}
