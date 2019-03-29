package bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Image {

    public Image() {
    }

    public List<String> getPlanetImgLinks(String planet) throws IOException {
        SyncPipe out = new SyncPipe(null, null);
        String result = new InvokePythonProject().invokePython("D:/test.py","test", out);
        List list = new ArrayList();
        for(String i : result.split("\r\n"))
            if(i.contains("http"))
                list.add(i.replaceAll("\'","").replaceFirst("b", ""));
        return list;
    }

}
