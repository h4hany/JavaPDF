/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfextract;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PRStream;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStream;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringReader;
import static java.lang.reflect.Array.set;
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
public class ExtractInfo {

    public void fileInfo() throws MalformedURLException, IOException {
        //  Map<String, String> map = new HashMap<String, String>();

        String line;
        String lineOneData, lineTwoData, lineThreeData, type, documentNumber, dateOfBirth, dateOfExpiry, issuer,
                nationality, lastName, firstName, discretionary1, discretionary2, gender;

        String[] stringArrayVar = {"lineOneData", "lineTwoData", "lineThreeData", "type", "documentNumber", "dateOfBirth", "dateOfExpiry", "issuer",
            "nationality", "lastName", "firstName", "discretionary1", "discretionary2", "gender"};
        String[] stringArrayNames = {"Line One Data ", "Line Two Data ", "Line Three Data ", "Type ", "Document Number ", "Date of Birth ", "Date of Expiry ", "Issuer ", "Nationality ", "Last Names ", "First Names ", "Discretionary 1 ", "Discretionary 2 ", "Gender "

        };
        try {
            InputStream fis = new FileInputStream("test.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            //line = br.readLine();
            int i = 0;
            int n = 14;
            while ((line = br.readLine()) != null) {
                // Deal with the line

                for (i = 0; i < stringArrayVar.length; i++) {

                    if (line.contains(stringArrayNames[i])) {
                        stringArrayVar[i] = line.replace(stringArrayNames[i], "");
                        System.out.println(stringArrayNames[i] + " " + stringArrayVar[i]);
                    }

                }

            }

            /**
             * here i will call the function sendDataToServer it take 3
             * parameter
             *
             * @param yourUrl server URL
             * @param colNames Names of Col in database
             * @param postArray variable will be send
             */
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }

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

    /**
     *
     * @param yourUrl
     * @param colNames
     * @param postArray
     * @throws MalformedURLException
     * @throws IOException
     */
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

    public void extractImagesInfo() {
        try {
            PdfReader chartReader = new PdfReader("vv.pdf");
            for (int i = 0; i < chartReader.getXrefSize(); i++) {
                PdfObject pdfobj = chartReader.getPdfObject(i);
                if (pdfobj != null && pdfobj.isStream()) {
                    PdfStream stream = (PdfStream) pdfobj;
                    PdfObject pdfsubtype = stream.get(PdfName.SUBTYPE);
                    //System.out.println("Stream subType: " + pdfsubtype); 
                    if (pdfsubtype != null
                            && pdfsubtype.toString().equals(PdfName.IMAGE.toString())) {
                        byte[] image = PdfReader.getStreamBytesRaw((PRStream) stream);
                        Image imageObject = Image.getInstance(image);
                        System.out.println("Resolution" + imageObject.getDpiX());
                        System.out.println("Height" + imageObject.getHeight());
                        System.out.println("Width" + imageObject.getWidth());

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void imageNew() throws IOException {
        PdfReader reader;

        File file = new File("vv.pdf");
        reader = new PdfReader(file.getAbsolutePath());
        for (int i = 0; i < reader.getXrefSize(); i++) {
            PdfObject pdfobj = reader.getPdfObject(i);
            if (pdfobj == null || !pdfobj.isStream()) {
                continue;
            }
            PdfStream stream = (PdfStream) pdfobj;
            PdfObject pdfsubtype = stream.get(PdfName.SUBTYPE);
            if (pdfsubtype != null && pdfsubtype.toString().equals(PdfName.IMAGE.toString())) {
                byte[] img = PdfReader.getStreamBytesRaw((PRStream) stream);
                FileOutputStream out = new FileOutputStream(new File(file.getParentFile(), String.format("%1$05d", i) + ".jpg"));
                out.write(img);
                out.flush();
                out.close();
            }
        }
    }

    public void extractImages(String filename)throws IOException, DocumentException {
        
        PdfReader reader = new PdfReader(filename);
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        ImageRenderListener listener = new ImageRenderListener("spdf");

        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            parser.processContent(i, listener);
        }
        reader.close();
        BufferedImage buffImage;
    }

    public byte[] extractBytes(String ImageName) throws IOException {
        // open image
        File imgPath = new File(ImageName);
        BufferedImage bufferedImage = ImageIO.read(imgPath);

        // get DataBufferBytes from Raster
        WritableRaster raster = bufferedImage.getRaster();
        DataBufferByte data = (DataBufferByte) raster.getDataBuffer();

        return (data.getData());
    }

    public void sendDataToServern(String yourUrl, String aa) throws MalformedURLException, IOException {
        URL url = new URL(yourUrl);
        URLConnection con = url.openConnection();
        // activate the output
        con.setDoOutput(true);
        PrintStream ps = new PrintStream(con.getOutputStream());
        // send your parameters to your site

        ps.print("&data=" + aa);

        // we have to get the input stream in order to actually send the request
        con.getInputStream();

        // close the print stream
        ps.close();
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
    }

    /*public static void main(String[] args) throws Exception, IOException {
        ExtractInfo up = new ExtractInfo();
        up.extractImages("z.pdf");

    }*/
}
