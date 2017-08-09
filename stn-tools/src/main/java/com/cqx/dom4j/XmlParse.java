package com.cqx.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by Shan on 2017/2/23.
 */
public class XmlParse {



    /**
     * 不用xpath
     * @throws DocumentException
     * @throws IOException
     */
    @Test
    public void test1() throws DocumentException, IOException {

        URL url = XmlParse.class.getResource("/demo.xml");

        // 获取document对象
        SAXReader reader = new SAXReader();
        File file = new File(url.getPath());
        Document document = reader.read(file);

        // 1.调用getRootElement()获取文档的根节点.
        Element rootElement = document.getRootElement();

        // 调用element()第一本书
        /*Element接口表示 HTML或 XML 文档中的一个元素。元素可能有与它们相关的属性；
        Element接口继承自 Node，方法更丰富，返回一个集合*/
        Element firstbook = rootElement.element("书");

        // 2、 elements()获取此元素节点的所有子节点
        List<Element> elements = firstbook.elements();
        //遍历集合中的元素子节点，打印所有元素子节点
        for (Element element2 : elements)
        {
            String text = element2.getText();
            System.out.println(element2.getName() + text);
        }

        // 取得某节点下所有名为“作者”的子节点，并打印出来 .
        List elements2 = firstbook.elements("作者");
        for (Object object : elements2)
        {//遍历子节点获取作者标签内的  文本内容
            Element element2 = (Element) object;
            System.out.println(element2.getText());
        }

        // 3、elements() element()修改某个元素节点的主体内容 修改第二本书的作者 为 李开复
        Element secondBook = (Element) rootElement.elements("书").get(1);
        Element secondBookAuthor = secondBook.element("作者");
        secondBookAuthor.setText("Kaifu Lee");

        System.out.println(secondBookAuthor.getText());

        // 4、addElement()向指定元素节点中增加子元素节点 第一本书增加ISBN
        Element addElement = firstbook.addElement("ISBN");
        addElement.setText("987465498748");

        // 5、向指定元素节点上增加同级元素节点
        // 增加一本书 找到父节点 书架
        Element addElement2 = rootElement.addElement("书");
        //增加书名节点
        Element bookname = addElement2.addElement("书名");
        bookname.setText("解忧杂货铺");

        Element bookauthor = addElement2.addElement("作者");
        bookauthor.setText("东野圭吾");

        Element bookprice = addElement2.addElement("售价");
        bookprice.setText("2RMB");
        //增加售价的属性
        bookprice.addAttribute("where", "amazon");

        // 6、删除指定元素节点
        Element element = firstbook.element("个人信息");
        firstbook.remove(element);

        // 把内存中的dom树 写回到一个xml文件
        OutputFormat format = OutputFormat.createPrettyPrint();
        // 指定XML编码 输出到output.xml
        format.setEncoding("GBK");
        XMLWriter writer;
        writer = new XMLWriter(new FileOutputStream("output.xml"), format);
        writer.write(document);
        writer.close();
    }


    /**
     * 解析代码并打印输出(使用xpath)
     * @throws DocumentException
     * @throws IOException
     * @throws IOException
     */
    public void test2() throws DocumentException, IOException, IOException
    {
        //获取Document对象
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("book.xml"));

        // 1.获取文档的根节点.
        // 第一本书 /书架/书[1]
        List selectNodes = document.selectNodes("/书架/书[1]");
        Element object2 = (Element) selectNodes.get(0);

        // 2、打印某节点的所有元素子节点
        List firstbookchildren = document.selectNodes("/书架/书[1]/*");
        for (Object object : firstbookchildren)
        {
            Element element = (Element) object;
            System.out.println(element.getName() + element.getText());

        }
        // 取得某节点下所有名为“作者”的子节点，并打印出来
        List selectNodes2 = document.selectNodes("//作者");
        for (Object object : selectNodes2)
        {
            Element element2 = (Element) object;
            System.out.println(element2.getText());
        }
        // 3、修改某个元素节点的主体内容 修改第二本书的作者 为 李开复
        Element selectNodes3 = (Element) document
                .selectSingleNode("/书架/书[2]/作者");
        selectNodes3.setText("Owen");

        // 4、向指定元素节点上增加同级元素节点
        // 增加一本书 找到父节点 书架

        Element addElement2 = document.getRootElement().addElement("书");

        Element bookname = addElement2.addElement("书名");
        bookname.setText("解忧杂货铺");

        Element bookauthor = addElement2.addElement("作者");
        bookauthor.setText("东野圭吾");

        Element bookprice = addElement2.addElement("售价");
        bookprice.setText("2RMB");

        bookprice.addAttribute("where", "amazon");

        // 把内存中的dom树 写回到一个xml文件
        OutputFormat format = OutputFormat.createPrettyPrint();

        //// 指定XML编码 写出到指定文件
        format.setEncoding("GBK");
        XMLWriter writer;
        writer = new XMLWriter(new FileOutputStream("output.xml"), format);
        writer.write(document);
        writer.close();
    }

}
