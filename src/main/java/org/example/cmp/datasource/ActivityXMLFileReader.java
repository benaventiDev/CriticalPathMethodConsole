package org.example.cmp.datasource;


import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.example.cmp.activity.Activity;
import org.example.cmp.activity.ActivityListManager;
import org.example.cmp.exceptions.DuplicatedActivityException;
import org.example.cmp.exceptions.DuplicatedActivityPathException;
import org.example.cmp.exceptions.MissingActivityException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ActivityXMLFileReader extends ActivityFileReader {



    public ActivityXMLFileReader(File file) {
        super(file);
    }

    @Override
    protected ActivityListManager read() throws DuplicatedActivityException, MissingActivityException, DuplicatedActivityPathException {
        ActivityListManager listActivityManager = new ActivityListManager();
        try {
            // create a document builder factory
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
            Document document = builder.parse(super.getFile());

            // get the root element of the document
            Element root = document.getDocumentElement();

            // get all activity elements
            NodeList activityNodes = root.getElementsByTagName("activity");
            for (int i = 0; i < activityNodes.getLength(); i++) {
                Node node = activityNodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element activityElement = (Element) node;
                    String name = activityElement.getAttribute("Name");
                    String description = activityElement.getAttribute("Description");
                    String previousActivities = activityElement.getAttribute("Previous Activities");
                    int regular_duration = Integer.parseInt(activityElement.getAttribute("Time"));
                    Double regularCost = 0.0d;
                    Integer minimumTime = 0;
                    Double extraCost = 0.0d;

                    setOptionalValues(activityElement, regularCost, minimumTime, extraCost);

                    // create an activity object and add it to the list
                    Activity activity = super.getActivityFactory().createActivity(name, description, regular_duration, regularCost, minimumTime, extraCost);
                    ActivityData activityData = new ActivityData(activity, previousActivities);
                    listActivityManager.addActivityData(activityData);

                }
            }
            listActivityManager.setAllPreviousActivities();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return listActivityManager;
    }

    private void setOptionalValues(Element activityElement, Double regularCost, Integer minimumTime, Double extraCost){
        // check if the regular cost and extra cost attributes exist
        if (activityElement.hasAttribute("Regular Cost")) {
            regularCost = Double.parseDouble(activityElement.getAttribute("Regular Cost"));
        }
        if (activityElement.hasAttribute("Minimum Time")) {
            minimumTime = Integer.parseInt(activityElement.getAttribute("Minimum Time"));
        }
        if (activityElement.hasAttribute("Extra Cost")) {
            extraCost = Double.parseDouble(activityElement.getAttribute("Maximum Cost"));
        }
    }



}

