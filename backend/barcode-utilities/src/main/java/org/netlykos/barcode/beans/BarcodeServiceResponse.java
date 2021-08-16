package org.netlykos.barcode.beans;

import java.awt.image.BufferedImage;

public class BarcodeServiceResponse extends BarcodeBaseBean {

  private BufferedImage bufferedImage;

  public BufferedImage getBufferedImage() {
    return bufferedImage;
  }

  public void setBufferedImage(BufferedImage bufferedImage) {
    this.bufferedImage = bufferedImage;
  }

  public BarcodeServiceResponse bufferedImage(BufferedImage bufferedImage) {
    setBufferedImage(bufferedImage);
    return this;
  }

}
