package org.netlykos.barcode.controllers;

import org.netlykos.barcode.BarcodeGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;

import com.google.zxing.WriterException;

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
  public ResponseEntity<BufferedImage> pdf417Barcode(@RequestBody String barcode) throws WriterException {
    return okResponse(BarcodeGenerator.generatePDF417BarcodeImage(barcode));
  }

  @PostMapping(value = "/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
  public ResponseEntity<BufferedImage> qrCode(@RequestBody String barcode) throws WriterException {
    return okResponse(BarcodeGenerator.generateQRCodeImage(barcode));
  }

  private ResponseEntity<BufferedImage> okResponse(BufferedImage image) {
    return new ResponseEntity<>(image, HttpStatus.OK);
  }

}
