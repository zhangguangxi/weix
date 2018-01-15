package com.weix.commons;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.naming.NameCoder;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.Xpp3DomDriver;

import java.io.InputStream;
import java.io.Writer;

public abstract class XStreamUtils {
    public static  void toObject(String xml,Object bean){
        init(bean).fromXML(xml,bean);
    }

    public static  void toObject(InputStream input,Object bean){
        init(bean).fromXML(input,bean);
    }

    public static String toXml(Object bean){
        return init(bean).toXML(bean);
    }

    private static XStream init(Object bean){
        XStream xStream = new XStream(new Xpp3DomDriver() {
            @Override
            public HierarchicalStreamWriter createWriter(Writer out) {
                return new PrettyPrintWriter(out,getNameCoder()){
                    String CDATA_PREFIX = "<![CDATA[";
                    String CDATA_SUFFIX = "]]>";
                    @Override
                    protected void writeText(QuickWriter writer, String text) {
                       //数字不需要使用CDATA
                       if(text.matches("[0-9]+")){
                           writer.write(text);
                       }

                       //已经使用了CDATA
                        if(text.startsWith(CDATA_PREFIX)&& text.endsWith(CDATA_SUFFIX)){
                           writer.write(text);
                            return;
                        }

                        //其他的
                        writer.write(CDATA_PREFIX+text+CDATA_SUFFIX);
                    }
                };
            }

            /**
             * 避免 _ -> -
             * @return
             */
            @Override
            protected NameCoder getNameCoder() {
                return super.getNameCoder();
            }
        });

        xStream.autodetectAnnotations(true);
        Class<?> aClass = bean.getClass();
        XStreamAlias alias = aClass.getAnnotation(XStreamAlias.class);
        if(null !=alias){
            xStream.alias(alias.value(),aClass);
        }else{
            Class<?> superclass = aClass.getSuperclass();
            while(Object.class != superclass){
                alias = superclass.getAnnotation(XStreamAlias.class);
                if(null != alias){
                    xStream.alias(alias.value(),aClass);
                    break;
                }
                superclass=superclass.getSuperclass();
            }
        }
        //忽略未知的元素
        xStream.ignoreUnknownElements();
        return xStream;
    }

}
