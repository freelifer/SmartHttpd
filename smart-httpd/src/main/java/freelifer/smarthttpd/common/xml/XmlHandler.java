package freelifer.smarthttpd.common.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kzhu on 2017/7/28.
 */
public class XmlHandler extends DefaultHandler {
    public static final String VERSION_1_0 = "1.0";

    private boolean firstExec = false;
    private Map<String, XMLListener> xmlListenerMap = new HashMap<>();
    private String currentVersion = VERSION_1_0;
    private XMLListener currentXMLListener;

    public void addXMLListener(String version, XMLListener listener) {
        xmlListenerMap.put(version, listener);
    }

    public <T> T getTarget() {
        if (currentXMLListener != null) {
            return (T) currentXMLListener.getResult();
        }
        return null;
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (!firstExec) {
            firstExec = true;
            String version = attributes.getValue("version");
            currentVersion = version;
            currentXMLListener = xmlListenerMap.get(currentVersion);
        }
        if (currentXMLListener != null) {
            currentXMLListener.startElement(currentVersion, uri, localName, qName, attributes);
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (currentXMLListener != null) {
            currentXMLListener.endElement(currentVersion, uri, localName, qName);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentXMLListener != null) {
            currentXMLListener.characters(currentVersion, ch, start, length);
        }
    }
}
