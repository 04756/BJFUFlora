package bean;

import java.io.IOException;

public class Jython {
    public void RunPython(){
        Process p=null;

        try {
            p=Runtime.getRuntime().exec("/usr/python3/Python-3.5.3/python /usr/local/tomcat/apache-tomcat-8.5.40/webapps/flora/WEB-INF/classes/bean/AnalyseInput.py");
            p.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e2){
            e2.printStackTrace();
        }
        if(p!=null)
            p.destroy();
        System.out.println("program over");
    }
}
