package bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Jython {
    public void RunPython(){
//        Process p=null;
//
//        try {
//            p=Runtime.getRuntime().exec("/usr/python3/Python-3.5.3/python /usr/local/tomcat/apache-tomcat-8.5.40/webapps/flora/WEB-INF/classes/bean/AnalyseInput.py");
//            p.waitFor();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e2){
//            e2.printStackTrace();
//        }
//        if(p!=null)
//            p.destroy();
//        System.out.println("program over");

        try
        {
            String cmd="/usr/python3/Python-3.5.3/python /usr/local/tomcat/apache-tomcat-8.5.40/webapps/flora/WEB-INF/classes/bean/AnalyseInput.py";
            final Process process = Runtime.getRuntime().exec(cmd);
            System.out.println("start run cmd=" + cmd);

            //process InputStream thread
            new Thread()
            {
                @Override
                public void run()
                {
                    BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String line = null;

                    try
                    {
                        while((line = in.readLine()) != null)
                        {
                            System.out.println("output: " + line);
                        }
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    finally
                    {
                        try
                        {
                            in.close();
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();

            new Thread()
            {
                @Override
                public void run()
                {
                    BufferedReader err = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                    String line = null;

                    try
                    {
                        while((line = err.readLine()) != null)
                        {
                            System.out.println("err: " + line);
                        }
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    finally
                    {
                        try
                        {
                            err.close();
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();

            process.waitFor();
            process.destroy();
            System.out.println("finish run cmd=" + cmd);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }


}
