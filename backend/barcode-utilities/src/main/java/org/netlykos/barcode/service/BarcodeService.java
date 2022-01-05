package org.netlykos.barcode.service;

import java.util.Collections;
import java.util.EnumMap;
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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.netlykos.barcode.beans.BarcodeGenerateRequest;
import org.netlykos.barcode.beans.BarcodeServiceResponse;
import org.netlykos.barcode.beans.BarcodeType;
import org.springframework.stereotype.Component;

/**
 * This class is a singleton that supports generating barcode images of various types.
 *
 * @author Adi B (netlykos-at-netlykos-dot-org)
 */
@Component
public class BarcodeService {

  private static final Logger LOGGER = LogManager.getLogger(BarcodeService.class);

  private static Map<BarcodeType, Writer> writers = buildWriters();
  private static Map<BarcodeType, BarcodeFormat> formats = buildBarcodeFormats();
  private static Map<BarcodeType, Integer> defaultHeights = buildDefaultHeights();
  private static Map<BarcodeType, Integer> defaultWidths = buildDefaultWidth();

  public BarcodeServiceResponse generateBarcodeImage(BarcodeGenerateRequest request) throws WriterException {
    return generateBarcodeImage(request.getBarcodeType(), request.getContent(), request.getWidth(), request.getHeight());
  }

  public BarcodeServiceResponse generateBarcodeImage(BarcodeType type, String content, Integer width, Integer height) throws WriterException {
    Writer writer = writers.get(type);
    BarcodeFormat format = formats.get(type);
    Integer barcodeWidth = getDefaultIfNotPopulated(width, defaultWidths, type);
    Integer barcodeHeight = getDefaultIfNotPopulated(height, defaultHeights, type);
    BarcodeServiceResponse response = new BarcodeServiceResponse().barcodeType(type).content(content).width(barcodeWidth).height(barcodeHeight);
    return generateBarcodeImage(writer, format, response);
  }

  private BarcodeServiceResponse generateBarcodeImage(Writer writer, BarcodeFormat format, BarcodeServiceResponse response) throws WriterException {
    String content = response.getContent();
    Integer width = response.getWidth();
    Integer height = response.getHeight();
    LOGGER.debug("Generating a barcode of type [{}] with width [{}], height [{}] and content [{}]", format, width, height, content);
    BitMatrix bitMatrix = writer.encode(content, format, width, height);
    return response.bufferedImage(MatrixToImageWriter.toBufferedImage(bitMatrix));
  }

  private static Integer getDefaultIfNotPopulated(Integer supplied, Map<BarcodeType, Integer> defaultMap, BarcodeType type) {
    if (supplied == null || supplied < 1) {
      return defaultMap.get(type);
    }
    return supplied;
  }

  private static final Map<BarcodeType, Writer> buildWriters() {
    Map<BarcodeType, Writer> writersMap = new EnumMap<>(BarcodeType.class);
    writersMap.put(BarcodeType.UPCA, new UPCAWriter());
    writersMap.put(BarcodeType.EAN13, new EAN13Writer());
    writersMap.put(BarcodeType.CODE128, new Code128Writer());
    writersMap.put(BarcodeType.PDF417, new PDF417Writer());
    writersMap.put(BarcodeType.QRCODE, new QRCodeWriter());
    return Collections.unmodifiableMap(writersMap);
  }

  private static Map<BarcodeType, BarcodeFormat> buildBarcodeFormats() {
    Map<BarcodeType, BarcodeFormat> formatMap = new EnumMap<>(BarcodeType.class);
    formatMap.put(BarcodeType.UPCA, BarcodeFormat.UPC_A);
    formatMap.put(BarcodeType.EAN13, BarcodeFormat.EAN_13);
    formatMap.put(BarcodeType.CODE128, BarcodeFormat.CODE_128);
    formatMap.put(BarcodeType.PDF417, BarcodeFormat.PDF_417);
    formatMap.put(BarcodeType.QRCODE, BarcodeFormat.QR_CODE);
    return Collections.unmodifiableMap(formatMap);
  }

  private static Map<BarcodeType, Integer> buildDefaultHeights() {
    Map<BarcodeType, Integer> heightMap = new EnumMap<>(BarcodeType.class);
    heightMap.put(BarcodeType.UPCA, 150);
    heightMap.put(BarcodeType.EAN13, 150);
    heightMap.put(BarcodeType.CODE128, 150);
    heightMap.put(BarcodeType.PDF417, 700);
    heightMap.put(BarcodeType.QRCODE, 200);
    return Collections.unmodifiableMap(heightMap);
  }

  private static final Map<BarcodeType, Integer> buildDefaultWidth() {
    Map<BarcodeType, Integer> widthMap = new EnumMap<>(BarcodeType.class);
    widthMap.put(BarcodeType.UPCA, 300);
    widthMap.put(BarcodeType.EAN13, 300);
    widthMap.put(BarcodeType.CODE128, 300);
    widthMap.put(BarcodeType.PDF417, 700);
    widthMap.put(BarcodeType.QRCODE, 200);
    return Collections.unmodifiableMap(widthMap);
  }

}
