/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfextract;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.imageio.ImageIO;

/**
 *
 * @author hany
 */
public class ControlPDF {

    public List<String> parsePdf(String pdfPath) throws IOException {
        PdfReader reader = new PdfReader(pdfPath);
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        //String [] arrayOftext= new String[300];
        List<String> arrayOftext = new ArrayList<String>();

        // PrintWriter out = new PrintWriter(new FileOutputStream(destnationPath));
        TextExtractionStrategy strategy;
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
            // out.println(i + " " + strategy.getResultantText());
            arrayOftext.add(strategy.getResultantText());
        }
        reader.close();

        return arrayOftext;
    }

    public String[] fileInfoSe(String arrayOftext) throws MalformedURLException, IOException, FileNotFoundException {
        String line;
        String[] stringArrayVar = {"lineOneData", "lineTwoData", "lineThreeData", "type",
            "documentNumber", "dateOfBirth", "dateOfExpiry", "issuer",
            "nationality", "lastName", "firstName", "discretionary1",
            "discretionary2", "gender"};
        String[] stringArrayNames = {"Line One Data ", "Line Two Data ", "Line Three Data ", "Type ",
            "Document Number ", "Date of Birth ", "Date of Expiry ", "Issuer ",
            "Nationality ", "Last Names ", "First Names ", "Discretionary 1 ",
            "Discretionary 2 ", "Gender "};
        try {

            BufferedReader br = new BufferedReader(new StringReader(arrayOftext));
            while ((line = br.readLine()) != null) {
                // Deal with the line
                for (int i = 0; i < stringArrayVar.length; i++) {

                    if (line.contains(stringArrayNames[i])) {
                        stringArrayVar[i] = line.replace(stringArrayNames[i], "");
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
        return stringArrayVar;
    }

    public void sendDataToServer(String yourUrl, String[] colNames, String[] postArray) throws MalformedURLException, IOException {
        URL url = new URL(yourUrl);
        URLConnection con = url.openConnection();
        // activate the output
        con.setDoOutput(true);
        PrintStream ps = new PrintStream(con.getOutputStream());
        // send your parameters to your site
        for (int i = 0; i < postArray.length; i++) {

            ps.print("&" + colNames[i] + "=" + postArray[i]);
        }

        // we have to get the input stream in order to actually send the request
        con.getInputStream();

        // close the print stream
        ps.close();
    }

    public void extractImages(String filename, String save) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(filename);
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        ImageRenderListener listener = new ImageRenderListener(save);

        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            parser.processContent(i, listener);
        }
        reader.close();
    }

    public boolean parsePdfToText(String pdfPath, File destnationPath) throws IOException, DocumentException, Exception {
        boolean success;
        String desPath = null;
        PdfReader reader = new PdfReader(pdfPath);
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        try {
            desPath = destnationPath.getName();

            new File(desPath.replace(".txt", "")).mkdir();
            PrintWriter out = new PrintWriter(new FileOutputStream(desPath.replace(".txt", "") + "/" + destnationPath.getName()));
            TextExtractionStrategy strategy;
            for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
                out.println(i + " " + strategy.getResultantText());
            }
            reader.close();
            out.flush();
            out.close();
            extractImages(pdfPath, desPath.replace(".txt", "") + "/" + desPath.replace(".txt", ""));
            success = true;
        } catch (Exception ex) {
            success = false;

        }

        return success;
    }

    public static String imageToBase64String(File imageFile) throws Exception {

        String image = null;
        BufferedImage buffImage = ImageIO.read(imageFile);

        if (buffImage != null) {
            java.io.ByteArrayOutputStream os = new java.io.ByteArrayOutputStream();
            ImageIO.write(buffImage, "jpg", os);
            byte[] data = os.toByteArray();
            image = MyBase64.encode(data);

        }
        return image;
    }

    public Map<String, String> fileInfoSeMap(String arrayOftext) throws MalformedURLException, IOException, FileNotFoundException {
        Map<String, String> map = new HashMap<String, String>();
        String line;
        String[] stringArrayVar = {"lineOneData", "lineTwoData", "lineThreeData", "type",
            "documentNumber", "dateOfBirth", "dateOfExpiry", "issuer",
            "nationality", "lastName", "firstName", "discretionary1",
            "discretionary2", "gender"};
        String[] stringArrayNames = {"Line One Data ", "Line Two Data ", "Line Three Data ", "Type ",
            "Document Number ", "Date of Birth ", "Date of Expiry ", "Issuer ",
            "Nationality ", "Last Names ", "First Names ", "Discretionary 1 ",
            "Discretionary 2 ", "Gender "};
        try {

            BufferedReader br = new BufferedReader(new StringReader(arrayOftext));
            while ((line = br.readLine()) != null) {
                // Deal with the line
                for (int i = 0; i < stringArrayVar.length; i++) {

                    if (line.contains(stringArrayNames[i])) {
                        stringArrayVar[i] = line.replace(stringArrayNames[i], "");
                        map.put(stringArrayNames[i], stringArrayVar[i]);
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
        return map;
    }

    public void sendDataToServerMap(String yourUrl, Map<String, String> map) throws MalformedURLException, IOException {

        Set<Map.Entry<String, String>> set = map.entrySet();

        URL url = new URL(yourUrl);
        URLConnection con = url.openConnection();
        // activate the output
        con.setDoOutput(true);
        PrintStream ps = new PrintStream(con.getOutputStream());
        // send your parameters to your site

        for (Map.Entry<String, String> m : set) {
            System.out.println("Key :" + m.getKey() + " Vlue : " + m.getValue());
            ps.print("&" + m.getKey().replace(" ", "") + "=" + m.getValue());

        }

        // we have to get the input stream in order to actually send the request
        con.getInputStream();

        // close the print stream
        ps.close();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        con.getInputStream()));
        String decodedString;
        while ((decodedString = in.readLine()) != null) {
            System.out.println(decodedString);
        }
        in.close();

    }
    String my;

    public String authFromZend(String yourUrl, String username, String password) throws MalformedURLException, IOException {

        URL url = new URL(yourUrl);
        URLConnection con = url.openConnection();
        // activate the output
        con.setDoOutput(true);
        PrintStream ps = new PrintStream(con.getOutputStream());
        // send your parameters to your site

        ps.print("&username=" + username);
        ps.print("&password=" + password);

        // we have to get the input stream in order to actually send the request
        con.getInputStream();

        // close the print stream
        ps.close();
        String decodedString;

        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        con.getInputStream()));

        while ((decodedString = in.readLine()) != null) {
            my = decodedString;
            System.out.println(decodedString);
        }

        in.close();
        System.out.println("after : " + my);
        return my;
    }

}
