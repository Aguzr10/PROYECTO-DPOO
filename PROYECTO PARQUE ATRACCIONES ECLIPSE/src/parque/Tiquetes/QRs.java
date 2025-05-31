package parque.Tiquetes;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;

import java.awt.image.BufferedImage;

public class QRs {
    public static BufferedImage generarQRImagen(String texto, int width, int height) throws WriterException {
        QRCodeWriter qrWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrWriter.encode(texto, BarcodeFormat.QR_CODE, width, height);

        BufferedImage imagen = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int color = bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF; 
                imagen.setRGB(x, y, color);
            }
        }
        return imagen;
    }
}

