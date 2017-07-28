package freelifer.smarthttpd.common.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author kzhu on 2017/7/28.
 */
public interface XMLListener<T> {

    void startElement(String version, String uri, String localName, String qName, Attributes attributes) throws SAXException;

    void endElement(String version, String uri, String localName, String qName) throws SAXException;

    void characters(String version, char[] ch, int start, int length) throws SAXException;

    T getResult();
}
