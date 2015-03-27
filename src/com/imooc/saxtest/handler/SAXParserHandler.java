package com.imooc.saxtest.handler;

import com.imooc.entity.Book;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: QiuShiLe
 * Date: 2015/3/26.
 * Time: 14:24
 * Project: XMLAnalyse
 * 用来遍历xml文件的开始标签，结束标签, 解析开始， 解析结束
 */
public class SAXParserHandler extends DefaultHandler{

    String value = "";
    Book book;
    private ArrayList<Book> bookList = new ArrayList<Book>();

    public ArrayList<Book> getBookList() {
        return bookList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);  // 父类滴
        if(qName.equals("book")){   //解析book元素的属性
            System.out.println("=====下面开始遍历本书的内容=====");

//            String value = attributes.getValue("id");
//            System.out.println(book.id:" + value);

            book = new Book();
            for (int i = 0,num = attributes.getLength(); i < num; i++) {
                System.out.println("book元素的第" + (i+1) + "个属性是："
                    + attributes.getQName(i) + "  值为：" + attributes.getValue(i));
                if(attributes.getQName(i).equals("id")){
                    book.setId(attributes.getValue(i));
                }
            }
        }else if(!qName.equals("bookstore")){
            System.out.print("节点名为" + qName);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if(qName.equals("book")){
            bookList.add(book);
            System.out.println("=======结束遍历本书的内容=======");
        }else if(qName.equals("name")){
            book.setName(value);
        }else if(qName.equals("author")){
            book.setAuthor(value);
        }else if(qName.equals("year")){
            book.setYear(value);
        }else if(qName.equals("price")){
            book.setPrice(value);
        }else if(qName.equals("language")){
            book.setLanguage(value);
        }
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        System.out.println("SAX解析开始");
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("SAX解析结束");
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        value = new String(ch, start, length);
        if(!value.trim().equals("")){
            System.out.println("    值为" + value);
        }
    }
}
