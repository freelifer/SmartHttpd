package freelifer.smarthttpd.inner.managerimpl;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import freelifer.smarthttpd.common.Converter;
import freelifer.smarthttpd.common.xml.XMLListener;
import freelifer.smarthttpd.common.xml.XmlHandler;
import freelifer.smarthttpd.inner.log.Logger;
import freelifer.smarthttpd.inner.manager.ConfigManager;
import freelifer.smarthttpd.inner.model.ApplicationInfo;
import freelifer.smarthttpd.inner.model.Server;
import freelifer.smarthttpd.inner.model.ServletInfo;
import freelifer.smarthttpd.inner.util.TextUtils;

/**
 * @author kzhu on 2017/7/27.
 */
public class ConfigManagerImpl implements ConfigManager {
    private static final Logger log = Logger.getLogger(false, ConfigManager.class);

    private Server serverInfo;
    private Converter<String, InputStream> converter;
    private String filePath;

    @Override
    public void setConfigFile(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void setConverter(Converter<String, InputStream> converter) {
        this.converter = converter;
    }

    @Override
    public void parse() {
        if (TextUtils.isEmpty(filePath)) {
            log.e("Config parse file path is empty.");
            defaultConfig();
            return;
        }

        if (this.converter == null) {
            log.e("Config parse Converter is null.");
            defaultConfig();
            return;
        }

        InputStream is = this.converter.cover(filePath);
        if (is == null) {
            log.e("Config parse InputStream is null.");
            defaultConfig();
            return;
        }

        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            XmlHandler xmlHandler = initXmlHandler();
            parser.parse(is, xmlHandler);
            serverInfo = xmlHandler.getTarget();
            log.i("XML result-->\n%s", serverInfo);
            return;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        defaultConfig();
    }

    @Override
    public Server getServerInfo() {
        return serverInfo;
    }

    private void defaultConfig() {

    }

    private XmlHandler initXmlHandler() {
        XmlHandler xmlHandler = new XmlHandler();
        xmlHandler.addXMLListener(XmlHandler.VERSION_1_0, new ServerXMLParse());
        return xmlHandler;
    }

    private final class ServerXMLParse implements XMLListener<Server> {

        //标记当前节点
        private String currentTag;

        private Server server;
        private ServletInfo servletInfo;

        @Override
        public void startElement(String version, String uri, String localName, String qName, Attributes attributes) throws SAXException {
            log.i("ServerParse startElement %s %s %s", uri, localName, qName);
            if ("server".equals(qName)) {
                server = Server.create();
                server.setName(attributes.getValue("name"));
                currentTag = "server";
            } else if ("connector".equals(qName)) {
                server.setPort(attributes.getValue("port"));
                server.setMinProcessors(attributes.getValue("minProcessors"));
                server.setMaxProcessors(attributes.getValue("maxProcessors"));
                server.setEnableLookups(attributes.getValue("enableLookups"));
                server.setRedirectPort(attributes.getValue("redirectPort"));
                server.setAcceptCount(attributes.getValue("acceptCount"));
                server.setConnectionTimeout(attributes.getValue("connectionTimeout"));
                currentTag = "connector";
            } else if ("application".equals(qName)) {
                ApplicationInfo applicationInfo = ApplicationInfo.create();
                applicationInfo.setName(attributes.getValue("name"));
                server.setApplicationInfo(applicationInfo);
                currentTag = "application";
            } else if ("servlet".equals(qName)) {
                servletInfo = ServletInfo.create();
                currentTag = "servlet";
            } else if ("url".equals(qName)) {
                currentTag = "url";
            } else if ("servlet-class".equals(qName)) {
                currentTag = "servlet-class";
            }
        }

        @Override
        public void endElement(String version, String uri, String localName, String qName) throws SAXException {
            if ("servlet".equals(qName)) {
                List<ServletInfo> list = server.getApplicationInfo().getServletInfos();
                if (list == null) {
                    list = new ArrayList<>();
                    server.getApplicationInfo().setServletInfos(list);
                }
                list.add(servletInfo);
            }
        }

        @Override
        public void characters(String version, char[] ch, int start, int length) throws SAXException {
            if ("url".equals(currentTag)) {
                servletInfo.setUrl(new String(ch, start, length));
            } else if ("servlet-class".equals(currentTag)) {
                servletInfo.setServletClass(new String(ch, start, length));
            }
            currentTag = null;
        }

        @Override
        public Server getResult() {
            return server;
        }
    }

}
