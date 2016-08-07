/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfextract;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

/**
 *
 * @author hany
 */
public class dummy {

    public void fileInfo() throws MalformedURLException, IOException {
        Map<String, String> map = new HashMap<String, String>();
        List<String> myData = new ArrayList<String>();

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
                    stringArrayVar [i]= line.replace(stringArrayNames[i], "");
                    System.out.println(stringArrayNames[i]+" "+ stringArrayVar [i]);
                   // System.out.println(i);

                    }
                    
                }
                /* if (line.contains("Line One Data ")) {
                    lineOneData = line.replace("Line One Data ", "");
                    myData.add(lineOneData);
                    System.out.println(lineOneData);

                } else if (line.contains("Line Two Data ")) {
                    lineTwoData = line.replace("Line Two Data ", "");
                    myData.add(lineTwoData);
                    System.out.println(lineTwoData);

                } else if (line.contains("Line Three Data ")) {
                    lineThreeData = line.replace("Line Three Data ", "");
                    myData.add(lineThreeData);
                    System.out.println(lineThreeData);

                } else if (line.contains("Type ")) {
                    type = line.replace("Type ", "");
                    myData.add(type);

                    System.out.println(type);

                } else if (line.contains("Document Number ")) {
                    documentNumber = line.replace("Document Number ", "");
                    myData.add(documentNumber);

                    System.out.println(documentNumber);

                } else if (line.contains("Date of Birth ")) {
                    dateOfBirth = line.replace("Date of Birth ", "");
                    myData.add(dateOfBirth);

                    System.out.println(dateOfBirth);

                } else if (line.contains("Date of Expiry ")) {
                    dateOfExpiry = line.replace("Date of Expiry ", "");
                    myData.add(dateOfExpiry);

                    System.out.println(dateOfExpiry);

                } else if (line.contains("Issuer ")) {
                    issuer = line.replace("Issuer ", "");
                    myData.add(issuer);

                    System.out.println(issuer);

                } else if (line.contains("Nationality ")) {
                    nationality = line.replace("Nationality ", "");
                    myData.add(nationality);

                    System.out.println(nationality);

                } else if (line.contains("Last Names ")) {
                    lastName = line.replace("Last Names ", "");
                    myData.add(lastName);

                    System.out.println(lastName);

                } else if (line.contains("First Names ")) {
                    firstName = line.replace("First Names ", "");
                    myData.add(firstName);

                    System.out.println(firstName);

                } else if (line.contains("Discretionary 1 ")) {
                    discretionary1 = line.replace("Discretionary 1 ", "");
                    myData.add(discretionary1);

                    System.out.println(discretionary1);

                } else if (line.contains("Discretionary 2 ")) {
                    discretionary2 = line.replace("Discretionary 2 ", "");
                    myData.add(discretionary2);

                    System.out.println(discretionary2);

                } else if (line.contains("Gender ")) {
                    gender = line.replace("Gender ", "");
                    myData.add(gender);

                    System.out.println(gender);

                }*/

            }
            
            
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }

    }

    public void sendDataToServer(String yourUrl, String[] colNames, String[] postArray) throws MalformedURLException, IOException {
        URL url = new URL(yourUrl);
        URLConnection con = url.openConnection();
        // activate the output
        con.setDoOutput(true);
        PrintStream ps = new PrintStream(con.getOutputStream());
        // send your parameters to your site
        for (int i = 0; i <= postArray.length; i++) {

            ps.print("&" + colNames[i] + "=" + postArray[i] + "");
        }

        // we have to get the input stream in order to actually send the request
        con.getInputStream();

        // close the print stream
        ps.close();
    }

    public static void main(String[] args) throws MalformedURLException, IOException {
        ExtractInfo up = new ExtractInfo();
    //    up.fileInfo();
    }
    
        public boolean uploadFile(String fileToBeUploaded) {
        String server = "http://localhost/upload/";
        int port = 80;
        String user = "root";
        String pass = "dev";
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();

            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            // APPROACH #1: uploads first file using an InputStream
            File firstLocalFile = new File(fileToBeUploaded);

            String firstRemoteFile = "test.txt";
            InputStream inputStream = new FileInputStream(firstLocalFile);

            System.out.println("Start uploading first file");
            boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
            inputStream.close();
            if (done) {
                System.out.println("The first file is uploaded successfully.");
            }

            // APPROACH #2: uploads second file using an OutputStream
          /*  File secondLocalFile = new File("E:/Test/Report.doc");
            String secondRemoteFile = "test/Report.doc";
            inputStream = new FileInputStream(secondLocalFile);

            System.out.println("Start uploading second file");
            OutputStream outputStream = ftpClient.storeFileStream(secondRemoteFile);
            byte[] bytesIn = new byte[4096];
            int read = 0;

            while ((read = inputStream.read(bytesIn)) != -1) {
                outputStream.write(bytesIn, 0, read);
            }
            inputStream.close();
            outputStream.close();

            boolean completed = ftpClient.completePendingCommand();
            if (completed) {
                System.out.println("The second file is uploaded successfully.");
            }*/

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return true;
    }

}
