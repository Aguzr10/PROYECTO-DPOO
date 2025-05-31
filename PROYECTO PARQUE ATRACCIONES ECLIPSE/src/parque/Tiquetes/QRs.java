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
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class QRs {

    public static void generarQR(Tiquete tiquete)
            throws WriterException, IOException {
    	String filePath = "./datos/qr.png";
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L); // Set error correction level
        hints.put(EncodeHintType.MARGIN, 1); // Set margin

        String tipo = "TIPO TIQUETE: " + tiquete.getCategoriaTiquete();
        String id = "ID: ";
        String printDate = "FECHA EXPEDICIÓN: " + LocalDateTime.now().toString();
        String text = tipo + "\n" + id + "\n" + printDate; // Texto a poner en el QR
        
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 500, 500, hints);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        System.out.println("Código QR correctamente generado en: " + filePath);
    }
}
