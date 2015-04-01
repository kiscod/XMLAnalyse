package com.imooc.dom4jtest;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: QiuShiLe
 * Date: 2015/3/31
 * Time: 16:05
 * Project: XMLAnalyse
 */
public class DOM4JTest {

    public static void main(String[] args) {



        SAXReader reader = new SAXReader();//通过dom4j的sax加载xml
        try {
            Document document = reader.read(new File("demo" + File.separator + "books.xml"));
            Element bookStore = document.getRootElement();//获取根节点
            Iterator it = bookStore.elementIterator();
            while (it.hasNext()) {

                Element book = (Element) it.next();
                List<Attribute> bookAttrs = book.attributes();
                for (Attribute attr : bookAttrs) {
                    System.out.println("节点 " + attr.getName() + " 的值为 " + attr.getValue());
                }
                Iterator itt = book.elementIterator();
                while (itt.hasNext()) {
                    Element bookChild = (Element) itt.next();
                    System.out.println("节点 " + bookChild.getName() + " 的值为 " + bookChild.getStringValue());
                }

            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
