package org.netlykos.barcode.utilities;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.oned.EAN13Writer;
import com.google.zxing.oned.UPCAWriter;
import com.google.zxing.pdf417.PDF417Writer;
import com.google.zxing.qrcode.QRCodeWriter;

import java.awt.image.BufferedImage;

public class BarcodeGenerator {

  private BarcodeGenerator() {
    /* do nothing constructor */
  }

  public static BufferedImage generateUPCABarcodeImage(String barcodeText) {
    UPCAWriter barcodeWriter = new UPCAWriter();
    BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.UPC_A, 300, 150);
    return MatrixToImageWriter.toBufferedImage(bitMatrix);
  }

  public static BufferedImage generateEAN13BarcodeImage(String barcodeText) {
    EAN13Writer barcodeWriter = new EAN13Writer();
    BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.EAN_13, 300, 150);
    return MatrixToImageWriter.toBufferedImage(bitMatrix);
  }

  public static BufferedImage generateCode128BarcodeImage(String barcodeText) {
    Code128Writer barcodeWriter = new Code128Writer();
    BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.CODE_128, 300, 150);
    return MatrixToImageWriter.toBufferedImage(bitMatrix);
  }

  public static BufferedImage generatePDF417BarcodeImage(String barcodeText) {
    PDF417Writer barcodeWriter = new PDF417Writer();
    try {
      BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.PDF_417, 700, 700);
      return MatrixToImageWriter.toBufferedImage(bitMatrix);
    } catch (WriterException we) {
      throw new BarcodeGeneratorException(we.getMessage(), we);
    }
  }

  public static BufferedImage generateQRCodeImage(String barcodeText) {
    QRCodeWriter barcodeWriter = new QRCodeWriter();
    try {
      BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);
      return MatrixToImageWriter.toBufferedImage(bitMatrix);
    } catch (WriterException we) {
      throw new BarcodeGeneratorException(we.getMessage(), we);
    }
  }
}
