package bean;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 调用python程序通过百度搜索api获取相关植物的有关图片
 */
public class Image {

    public Image() {
    }

    public List<String> getPlanetImgLinks(String planet, HttpServletRequest request) throws IOException {

        SyncPipe out = new SyncPipe(null, null);
        String pyPath = this.getClass().getResource("").getPath()+"/test.py";
        pyPath = pyPath.replaceFirst("/","");
        String result = new InvokePythonProject().invokePython(pyPath,planet, out);



        List list = new ArrayList();
        for(String i : result.split("\r\n"))
            if(i.contains("http"))
                list.add(i.replaceAll("\'","").replaceFirst("b", ""));
        return list;
    }

}
