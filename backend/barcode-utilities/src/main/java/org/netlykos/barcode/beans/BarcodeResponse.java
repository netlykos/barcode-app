package org.netlykos.barcode.beans;

import java.awt.image.BufferedImage;

public class BarcodeResponse extends BarcodeRequest {

  private BufferedImage bufferedImage;

  public BufferedImage getBufferedImage() {
    return bufferedImage;
  }

  public void setBufferedImage(BufferedImage bufferedImage) {
    this.bufferedImage = bufferedImage;
  }

  public BarcodeResponse bufferedImage(BufferedImage bufferedImage) {
    setBufferedImage(bufferedImage);
    return this;
  }

  public BarcodeResponse barcodeType(BarcodeType barcodeType) {
    setBarcodeType(barcodeType);
    return this;
  }

  public BarcodeResponse content(String content) {
    setContent(content);
    return this;
  }

  public BarcodeResponse height(Integer height) {
    setHeight(height);
    return this;
  }

  public BarcodeResponse width(Integer width) {
    setWidth(width);
    return this;
  }

}
