package com.imooc.saxtest.test;

import com.imooc.entity.Book;
import com.imooc.saxtest.handler.SAXParserHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: QiuShiLe
 * Date: 2015/3/26.
 * Time: 14:20
 * Project: XMLAnalyse
 */
public class SAXText {

    /**
     * 解析XML
     */
    public ArrayList<Book> parseXML(){
        String demoFileName = "demo" + java.io.File.separator + "books.xml";

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParserHandler handler = null;

        try {
            //通过factory获取实例
            SAXParser parser = factory.newSAXParser();
            handler = new SAXParserHandler();
            parser.parse(demoFileName, handler);
            System.out.println("Book个数为" + handler.getBookList().size());
            for (Book book : handler.getBookList()) {
                System.out.println(book.toString());
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return handler.getBookList();
    }

    public void createXML(){
        ArrayList<Book> bookList = parseXML();
        SAXTransformerFactory tff = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
        try {
            TransformerHandler handler = tff.newTransformerHandler();
            Transformer tr = handler.getTransformer();
            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); //设置编码
            tr.setOutputProperty(OutputKeys.INDENT, "yes");     //是否换行
            File outFile = new File("demo" + java.io.File.separator + "books3.xml");
            if (!outFile.exists()) {
                outFile.createNewFile();
            }
            Result result = new StreamResult(new FileOutputStream(outFile));
            handler.setResult(result);

            handler.startDocument();

            AttributesImpl attr = new AttributesImpl();
            handler.startElement("", "", "bookstore", attr);
            for (Book book : bookList) {
                attr.clear();
                attr.addAttribute("", "", "id", "", book.getId());
                handler.startElement("", "", "book", attr);

                /**
                 * 得到name节点
                 * 太麻烦的
                 * 可以用反射在Book.java加函数getValue优化
                 * public String getValue(String name)
                 */
                if(null!=book.getName() && !"".equals(book.getName().trim())){
                    attr.clear();
                    handler.startElement("", "", "name", attr);
                    handler.characters(book.getName().toCharArray(), 0, book.getName().length());
                    handler.endElement("", "", "name");
                }


                handler.endElement("", "", "book");

            }

            handler.endElement("", "", "bookstore");


            handler.endDocument();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SAXText test = new SAXText();
        test.createXML();
    }
}
