package cn.jtduan.htmlparser;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.filters.HasParentFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jtduan on 2016/9/20.
 */
public class HtmlParser {
    Parser parser;

    public HtmlParser(String html) throws ParserException {
//        Parser parser = new Parser(readFile("index.html"));
        this.parser = new Parser(html);
        parser.setEncoding("GBK");
    }

    public HtmlParser(URL url) throws ParserException {
        this.parser = new Parser(url.toString());
        parser.setEncoding("GBK");
    }

    public  NodeFilter regexFilter = new NodeFilter() {
        @Override
        public boolean accept(Node node) {
            if (node.getPreviousSibling() == null || node.getPreviousSibling().getFirstChild() == null) return false;
            if (node.getPreviousSibling().getFirstChild().getText().matches("^\\[.*\\]$"))
                return true;
            return false;
        }
    };

    public  HasParentFilter hasParentFilter = new HasParentFilter(new CssSelectorNodeFilter("div[class~='ResultBox']"), true);

    public  TagNameFilter aFilter = new TagNameFilter("a");

    public  AndFilter allFilter = new AndFilter(new AndFilter(hasParentFilter, regexFilter), aFilter);


    public List<URL> parse() throws ParserException, IOException {
        List<URL> l = new ArrayList<URL>();
        NodeList nodeList = parser.extractAllNodesThatMatch(allFilter);
        for (Node node : nodeList.toNodeArray()) {
            LinkTag tag = (LinkTag) node;
            l.add(new URL(tag.getLink()));
        }
        return l;
    }
}
