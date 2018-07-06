package com.app;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

@Controller
public class EmployeeController {

	@Autowired
	private RecaptchaUtil util;

	@GetMapping("/test")
	public String test() {

		return "index";
	}

	@PostMapping("/mahi")
	public String validatereCaptcha(HttpServletResponse response, HttpServletRequest request, HttpSession session,
			@RequestParam("recap") String recap, ModelMap map) {

		if (recap != null && session != null) {

			if (session.getAttribute("sesReCaptcha").equals(recap)) {

				map.addAttribute("msg", "Hello java spring world...");
				return "success";
			} else {

				map.addAttribute("msg", "pls enter valid recaptcha");
				request.getSession().removeAttribute("result");

				// Set appropriate http headers
				response.setHeader("Cache-Control", "no-store");
				response.setHeader("Pragma", "no-cache");
				response.setDateHeader("Expires", 0);
				response.setContentType("image/jpeg");

				return "index";
			}
		} else {
			map.addAttribute("msg", "pls enter recaptcha... ");
			return "index";

		}

	}

	@RequestMapping("/recaptchaImage.jpg")
	public void repcatcha(HttpServletRequest req, HttpServletResponse response, HttpSession session)
			throws IOException {
		// set mime type as jpg image
		response.setContentType("image/jpeg,image/jpg");
		ServletOutputStream out = response.getOutputStream();

		BufferedImage image = new BufferedImage(100, 40, BufferedImage.TYPE_BYTE_INDEXED);

		Graphics2D graphics = image.createGraphics();

		// Set back ground of the generated image to white
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, 200, 40);

		// set gradient font of text to be converted to image
		GradientPaint gradientPaint = new GradientPaint(10, 5, Color.BLUE, 20, 10, Color.LIGHT_GRAY, true);
		graphics.setPaint(gradientPaint);
		Font font = new Font("Comic Sans MS", Font.BOLD, 30);
		graphics.setFont(font);

		// write 'Hello World!' string in the image

		String recaptha = util.recap();

		session.setAttribute("sesReCaptcha", recaptha);
		System.out.println("session create...");

		graphics.drawString(recaptha, 5, 30);

		// release resources used by graphics context
		graphics.dispose();

		// encode the image as a JPEG data stream and write the same to servlet
		// output stream
		// sJPEGCodec.createJPEGEncoder(out).encode(image);

		// close the stream

		ImageIO.write(image, "png", out);

		out.close();
	}

	/*
	 * Bar code generate example
	 */
	@RequestMapping("/qr_image.jpg")
	private void createQRImage(HttpServletResponse response) throws WriterException, IOException {

		String qrCodeText = "Name : Mahendra \nDesgination : Java Developer";
		String filePath = "png";
		int size = 125;
		String fileType = "png";
		System.out.println("DONE");
		
		ServletOutputStream out = response.getOutputStream();

		// Create the ByteMatrix for the QR-Code that encodes the given String
		Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, size, size, hintMap);
		// Make the BufferedImage that are to hold the QRCode
		int matrixWidth = byteMatrix.getWidth();
		BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
		image.createGraphics();

		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, matrixWidth, matrixWidth);
		// Paint and save the image using the ByteMatrix
		graphics.setColor(Color.BLACK);

		for (int i = 0; i < matrixWidth; i++) {
			for (int j = 0; j < matrixWidth; j++) {
				if (byteMatrix.get(i, j)) {
					graphics.fillRect(i, j, 1, 1);
				}
			}
		}
		ImageIO.write(image, fileType, out);
	}
}
