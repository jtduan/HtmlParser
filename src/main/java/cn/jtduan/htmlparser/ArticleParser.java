package cn.jtduan.htmlparser;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.filters.HasParentFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jtduan on 2016/9/20.
 */
public class ArticleParser {
    Parser parser;

    public ArticleParser(String html) throws ParserException {
//        Parser parser = new Parser(readFile("index.html"));
        this.parser = new Parser(html);
        parser.setEncoding("GBK");
    }

    public ArticleParser(URL url) throws ParserException {
        this.parser = new Parser(url.toString());
        parser.setEncoding("GBK");
    }

    public  CssSelectorNodeFilter cssFilter = new CssSelectorNodeFilter("div[class~='page_3_con']");
    public  HasParentFilter hasParentFilter = new HasParentFilter(cssFilter);

    public  TagNameFilter pFilter = new TagNameFilter("p");

    public  CssSelectorNodeFilter titleFilter = new CssSelectorNodeFilter("div[class~='page_3_title']");

    public  OrFilter articleFilter = new OrFilter(titleFilter,pFilter);


    public  String parse() throws IOException, ParserException {
        StringBuilder sb = new StringBuilder();
        /**
         * Parser仅仅能够使用一次
         */

//        NodeFilter filter = new CssSelectorNodeFilter("div[class~='positon']");
//        NodeList list = parser.extractAllNodesThatMatch(filter);
//        NodeList typies = list.elementAt(0).getChildren().extractAllNodesThatMatch(new TagNameFilter("a"));
//        for(Node n:typies.toNodeArray()){
//            sb.append(n.getChildren().elementAt(0).getText()).append(">");
//        }
        NodeList nodeList = parser.extractAllNodesThatMatch(hasParentFilter);
        nodeList.keepAllNodesThatMatch(articleFilter);
        for(Node node:nodeList.toNodeArray()){
            if(node.toHtml().matches("(?is)^.*?<table.*</table>.*$")){
                continue;
            }
            else if(node instanceof Div){
                sb.append(node.toPlainTextString()).append("\n");
                continue;
            }
            sb.append(node.toPlainTextString()).append("\n");
//            System.out.println(node.toHtml().replaceAll("(?is)<a.*?>(.*?)</a>","$1"));
        }
        return sb.toString();
    }


    public  String parseType() throws IOException, ParserException {
        StringBuilder sb = new StringBuilder();
        /**
         * 注意：Parser仅仅能够使用一次
         */
        NodeFilter filter = new CssSelectorNodeFilter("div[class~='positon']");
        NodeList list = parser.extractAllNodesThatMatch(filter);
        NodeList typies = list.elementAt(0).getChildren().extractAllNodesThatMatch(new TagNameFilter("a"));
        for(Node n:typies.toNodeArray()){
            sb.append(n.getChildren().elementAt(0).getText()).append(">");
        }
        return sb.toString();
    }
}
