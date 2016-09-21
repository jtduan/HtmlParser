package cn.jtduan;

import cn.jtduan.htmlparser.ArticleParser;
import cn.jtduan.htmlparser.HtmlParser;
import org.htmlparser.util.ParserException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * The type Html parser test.
 */
public class HtmlParserTest {

    /**
     * z这是个javaDOc
     *
     * @throws IOException     the io exception
     * @throws ParserException the parser exception
     * @author jtduan
     */
    @Test
    public void testJczq() throws IOException, ParserException {
        HtmlParser parser = new HtmlParser(new URL("http://info.sporttery.cn/news/match_select.php?sech_k_w=&match_id=0"));
        List<URL> list = parser.parse();
        Assert.assertNotNull(list);
        System.out.println(list.size());
        ArticleParser ap= new ArticleParser(list.get(0));

        String type =ap.parseType();
        String content =ap.parse();
        System.out.println(type);
        Assert.assertNotNull(type);
        System.out.println(content);
        Assert.assertEquals("",content.trim());
    }

}
