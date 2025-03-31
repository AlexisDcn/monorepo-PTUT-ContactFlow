package isis.projet.backend.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Map;

@RestController
@RequestMapping("/api/qrcode")
public class QRCodeController {

    @PostMapping("/generate")
    public ResponseEntity<byte[]> generateQRCode(@RequestBody Map<String, String> request) {
        try {
            String url = request.get("url");
            String format = request.get("format").toLowerCase(); // "png" ou "jpeg"
            int size = Integer.parseInt(request.getOrDefault("size", "250"));

            // Vérifier les valeurs
            if (url == null || url.isEmpty()) {
                return ResponseEntity.badRequest().body(null);
            }

            if (!format.equals("png") && !format.equals("jpeg")) {
                format = "png"; // format par défaut
            }

            // Générer le QR code
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, size, size);
            BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

            // Convertir l'image en bytes
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(qrImage, format, baos);
            byte[] imageBytes = baos.toByteArray();

            // Définir les headers pour le téléchargement
            HttpHeaders headers = new HttpHeaders();
            MediaType mediaType = format.equals("png") ? MediaType.IMAGE_PNG : MediaType.IMAGE_JPEG;
            headers.setContentType(mediaType);
            headers.setContentDispositionFormData("attachment", "qrcode." + format);

            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}