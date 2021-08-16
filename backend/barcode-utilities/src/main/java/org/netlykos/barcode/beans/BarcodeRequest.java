package org.netlykos.barcode.beans;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

public class BarcodeRequest {

  @NotEmpty private BarcodeType barcodeType;
  @NotEmpty private String content;
  @Positive private Integer width, height;

  public BarcodeType getBarcodeType() {
    return barcodeType;
  }

  public void setBarcodeType(BarcodeType barcodeType) {
    this.barcodeType = barcodeType;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Integer getWidth() {
    return width;
  }

  public void setWidth(Integer width) {
    this.width = width;
  }

  public Integer getHeight() {
    return height;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }

}