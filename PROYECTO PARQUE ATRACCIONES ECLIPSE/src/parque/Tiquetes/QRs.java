package parque.Tiquetes;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class QRs {

    public static void generarQR(String text)
            throws WriterException, IOException {
    	int height = 0, width = 0;
    	String filePath = "";
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L); // Set error correction level
        hints.put(EncodeHintType.MARGIN, 1); // Set margin

        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hints);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        System.out.println("Código QR correctamente generado en: " + filePath);
    }

    public static void main(String[] args) {
        String qrText = "https://www.example.com"; // Texto a convertir en QR

        try {
            generarQR(qrText);
        } catch (WriterException e) {
            System.err.println("Could not generate QR Code, WriterException :: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Could not write QR Code image, IOException :: " + e.getMessage());
        }
    }
}
