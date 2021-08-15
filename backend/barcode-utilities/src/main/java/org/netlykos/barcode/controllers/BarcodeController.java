package org.netlykos.barcode.controllers;

import java.awt.image.BufferedImage;

import org.netlykos.barcode.utilities.BarcodeGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/barcode")
public class BarcodeController {

  @GetMapping(value = "/upca/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
  public ResponseEntity<BufferedImage> upcaBarcode(@PathVariable("barcode") String barcode) {
    return okResponse(BarcodeGenerator.generateUPCABarcodeImage(barcode));
  }

  @GetMapping(value = "/ean13/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
  public ResponseEntity<BufferedImage> ean13Barcode(@PathVariable("barcode") String barcode) {
    return okResponse(BarcodeGenerator.generateEAN13BarcodeImage(barcode));
  }

  @PostMapping(value = "/code128", produces = MediaType.IMAGE_PNG_VALUE)
  public ResponseEntity<BufferedImage> code128Barcode(@RequestBody String barcode) {
    return okResponse(BarcodeGenerator.generateCode128BarcodeImage(barcode));
  }

  @PostMapping(value = "/pdf417", produces = MediaType.IMAGE_PNG_VALUE)
  public ResponseEntity<BufferedImage> pdf417Barcode(@RequestBody String barcode) {
    return okResponse(BarcodeGenerator.generatePDF417BarcodeImage(barcode));
  }

  @PostMapping(value = "/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
  public ResponseEntity<BufferedImage> qrCode(@RequestBody String barcode) {
    return okResponse(BarcodeGenerator.generateQRCodeImage(barcode));
  }

  private static ResponseEntity<BufferedImage> okResponse(BufferedImage image) {
    return new ResponseEntity<>(image, HttpStatus.OK);
  }

}
