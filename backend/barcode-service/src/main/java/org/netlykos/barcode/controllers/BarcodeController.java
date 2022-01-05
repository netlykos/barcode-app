package org.netlykos.barcode.controllers;

import java.awt.image.BufferedImage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.netlykos.barcode.beans.BarcodeGenerateRequest;
import org.netlykos.barcode.beans.BarcodeResponse;
import org.netlykos.barcode.beans.BarcodeServiceResponse;
import org.netlykos.barcode.beans.BarcodeType;
import org.netlykos.barcode.service.BarcodeService;
import org.netlykos.barcode.utilities.ImageUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

/**
 * @author Adi B (netlykos-at-netlykos-dot-org)
 *
 * The {@link BarcodeController} class defines the endpoints related to barcode generation. The barcode created can be
 * imbedded inline using a <i>get</i> request, or as part of a request/response using a <i>put</i> method.
 */
@RestController
@RequestMapping("api/barcode")
public class BarcodeController {

  private static final Logger LOGGER = LogManager.getLogger(BarcodeController.class);
  private static final String HEADER_X_IMAGE_HEIGHT = "x-image-height";
  private static final String HEADER_X_IMAGE_WIDTH = "x-image-width";
  private static final String HEADER_X_BARCODE_STRING = "x-barcode-string";
  private static final String RESPONSE_APPLICATION_FAILURE = "Application failure - check server logs.";

  @Autowired
  private BarcodeService barcodeService;

  @PostMapping("/generate")
  public ResponseEntity<BarcodeResponse> generate(@RequestBody BarcodeGenerateRequest request) {
    try {
      BarcodeServiceResponse serviceResponse = barcodeService.generateBarcodeImage(request);
      BarcodeResponse response = BarcodeResponse.from(serviceResponse);
      String base64 = ImageUtilities.toBase64Encoding(serviceResponse.getBufferedImage(), ImageUtilities.IMAGE_FORMAT_PNG);
      response.setBase64EncodedImage(base64);
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      LOGGER.warn(e.getMessage(), e);
      throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, RESPONSE_APPLICATION_FAILURE, null);
    }
  }

  @Operation(summary = "Create a barcode image with the arguments provided")
  // @formatter:off
  @ApiResponse(responseCode = "200", description = "The service was successfully able to generate a barcode image with the content supplied.",
    content = @Content(mediaType = "image/png"), headers = {
    @Header(name = HEADER_X_BARCODE_STRING, description = "The barcode content used to generate the barcode image"),
    @Header(name = HEADER_X_IMAGE_HEIGHT, description = "The height of the barcode image"),
    @Header(name = HEADER_X_IMAGE_WIDTH, description = "The width of the barcode image")
  })
  @ApiResponse(responseCode = "400", description = "The request wasn't properly formed. Either the width was populated, but the height wasn't, or the width was populated, but the height wasn't.",
    content = @Content)
  @ApiResponse(responseCode = "418", description = "An internal application error has occurred, check the application logs for more details.", content = @Content)
  // @formatter:on
  @GetMapping(value = "/generate/{type}/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
  public ResponseEntity<BufferedImage> generate(
  //@formatter:off
    @Parameter(description = "The barcode type to be created") @PathVariable(name = "type") BarcodeType type,
    @Parameter(description = "The content to be used to create the given barcode") @PathVariable(name = "content") String content,
    @Parameter(description = "The width of the barcode image to be created", required = false) @RequestParam(name = "width", required = false) Integer width,
    @Parameter(description = "The height of the barcode image be created", required = false) @RequestParam(name = "height", required = false) Integer height)
  //@formatter:on
  {
    return create(type, content, width, height);
  }

  @Operation(summary = "Create a barcode image with the query arguments provided")
  // @formatter:off
  @ApiResponse(responseCode = "200", description = "The service was successfully able to generate a barcode image with the query parameters supplied.",
    content = @Content(mediaType = "image/png"), headers = {
    @Header(name = HEADER_X_BARCODE_STRING, description = "The barcode content used to generate the barcode image"),
    @Header(name = HEADER_X_IMAGE_HEIGHT, description = "The height of the barcode image"),
    @Header(name = HEADER_X_IMAGE_WIDTH, description = "The width of the barcode image")
  })
  @ApiResponse(responseCode = "400", description = "The request wasn't properly formed. Either the width was populated, but the height wasn't, or the width was populated, but the height wasn't.",
    content = @Content)
  @ApiResponse(responseCode = "418", description = "An internal application error has occurred, check the application logs for more details.", content = @Content)
  // @formatter:on
  @GetMapping(value = "/create", produces = MediaType.IMAGE_PNG_VALUE)
  public ResponseEntity<BufferedImage> create(
  //@formatter:off
    @Parameter(description = "The barcode type to be created") @RequestParam(name = "type") BarcodeType type,
    @Parameter(description = "The content to be used to create the given barcode") @RequestParam(name = "content") String content,
    @Parameter(description = "The width of the barcode image to be created", required = false) @RequestParam(name = "width", required = false) Integer width,
    @Parameter(description = "The height of the barcode image be created", required = false) @RequestParam(name = "height", required = false) Integer height)
  //@formatter:on
  {
    if ((width != null && height == null) || (width == null && height != null)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Both width and height need to be provided");
    }
    try {
      BarcodeServiceResponse response = barcodeService.generateBarcodeImage(type, content, width, height);
      HttpHeaders headers = new HttpHeaders();
      headers.set(HEADER_X_IMAGE_HEIGHT, response.getHeight().toString());
      headers.set(HEADER_X_IMAGE_WIDTH, response.getWidth().toString());
      headers.set(HEADER_X_BARCODE_STRING, response.getContent());
      return ResponseEntity.ok().headers(headers).body(response.getBufferedImage());
    } catch (Exception e) {
      LOGGER.warn(e.getMessage(), e);
      throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, RESPONSE_APPLICATION_FAILURE, null);
    }
  }

}
