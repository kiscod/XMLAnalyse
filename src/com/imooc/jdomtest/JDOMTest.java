package com.imooc.jdomtest;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: QiuShiLe
 * Date: 2015/3/26
 * Time: 19:37
 * Project: XMLAnalyse
 */
public class JDOMTest {
    public static void main(String[] args) {

        //创建一个SAXBuilder对象
        SAXBuilder saxBuilder = new SAXBuilder();
        //创建一个输入流 以加载xml
        InputStream in;
        try {
            in = new FileInputStream("demo" + java.io.File.separator + "books.xml");
            Document document = saxBuilder.build(in);
            //获取根节点
            Element rootElement = document.getRootElement();
            //获取子节点的集合
            List<Element> bookList = rootElement.getChildren();

            for (Element book : bookList) {
                System.out.println("===开始解析第" + (bookList.indexOf(book) + 1) + "本书===");

                //解析book的属性
                //book.getAttributeValue("id"); //知道名称时获取节点值
                List<Attribute> attrList = book.getAttributes();
                for (Attribute attr : attrList) {
                    System.out.println(attr.getName());
                    System.out.println(attr.getValue());
                }

                List<Element> bookChilds = book.getChildren();
                for (Element child : bookChilds) {
                    System.out.println("节点" + child.getName() + "的值为" + child.getValue());
                }

                System.out.println("===结束解析第" + (bookList.indexOf(book) + 1) + "本书===");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
