package bean;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Callable;

public class SyncPipe implements Callable<String> {

    private final OutputStream ostrm_;
    private final InputStream istrm_;
    private StringBuffer result = new StringBuffer("");

    public SyncPipe(InputStream istrm, OutputStream ostrm) {
        istrm_ = istrm;
        ostrm_ = ostrm;
    }

    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        try {
            final byte[] buffer = new byte[1024];
            for (int length = 0; (length = istrm_.read(buffer)) != -1;) {
                ostrm_.write(buffer, 0, length);
                result.append(new String(buffer, 0, length));
            }
        } catch (Exception e) {
            throw new RuntimeException("处理命令出现错误：" + e.getMessage());
        }
        //pool-1-thread-1 return "线程返回值是:"+this.value;
        return result.toString();
    }

    public void run() {
        try {
            final byte[] buffer = new byte[1024];
            for (int length = 0; (length = istrm_.read(buffer)) != -1;) {
                ostrm_.write(buffer, 0, length);
                result.append(new String(buffer, 0, length));
            }
        } catch (Exception e) {
            throw new RuntimeException("处理命令出现错误：" + e.getMessage());
        }
//        notifyAll();
    }

    public StringBuffer getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = new StringBuffer(result);
    }
}
