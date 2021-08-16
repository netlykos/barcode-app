package org.netlykos.barcode.beans;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

public class BarcodeBaseBean {

  @NotEmpty
  private BarcodeType barcodeType;
  @NotEmpty
  private String content;
  @Positive
  private Integer width, height;

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

  @SuppressWarnings("unchecked")
  public <T extends BarcodeBaseBean> T barcodeType(BarcodeType barcodeType) {
    setBarcodeType(barcodeType);
    return (T) this;
  }

  @SuppressWarnings("unchecked")
  public <T extends BarcodeBaseBean> T content(String content) {
    setContent(content);
    return (T) this;
  }

  @SuppressWarnings("unchecked")
  public <T extends BarcodeBaseBean> T height(Integer height) {
    setHeight(height);
    return (T) this;
  }

  @SuppressWarnings("unchecked")
  public <T extends BarcodeBaseBean> T width(Integer width) {
    setWidth(width);
    return (T) this;
  }

  public static <T extends BarcodeBaseBean> T from(T source, T target) {
    return target.barcodeType(source.getBarcodeType()).content(source.getContent()).height(source.getHeight()).width(source.getWidth());
  }

}