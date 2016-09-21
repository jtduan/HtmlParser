package cn.jtduan;

import cn.jtduan.jsoup.ArticleParser;
import cn.jtduan.jsoup.ListParser;
import org.htmlparser.util.ParserException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by jtduan on 2016/9/21.
 */
public class JsoupTest {

//    public String articleUrl;

//    public static void main(String[] args) throws IOException {
////        Document doc = Jsoup.parse(html);
//        Document doc = Jsoup.connect("http://www.sporttery.cn/football/jcqz/2016/0920/215120.html").get();
//        Elements ListDiv = doc.getElementsByAttributeValue("class","positon");
////        System.out.println(ListDiv.text());
//        for (Element element :ListDiv) {
//            Elements links = element.getElementsByTag("a");
//            for (Element link : links) {
////                String linkHref = link.attr("href");
//                String linkText = link.text().trim();
////                System.out.println(linkHref);
//                System.out.println(linkText);
//            }
//        }
//    }

    @Test
    public void testJczq() throws IOException, ParserException {
        ListParser parser = new ListParser(new URL(Constant.JCZQ));
        List<URL> list = parser.parse();
        Assert.assertNotNull(list);
        System.out.println(list.size());
        ArticleParser ap= new ArticleParser(list.get(0));
        String content =ap.parse();
        System.out.println(content);
        Assert.assertNotNull(content);
    }


    @Test
    public void testJclq() throws IOException, ParserException {
        ListParser parser = new ListParser(new URL(Constant.JCLQ));
        List<URL> list = parser.parse();
        Assert.assertNotNull(list);
        System.out.println(list.size());
        ArticleParser ap= new ArticleParser(list.get(0));
        String content =ap.parse();
        System.out.println(content);
        Assert.assertNotNull(content);
    }

    @Test
    public void testDlt() throws IOException, ParserException {
        ListParser parser = new ListParser(new URL(Constant.DLT));
        List<URL> list = parser.parse();
        Assert.assertNotNull(list);
        System.out.println(list.size());
        ArticleParser ap= new ArticleParser(list.get(0));
        String content =ap.parse();
        System.out.println(content);
        Assert.assertNotNull(content);
    }

    @Test
    public void testZcfc() throws IOException, ParserException {
        ListParser parser = new ListParser(new URL(Constant.SCFC));
        List<URL> list = parser.parse();
        Assert.assertNotNull(list);
        System.out.println(list.size());
        ArticleParser ap= new ArticleParser(list.get(0));
        String content =ap.parse();
        System.out.println(content);
        Assert.assertNotNull(content);
    }
}
