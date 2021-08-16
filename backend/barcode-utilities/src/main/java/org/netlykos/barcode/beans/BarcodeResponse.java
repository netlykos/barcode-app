package org.netlykos.barcode.beans;

public class BarcodeResponse extends BarcodeGenerateRequest {

  private String base64EncodedImage;

  public String getBase64EncodedImage() {
    return base64EncodedImage;
  }

  public void setBase64EncodedImage(String base64EncodedImage) {
    this.base64EncodedImage = base64EncodedImage;
  }

  public BarcodeResponse base64EncodedImage(String base64EncodedImage) {
    setBase64EncodedImage(base64EncodedImage);
    return this;
  }

  public static <T extends BarcodeBaseBean> BarcodeResponse from(T t) {
    BarcodeResponse response = new BarcodeResponse();
    from(t, response);
    return response;
  }

}
