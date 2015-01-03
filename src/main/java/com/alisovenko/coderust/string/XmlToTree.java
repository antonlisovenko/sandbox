package com.alisovenko.coderust.string;

import com.alisovenko.base.Node;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.LinkedList;

/**
 * @author alisovenko 13.12.14
 */
public class XmlToTree {
    private static Node xml2Tree(XMLStreamReader reader) throws XMLStreamException {
//        LinkedList<CtCILibrary.TreeNode> stack = new LinkedList<>();

        /*while (reader.hasNext()) {
            if (
                    reader.getEventType() == XMLStreamConstants.START_DOCUMENT ||
                    reader.getEventType() == XMLStreamConstants.END_DOCUMENT ||
                    reader.getEventType() == XMLStreamConstants.SPACE)
            {
            }
            else if (reader.getEventType() == XMLStreamConstants.END_ELEMENT) {
                if (stack.size() > 0) {
                    stack.pop();
                }
            }
            else if (reader.getEventType() == XMLStreamConstants.START_ELEMENT) {
                if (stack.size() > 0) {
                    stack.peek().
                }
            }
        }*/
        return null;
    }
}
