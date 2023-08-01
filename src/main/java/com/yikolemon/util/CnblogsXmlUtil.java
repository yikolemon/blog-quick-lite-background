package com.yikolemon.util;


import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.tree.DefaultText;

import java.util.*;

/**
 * @author yikolemon
 * @date 2023/8/1 22:14
 * @description
 */
public class CnblogsXmlUtil {


    /**
     * 通过返回的xml获取member列表
     * @param document
     * @return
     */
    public static List<Element> getTargetElementByName(Document document){
        Element rootElement = document.getRootElement();
        // 通过element对象的elementIterator方法获取迭代器
        Queue<Element> elements=new LinkedList<>();
        elements.add(rootElement);
        //在没找到之前一直层序遍历
        Element targetElement=null;
        ArrayList<Element> res = new ArrayList<>();
        while (!elements.isEmpty()){
            Element poll = elements.poll();
            Iterator<Element> iterator = poll.elementIterator();
            while (iterator.hasNext()){
                Element next = iterator.next();
                if ("member".equals(next.getName())){
                    res.add(next);
                }else {
                    elements.add(next);
                }
            }
        }
        return res;
    }

    /**
     * 通过List<Element> member列表，获取其中的name-value键值对，以Map<String,String>形式返回
     */
    public static Map<String, String> getMemberKV(List<Element> list){
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            Element tempElement = list.get(i);
            Element nameElement = tempElement.element("name");
            Element valueElement = tempElement.element("value");
            String nameValue = nameElement.getText();
            //在value解析时，会把\n解析成一个defaultText对象，所以需要去除这些对象
            String valueValue = getSingleValueElementText(valueElement);
            map.put(nameValue,valueValue);
        }
        return map;
    }

    /**
     * 通过dom4j的document获取其中所有需要的kv对
     * @param document
     * @return
     */
    public static Map<String,String> getKVByDocument(Document document){
        List<Element> list = getTargetElementByName(document);
        Map<String, String> memberKV = getMemberKV(list);
        return memberKV;
    }

    /**因为单个Value的Element还包含一层类型的标签，所以需要特殊处理，取出内容
     * @param valueElement
     * @return Value的String内容
     */
    public static String getSingleValueElementText(Element valueElement){
        Iterator<Element> iterator = valueElement.elementIterator();
        while (iterator.hasNext()) {
            Element next = iterator.next();
            if (next instanceof DefaultText) {
                continue;
            } else {
                //获取到了value的element
                valueElement = next;
                break;
            }
        }
        return valueElement.getText();
    }

    //暂时不写
    public static List<String> getListValueElementText(Element valueElement){
        return null;
    }

}
