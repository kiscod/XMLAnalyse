package com.imooc.entity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: QiuShiLe
 * Date: 2015/3/26
 * Time: 16:44
 * Project: XMLAnalyse
 */

public class Book {
    private String id;
    private String name;
    private String author;
    private String year;
    private String price;
    private String language;

    public Book(){
        setId("");
        setAuthor("");
        setAuthor("");
        setYear("");
        setPrice("");
        setLanguage("");
    }
    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ( "".equals(name)        ? "" : ", name='"       + name     + '\'') +
                ( "".equals(author)      ? "" : ", author='"     + author   + '\'') +
                ( "".equals(year)        ? "" : ", year='"       + year     + '\'') +
                ( "".equals(price)       ? "" : ", price='"      + price    + '\'') +
                ( "".equals(language)    ? "" : ", language='"   + language + '\'') +
                '}';
    }

    public void setValue(String name, String value) {
        if("value".equals(name.toLowerCase())){
            return;
        }
        Class tempBook = this.getClass();
        Method[] ms = tempBook.getMethods();
        String tempName;
        for (Method method : ms) {
            tempName = method.getName();
            if(("set" + name).toLowerCase().equals(tempName.toLowerCase())){
                Method m = null;
                try {
                    m = tempBook.getMethod(tempName, String.class);
                    final Object invoke = m.invoke(this, value);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
