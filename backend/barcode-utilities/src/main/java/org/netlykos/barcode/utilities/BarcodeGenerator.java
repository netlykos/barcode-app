package org.netlykos.barcode.utilities;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.oned.EAN13Writer;
import com.google.zxing.oned.UPCAWriter;
import com.google.zxing.pdf417.PDF417Writer;
import com.google.zxing.qrcode.QRCodeWriter;

import org.netlykos.barcode.beans.BarcodeRequest;
import org.netlykos.barcode.beans.BarcodeResponse;
import org.netlykos.barcode.beans.BarcodeType;
import org.springframework.stereotype.Component;

/**
 * @author Adi B (netlykos-at-netlykos-dot-org)
 *
 * This class is a singleton that supports generating barcode images of various types.
 */
@Component
public class BarcodeGenerator {

  private Map<BarcodeType, Writer> writers;
  private Map<BarcodeType, BarcodeFormat> formats;
  private Map<BarcodeType, Integer> defaultHeights;
  private Map<BarcodeType, Integer> defaultWidths;

  {
    Map<BarcodeType, Writer> writersMap = new HashMap<>();
    writersMap.put(BarcodeType.UPCA, new UPCAWriter());
    writersMap.put(BarcodeType.EAN13, new EAN13Writer());
    writersMap.put(BarcodeType.CODE128, new Code128Writer());
    writersMap.put(BarcodeType.PDF417, new PDF417Writer());
    writersMap.put(BarcodeType.QRCODE, new QRCodeWriter());
    writers = Collections.unmodifiableMap(writersMap);

    Map<BarcodeType, Integer> widthMap = new HashMap<>();
    widthMap.put(BarcodeType.UPCA, 300);
    widthMap.put(BarcodeType.EAN13, 300);
    widthMap.put(BarcodeType.CODE128, 300);
    widthMap.put(BarcodeType.PDF417, 700);
    widthMap.put(BarcodeType.QRCODE, 200);
    defaultWidths = Collections.unmodifiableMap(widthMap);

    Map<BarcodeType, Integer> heightMap = new HashMap<>();
    heightMap.put(BarcodeType.UPCA, 150);
    heightMap.put(BarcodeType.EAN13, 150);
    heightMap.put(BarcodeType.CODE128, 150);
    heightMap.put(BarcodeType.PDF417, 700);
    heightMap.put(BarcodeType.QRCODE, 200);
    defaultHeights = Collections.unmodifiableMap(heightMap);

    Map<BarcodeType, BarcodeFormat> formatMap = new HashMap<>();
    formatMap.put(BarcodeType.UPCA, BarcodeFormat.UPC_A);
    formatMap.put(BarcodeType.EAN13, BarcodeFormat.EAN_13);
    formatMap.put(BarcodeType.CODE128, BarcodeFormat.CODE_128);
    formatMap.put(BarcodeType.PDF417, BarcodeFormat.PDF_417);
    formatMap.put(BarcodeType.QRCODE, BarcodeFormat.QR_CODE);
    formats = Collections.unmodifiableMap(formatMap);
  }

  public BarcodeResponse generateBarcodeImage(BarcodeRequest request) {
    return generateBarcodeImage(request.getBarcodeType(), request.getContent(), request.getWidth(),
        request.getHeight());
  }

  public BarcodeResponse generateBarcodeImage(BarcodeType barcodeType, String text, Integer width, Integer height) {
    Writer writer = writers.get(barcodeType);
    BarcodeFormat format = formats.get(barcodeType);
    Integer barcodeWidth = width == null ? defaultWidths.get(barcodeType) : width;
    Integer barcodeHeight = height == null ? defaultHeights.get(barcodeType) : height;
    BarcodeResponse response = new BarcodeResponse().barcodeType(barcodeType).content(text).width(barcodeWidth)
        .height(barcodeHeight);
    return generateBarcodeImage(writer, format, response);
  }

  protected BarcodeResponse generateBarcodeImage(Writer writer, BarcodeFormat format, BarcodeResponse barcodeResponse) {
    String text = barcodeResponse.getContent();
    Integer width = barcodeResponse.getWidth();
    Integer height = barcodeResponse.getHeight();
    try {
      BitMatrix bitMatrix = writer.encode(text, format, width, height);
      return barcodeResponse.bufferedImage(MatrixToImageWriter.toBufferedImage(bitMatrix));
    } catch (WriterException we) {
      throw new BarcodeGeneratorException(we.getMessage(), we);
    }
  }

}
