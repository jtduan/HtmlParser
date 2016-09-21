package cn.jtduan.jsoup;

import cn.jtduan.Util;
import org.htmlparser.util.ParserException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

/**
 * Created by jtduan on 2016/9/20.
 */
public class ArticleParser {

    private Document doc;
    public ArticleParser(URL url) throws IOException {
        this.doc = Jsoup.connect(url.toString()).get();
    }

    public ArticleParser(String fileName) throws IOException {
        this.doc =  Jsoup.parse(Util.readFile(fileName));
    }

    public String parse() throws ParserException, IOException {
        Elements article = doc.select("body").select(".wrap").select(".page_3_left").select(".page_3_con");
        String title = article.select(".page_3_title").text();
        StringBuilder sb = new StringBuilder();
        sb.append(title).append("\n");
        for(Element e:article.select("p")){
            sb.append(e.text()).append("\n");
        }
        return sb.toString();
    }
}
