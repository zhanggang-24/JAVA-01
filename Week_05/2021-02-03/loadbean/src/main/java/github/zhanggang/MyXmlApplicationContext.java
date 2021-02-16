package github.zhanggang;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyXmlApplicationContext {
    private final static Map<String, Object> beansMap = new HashMap<>();

    public MyXmlApplicationContext(String xmlPath) {
        init(xmlPath);
    }

    private void init(String xmlPath) {
        InputStream stream = MyXmlApplicationContext.class.getClassLoader().getResourceAsStream(xmlPath);

        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(stream);
            Element rootElement = document.getRootElement();
            List<Element> beanElements = rootElement.elements("bean");
            //根据bean注入对象
            for (Element element : beanElements) {
                if (element.getName().equals("bean")) {
                    String beanId = element.attributeValue("id");
                    if (beanId != null && !beanId.equals("")) {
                        String classPath = element.attributeValue("class");
                        Class<?> aClass = Class.forName(classPath);
                        Object instance = aClass.newInstance();
                        beansMap.put(beanId, instance);
                    }
                }
            }
            //根据bean下的property注入对象
            for (Element element : beanElements) {
                List<Element> propertyElement = element.elements("property");
                String beanId = element.attributeValue("id");
                if (propertyElement != null && propertyElement.size() > 0) {
                    for (Element property : propertyElement) {
                        String name = property.attributeValue("name");
                        String ref = property.attributeValue("ref");

                        Object o = beansMap.get(beanId);
                        Method[] methods = o.getClass().getMethods();
                        for (Method method : methods) {
                            if (method.getName().equalsIgnoreCase("set" + name)) {
                                method.invoke(o, beansMap.get(ref));
                            }
                        }
                    }
                }
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    
    public Object getBean(String beanId) {
        return beansMap.get(beanId);
    }
}
