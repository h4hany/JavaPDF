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
import de.javasoft.io.FileUtils;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author hany
 */
public class ControlPDF {

    String response;
   

    
    // extract string from pdf and push it into array
    public List<String> parsePdfToArrayList(String pdfPath) throws IOException {
        PdfReader reader = new PdfReader(pdfPath);
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        List<String> arrayOftext = new ArrayList<String>();

        TextExtractionStrategy strategy;
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
            arrayOftext.add(strategy.getResultantText());
        }
        reader.close();

        return arrayOftext;
    }

    // extract images from pdf and save it into destn folder
    public void extractImagesFromPdf(String filename, String save) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(filename);
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        ImageRenderListener listener = new ImageRenderListener(save);

        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            parser.processContent(i, listener);
        }
        reader.close();
    }
    
    /* 
        1- convert pdf to text
        2- return the face image to base64
    */
    public String pdfToDIrAndimgToString(String pdfPath, File destnationPath) throws IOException, DocumentException, Exception {
        //boolean success;
        String kk = "";
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
            extractImagesFromPdf(pdfPath, desPath.replace(".txt", "") + "/" + desPath.replace(".txt", ""));
            File f = new File(desPath.replace(".txt", "") + "/" + desPath.replace(".txt", "") + "-16.null");
            kk = imageToBase64String(f);
             /*File deletedFolder = new File(destnationPath.getParent());
             System.out.println("here"+destnationPath.getParent());
            deletedFolder.delete();
                System.out.println("is delete+ " + deletedFolder.delete());*/
             //       FileUtils.deleteDirectory(new File(deletedFolder.getParent()));
            //success = true;
        } catch (Exception ex) {
            //success = false;

        }

        return kk;
    }

    
    // convert image to base64
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

    
    // info. of the customer are in map of key and value 
    
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
            //  System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
        return map;
    }
    String msgStatus;

    
    // send data to server 
    public String sendDataToServerMap(String yourUrl, Map<String, String> map, String image) throws MalformedURLException, IOException {

        Set<Map.Entry<String, String>> set = map.entrySet();

        URL url = new URL(yourUrl);
        URLConnection con = url.openConnection();
        // activate the output
        con.setDoOutput(true);
        PrintStream ps = new PrintStream(con.getOutputStream());
        // send your parameters to your site
        ps.print("&image=" + image);

        for (Map.Entry<String, String> m : set) {
            // System.out.println("Key :" + m.getKey() + " Vlue : " + m.getValue());
            ps.print("&" + m.getKey().replace(" ", "") + "=" + m.getValue());

        }

        // we have to get the input stream in order to actually send the request
        Thread thread = new Thread() {
            public void run() {
                try {
                    con.getInputStream();
                } catch (IOException ex) {
                    Logger.getLogger(ControlPDF.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        };

        thread.start();

        // close the print stream
        ps.close();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        con.getInputStream()));
        String decodedString;
        while ((decodedString = in.readLine()) != null) {
          //  System.out.println(decodedString);
            msgStatus = decodedString;
        }
        in.close();

        return msgStatus;

    }

    
    // auth user name and password
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
        // read response from server
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        con.getInputStream()));

        while ((decodedString = in.readLine()) != null) {
            response = decodedString;
            // System.out.println(decodedString);
        }

        in.close();
        //  System.out.println("after : " + my);
        return response;
    }

}
