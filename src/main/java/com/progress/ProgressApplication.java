package com.progress;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@SpringBootApplication
public class ProgressApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProgressApplication.class, args);
		
//		generateQR("AzPL-590", 300, 300, "/home/yoshi/qr.png");
	}
	
	public static void generateQR(String text, int width, int height, String savePath) {		
		try {
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
			
			Path path = FileSystems.getDefault().getPath(savePath);			
			MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);			
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
