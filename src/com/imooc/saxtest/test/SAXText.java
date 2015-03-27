package com.imooc.saxtest.test;

import com.imooc.entity.Book;
import com.imooc.saxtest.handler.SAXParserHandler;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: QiuShiLe
 * Date: 2015/3/26.
 * Time: 14:20
 * Project: XMLAnalyse
 */
public class SAXText {
    public static void main(String[] args) {

        String demoFileName = "demo" + java.io.File.separator + "books.xml";

        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {
            //通过factory获取实例
            SAXParser parser = factory.newSAXParser();
            SAXParserHandler handler = new SAXParserHandler();
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


    }
}
