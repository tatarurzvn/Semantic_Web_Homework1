/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.jboss.weld.util.collections.ArraySet;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author rtataru
 */
public class HRDAO {

    private static HRDAO instance;

    public static HRDAO getInstance() {
        if (instance == null) {
            instance = new HRDAO();
        }
        return instance;
    }

    private HRDAO() {
    }

    public boolean exists(String fileName) {
        File f = new File(fileName);
        return f.exists();
    }

    public List<String> getInfo(String id) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        List<String> ret = new ArrayList<>();
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document xmlDocument = builder.parse(new File("patients.xml"));

        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "//patient[@id=" + "'" + id + "'" + "]";
        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            System.out.println("Current Element: " + nNode.getNodeName() + "\n");
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elm = (Element) nNode;
                String name = elm.getElementsByTagName("name").item(0).getTextContent();
                ret.add(name);
                Element visits = (Element) elm.getElementsByTagName("visits").item(0);
                NodeList visit = visits.getElementsByTagName("visit");
                for (int j = 0; j < visit.getLength(); j++) {
                    ret.add("_________________________");
                    ret.add("VISIT:\n");
                    Node nNode1 = visit.item(j);
                    if (nNode1.getNodeType() == Node.ELEMENT_NODE) {
                        Element elm1 = (Element) nNode1;
                        String diag = "Diagnostic: " + elm1.getElementsByTagName("diagnostic").item(0).getTextContent();
                        String medication = "Medication: " + elm1.getElementsByTagName("medication").item(0).getTextContent();
                        String trPlan = "Treatment plan: " + elm1.getElementsByTagName("treatment_plan").item(0).getTextContent();
                        String date = "Visit date: " + elm1.getElementsByTagName("visitDate").item(0).getTextContent();
                        ret.add(diag);
                        ret.add(medication);
                        ret.add(trPlan);
                        ret.add(date);
                    }
                }
                Element allergies = (Element) elm.getElementsByTagName("allergies").item(0);
                if (allergies.hasChildNodes()) {
                    NodeList allergy = allergies.getElementsByTagName("allergy");
                    ret.add("_________________________");
                    ret.add("ALLERGIES:\n");
                    for (int k = 0; k < allergy.getLength(); k++) {
                        Node tmp = allergy.item(k);
                        ret.add(tmp.getTextContent());
                    }
                }
                Element immuns = (Element) elm.getElementsByTagName("immunizations").item(0);
                if (allergies.hasChildNodes()) {
                    NodeList immun = immuns.getElementsByTagName("immunization");
                    ret.add("_________________________");
                    ret.add("IMMUNIZATIONS:\n");
                    for (int k = 0; k < immun.getLength(); k++) {
                        Node tmp = immun.item(k);
                        if (tmp.hasChildNodes()) {
                            ret.add("Name: " + tmp.getChildNodes().item(0).getTextContent());
                            ret.add("Date: " + tmp.getChildNodes().item(1).getTextContent());
                        }
                    }
                }
            }
        }
        return ret;
    }

    public void delAllergy(String delAllergy, String id) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, TransformerConfigurationException, TransformerException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document xmlDocument = builder.parse(new File("patients.xml"));

        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "/patients/patient[@id='" + id + "']/allergies/allergy[text()='" + delAllergy + "']";
        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
        Node cureForDis = nodeList.item(0);
        System.out.println(cureForDis.getTextContent());
        cureForDis.getParentNode().removeChild(cureForDis);

        Transformer tf = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(xmlDocument);
        StreamResult res = new StreamResult(new File("patients.xml"));
        tf.transform(source, res);
    }

    public void changeImu(String chDate, String imName, String id) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, TransformerException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document xmlDocument = builder.parse(new File("patients.xml"));
        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "/patients/patient[@id='" + id + "']/immunizations/immunization/imunName[text()='" + imName + "']/../imunDate";
        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
        Node imDate = nodeList.item(0);
        imDate.setTextContent(chDate);

        Transformer tf = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(xmlDocument);
        StreamResult res = new StreamResult(new File("patients.xml"));
        tf.transform(source, res);
    }

    public List<String> getImunsInfo() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        List<String> ret = new ArrayList<>();

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document xmlDocument = builder.parse(new File("patients.xml"));
        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "/patients/patient/immunizations/immunization/imunName";
        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
        Set<String> names = new ArraySet<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (!(names.contains(nodeList.item(i).getTextContent()))) {
                names.add(nodeList.item(i).getTextContent());
            }
        }
        names.forEach((elm) -> ret.add(elm));
        return ret;
    }

    public List<String> getImunStats(String date) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        List<String> ret = new ArrayList<>();

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document xmlDocument = builder.parse(new File("patients.xml"));
        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "/patients/patient/immunizations/immunization/imunDate[text()='" + date + "']/../imunName";
        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++)
        {
            ret.add(nodeList.item(i).getTextContent());
        }
        return ret;
    }

    public List<String> getAlergyStats() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        List<String> ret = new ArrayList<>();

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document xmlDocument = builder.parse(new File("patients.xml"));
        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "/patients/patient/allergies/allergy";
        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);

        Set<String> names = new ArraySet<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (!(names.contains(nodeList.item(i).getTextContent()))) {
                names.add(nodeList.item(i).getTextContent());
            }
        }

        for (String name : names) {
            String expression1 = "/patients/patient/allergies/allergy[text()='" + name + "']";
            NodeList nodeList1 = (NodeList) xPath.compile(expression1).evaluate(xmlDocument, XPathConstants.NODESET);
            ret.add(name + ": " + nodeList1.getLength());
        }
        return ret;
    }

    public List<String> getEncsBefore(String year) throws SAXException, ParserConfigurationException, IOException, XPathExpressionException {
        List<String> ret = new ArrayList<>();

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document xmlDocument = builder.parse(new File("patients.xml"));
        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "/patients/patient/visits/visit";
        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node n = nodeList.item(i);
            if (n.getLastChild().getTextContent().contains(year)) {
                NodeList encData = n.getChildNodes();
                ret.add("Diagnostic: " + encData.item(0).getTextContent());
                ret.add("Medication: " + encData.item(1).getTextContent());
                ret.add("Treatment Plan: " + encData.item(2).getTextContent());
                ret.add("Visit Date: " + encData.item(3).getTextContent());
                ret.add("_____________________________");
            }
        }

        return ret;
    }

    public void addImu(String imuDate, String imuName, String id) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, TransformerException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document xmlDocument = builder.parse(new File("patients.xml"));
        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "/patients/patient[@id='" + id + "']/immunizations";
        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
        Node immuns = nodeList.item(0);
        Element elm_immun = xmlDocument.createElement("immunization");

        Element elm_name = xmlDocument.createElement("imunName");
        elm_name.insertBefore(xmlDocument.createTextNode(imuName), elm_name.getLastChild());

        Element elm_date = xmlDocument.createElement("imunDate");
        elm_date.insertBefore(xmlDocument.createTextNode(imuDate), elm_date.getLastChild());

        elm_immun.appendChild(elm_name);
        elm_immun.appendChild(elm_date);
        immuns.appendChild(elm_immun);

        Transformer tf = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(xmlDocument);
        StreamResult res = new StreamResult(new File("patients.xml"));
        tf.transform(source, res);
    }

    public void addAllergy(String allergy, String id) throws ParserConfigurationException, SAXException, XPathExpressionException, IOException, TransformerConfigurationException, TransformerException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document xmlDocument = builder.parse(new File("patients.xml"));

        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "/patients/patient[@id='" + id + "']/allergies";
        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
        Node allergies = nodeList.item(0);
        Element elm_allergy = xmlDocument.createElement("allergy");
        elm_allergy.insertBefore(xmlDocument.createTextNode(allergy), elm_allergy.getLastChild());
        allergies.appendChild(elm_allergy);

        Transformer tf = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(xmlDocument);
        StreamResult res = new StreamResult(new File("patients.xml"));
        tf.transform(source, res);
    }

    public void addMedicalVisit(String diagnostic, String medication, String treatPlan, String date, String id) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, TransformerException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document xmlDocument = builder.parse(new File("patients.xml"));

        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "/patients/patient[@id='" + id + "']/visits";
        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
        Node visits = nodeList.item(0);

        Element elm_docVisit = xmlDocument.createElement("visit");
        Element elm_diagnostic = xmlDocument.createElement("diagnostic");
        Element elm_medication = xmlDocument.createElement("medication");
        Element elm_treatPlan = xmlDocument.createElement("treatment_plan");
        Element elm_date = xmlDocument.createElement("visitDate");

        elm_diagnostic.insertBefore(xmlDocument.createTextNode(diagnostic), elm_diagnostic.getLastChild());
        elm_medication.insertBefore(xmlDocument.createTextNode(medication), elm_medication.getLastChild());
        elm_treatPlan.insertBefore(xmlDocument.createTextNode(treatPlan), elm_treatPlan.getLastChild());
        elm_date.insertBefore(xmlDocument.createTextNode(date), elm_date.getLastChild());

        elm_docVisit.appendChild(elm_diagnostic);
        elm_docVisit.appendChild(elm_medication);
        elm_docVisit.appendChild(elm_treatPlan);
        elm_docVisit.appendChild(elm_date);

        visits.appendChild(elm_docVisit);

        Transformer tf = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(xmlDocument);
        StreamResult res = new StreamResult(new File("patients.xml"));
        tf.transform(source, res);
    }

    public void addHR(String name, String id, String diagnostic, String medication, String treatment_plan, String date) throws ParserConfigurationException, TransformerException, TransformerConfigurationException, IOException, SAXException {
        System.out.println(name);
        System.out.println(id);
        System.out.println(diagnostic);
        System.out.println(medication);
        System.out.println(treatment_plan);

        String xmlPath = "patients.xml";

        DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = df.newDocumentBuilder();
        Document doc = db.parse(xmlPath);
        Node data = doc.getFirstChild();
        Element element = doc.createElement("patient");
        element.setAttribute("id", id);
        data.appendChild(element);

        Element elm_name = doc.createElement("name");
        elm_name.insertBefore(doc.createTextNode(name), elm_name.getLastChild());
        element.appendChild(elm_name);

        Element elm_docVisits = doc.createElement("visits");
        Element elm_docVisit = doc.createElement("visit");
        Element elm_diagnostic = doc.createElement("diagnostic");
        Element elm_medication = doc.createElement("medication");
        Element elm_treatPlan = doc.createElement("treatment_plan");
        Element elm_date = doc.createElement("visitDate");

        elm_diagnostic.insertBefore(doc.createTextNode(diagnostic), elm_diagnostic.getLastChild());
        elm_medication.insertBefore(doc.createTextNode(medication), elm_medication.getLastChild());
        elm_treatPlan.insertBefore(doc.createTextNode(treatment_plan), elm_treatPlan.getLastChild());
        elm_date.insertBefore(doc.createTextNode(date), elm_date.getLastChild());

        elm_docVisit.appendChild(elm_diagnostic);
        elm_docVisit.appendChild(elm_medication);
        elm_docVisit.appendChild(elm_treatPlan);
        elm_docVisit.appendChild(elm_date);
        elm_docVisits.appendChild(elm_docVisit);
        element.appendChild(elm_docVisits);

        Element elm_allergies = doc.createElement("allergies");
        Element elm_imuns = doc.createElement("immunizations");
        element.appendChild(elm_allergies);
        element.appendChild(elm_imuns);

        Transformer tf = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult res = new StreamResult(new File(xmlPath));
        tf.transform(source, res);
        System.out.println("I wrote the file");

    }

}
