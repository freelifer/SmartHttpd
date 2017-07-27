package freelifer.smarthttpd.inner.managerimpl;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import freelifer.smarthttpd.inner.log.Logger;
import freelifer.smarthttpd.inner.manager.ConfigManager;
import freelifer.smarthttpd.inner.util.IOUtils;

/**
 * @author kzhu on 2017/7/27.
 */
public class ConfigManagerImpl implements ConfigManager {
    static final Logger log = Logger.getLogger(true, ConfigManager.class);

    private String configData;
    private InputStream inputStream;

    @Override
    public void setConfigInputStream(InputStream is) {
        try {
            configData = IOUtils.getStringFromInputStream(is);
        } catch (IOException e) {
            log.e("ServerManager set Config failure!");
        } finally {
            IOUtils.close(is);
        }
    }

    public void parse() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            ServerParse handler = new ServerParse();
            parser.parse(inputStream, handler);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final class ServerParse extends DefaultHandler {

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
        }
    }
}
