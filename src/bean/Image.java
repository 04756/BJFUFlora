package bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Image {

    private String imgLink;

    public Image(String imgLink) {
        this.imgLink = imgLink;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public List<String> getPlanetImgLinks(String planet) throws IOException {
        SyncPipe out = new SyncPipe(null, null);
        String result = new InvokePythonProject().invokePython("D:/test.py","test", out);
        List list = new ArrayList();
        for(String i : result.split("\r\n"))
            if(i.contains("http"))
                list.add(i);
        return list;
    }

    public static void main(String [] args)throws IOException{
        System.out.println("Image");
       for( String i : new Image("").getPlanetImgLinks(""))
           System.out.println(i);
    }

}
