package tvz.nppjj.paris.helpers;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.EnumMap;
import java.util.Map;

/**
 * Created by filipvinkovic on 18/06/16.
 */
public class BarcodeHelper {

    private static final int IMAGE_WIDTH = 400;

    public static String generateBase64Barcode(String code) {
        String fileType = "png";
        try {

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            Map<EncodeHintType, Object> hintMap = new EnumMap<>(EncodeHintType.class);
            hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            hintMap.put(EncodeHintType.MARGIN, 1);
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix byteMatrix = qrCodeWriter.encode(code, BarcodeFormat.QR_CODE, IMAGE_WIDTH, IMAGE_WIDTH, hintMap);
            int imageSize = byteMatrix.getWidth();
            BufferedImage image = new BufferedImage(imageSize, imageSize, BufferedImage.TYPE_INT_RGB);
            image.createGraphics();

            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, imageSize, imageSize);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < imageSize; i++) {
                for (int j = 0; j < imageSize; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            ImageIO.write(image, fileType, stream);
            return Base64.getEncoder().encodeToString(stream.toByteArray());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
