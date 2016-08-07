/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfextract;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

/**
 *
 * @author hany
 */
public class ImageManipulation {

    public byte[] extractBytes(String ImageName) throws IOException {
        // open image
        File imgPath = new File(ImageName);
        BufferedImage bufferedImage = ImageIO.read(imgPath);

        // get DataBufferBytes from Raster
        WritableRaster raster = bufferedImage.getRaster();
        DataBufferByte data = (DataBufferByte) raster.getDataBuffer();

        return (data.getData());
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

    public static void main(String[] args) throws IOException, Exception {
        ImageManipulation m = new ImageManipulation();
        //m.extractBytes("doaa/doaa-16.null");
        File f=new File("doaa/doaa-16.null");
        System.out.println(m.imageToBase64String(f));
    }
}
