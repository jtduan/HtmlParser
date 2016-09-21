package cn.jtduan.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static cn.jtduan.Util.readFile;

/**
 * Created by jtduan on 2016/9/20.
 */
public class ListParser {
    /**
     * The Doc.
     */
    Document doc;

    /**
     * Instantiates a new List parser.
     *
     * @param url the url
     * @throws IOException the io exception
     */
    public ListParser(URL url) throws IOException {
        this.doc = Jsoup.connect(url.toString()).get();
    }

    /**
     * Instantiates a new List parser.
     *
     * @param fileName the file name
     * @throws IOException the io exception
     */
    public ListParser(String fileName) throws IOException {
        this.doc =  Jsoup.parse(readFile(fileName));
    }

    /**
     * Parse list.
     *
     * @return list
     * @throws MalformedURLException the malformed url exception
     */
    public List<URL> parse() throws MalformedURLException {
        Elements listUl = doc.select("body").select(".wrap").select(".list_ul");
        List<URL> list= new ArrayList<>();
        for(Element e:listUl){
            for(Element a:e.getElementsByTag("a")){
                list.add(new URL(a.attr("href")));
            }
        }
        return list;
    }
}
