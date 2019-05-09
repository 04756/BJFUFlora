package bean;

import org.python.core.*;
import org.python.util.PythonInterpreter;

import java.io.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 通过cmd程序执行python文件并获取结果
 * 要求cmd具有python环境
 */
public class InvokePythonProject extends Thread implements Callable<String> {

    SyncPipe t1;
    SyncPipe t2;

    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        //pool-1-thread-1 return "线程返回值是:"+this.value;
        return null;
    }


    public String invokePython(String command, String planet, SyncPipe out) throws IOException {
//        先启动cmd，再运行命令，查看报错。
//        该方法能够详细的查看到报错
//        直接模拟cmd执行命令只能从waitfor返回的数字猜测原因不能查看具体错误
        String[] args = new String[] { "/usr/python3/Python-3.5.3/python", command};
        Process p = null;//创建一个线程池对象
        ExecutorService pool = Executors.newCachedThreadPool(); //创建一个有返回值的任务
        String returnValue = "";//· 创建一个字符串存放返回结果

        try {
            p = Runtime.getRuntime().exec(args);
            out = new SyncPipe(p.getInputStream(), System.out);
            t1 = new SyncPipe(p.getErrorStream(), System.err);
            t2 = out;
            // 执行任务并获取Future对象
            Future<String> future1 = pool.submit(t1);
            Future<String> future2 = pool.submit(t2);
            //PrintWriter stdin = new PrintWriter(p.getOutputStream());
            /** 以下可以输入自己想输入的cmd命令 */
            //stdin.println("python "+command+" "+planet);//此处自行填写，切记有空格，跟cmd的执行语句一致。
            //stdin.close();
            //从 Future 对象 获取任务返回值
             while(true) {
                 //可以用isDone()方法来查询Future是否已经完成，任务完成后，可以调用get()方法来获取结果
                 // 注意： 如果不加判断直接调用get方法，此时如果线程未完成，get将阻塞，直至结果准备就绪
                  if(future2.isDone()) {
                      try {
                          returnValue = future2.get().toString();
                          System.out.println("线程返回值:"+returnValue);
                      }catch (Exception e){
                          e.printStackTrace();
                      }
                      //关闭线程池
                       pool.shutdown();
                      //跳出循环
                       break;
                  }
             }
             return returnValue;
        } catch (Exception e) {
            throw new RuntimeException("编译出现错误：" + e.getMessage());
        }
    }

}