package com.imooc.domtest.test;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: QiuShiLe
 * Date: 2015/3/25.
 * Time: 21:56
 * Project: XMLAnalyse
 * 我们年轻的时候，总是把创作的冲动误以为是创作的才华; 总是把对孤独的恐惧误以为是对爱情的向往。
 */
public class DOMTest {


    public DocumentBuilder getDocumentBuilder(){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;  //创建一个DxBx对象
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return db;
    }

    /**
     * 解析XML
     */
    public void xmlParser() {

        String demoFileName = "demo" + java.io.File.separator + "books.xml";

        try {
            //创建DocumentBuilder对象
            //通过DocumentBuilder对象的parse方法加载books.xml文件
            Document document = getDocumentBuilder().parse(demoFileName);
            //获取所有book节点的集合
            NodeList bookList = document.getElementsByTagName("book");
            //通过bookList的getLength方法可以获取bookList的长度
            System.out.println("一共有" + bookList.getLength() + "本书");
            //遍历每一个book节点
            for (int i = 0; i< bookList.getLength(); i++) {
                System.out.println("=====下面开始遍历" + (i+1) + "本书的内容=====");

                //通过item方法获取一个节点，索引从0开始
                Node book = bookList.item(i);
                //获取node所有属性集合
                NamedNodeMap attrs = book.getAttributes();
                System.out.println("第" + (i+1) + "本书共有" + attrs.getLength() + "个属性");

                //遍历book的属性
                for (int j = 0; j < attrs.getLength(); j++) {
                    //通过item方法获取节点的某个属性
                    Node attr = attrs.item(j);
                    String attrName     = attr.getNodeName();
                    String attrValue    = attr.getNodeValue();
                    System.out.println(attrName + "属性的值为： " + attrValue);
                }

//                //前提：已经知道book节点为一个且知道是什么
//                Element book = (Element) bookList.item(i);
//                String attrVale = book.getAttribute("id");
//                System.out.println("id属性的属性值为" + attrVale);

                //解析book节点的子节点
                NodeList childNodes = book.getChildNodes();
                System.out.println("第" + (i+1) + "本书共有" + childNodes.getLength() + "个子节点");
                for (int k = 0; k < childNodes.getLength(); k++) {
                    //区分text类型和element类型的Node
                    if(childNodes.item(k).getNodeType() == Node.ELEMENT_NODE) {
                        Node node = childNodes.item(k);
                        String nodeName  = node.getNodeName();
                        String nodeValue = node.getTextContent();
                        System.out.println("子节点" + nodeName + "的值为" + nodeValue);
                    }
                }

                System.out.println("=======结束遍历" + (i+1) + "本书的内容=======");
            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成XML
     */
    public void createXML(){
        DocumentBuilder db = getDocumentBuilder();
        Document document = db.newDocument();
        document.setXmlStandalone(true);
        Element bookStore = document.createElement("bookStore");

        Element book = document.createElement("book");
        book.setAttribute("id", "1");
        
        Element name = document.createElement("name");
        name.setTextContent("小王子");
        Element author = document.createElement("author");
        author.setTextContent("Cliton");
        Element year = document.createElement("year");
        year.setTextContent("1999");

        book.appendChild(name);
        book.appendChild(author);
        book.appendChild(year);



        bookStore.appendChild(book);

        document.appendChild(bookStore);

        TransformerFactory tf = TransformerFactory.newInstance();
        try {
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(document), new StreamResult(
                    new File("demo" + java.io.File.separator + "books1.xml")));
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DOMTest test = new DOMTest();
//        test.xmlParser();
        test.createXML();
    }
}
