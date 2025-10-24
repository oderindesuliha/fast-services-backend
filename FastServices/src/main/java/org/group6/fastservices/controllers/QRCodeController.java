package org.group6.fastservices.controllers;

import com.google.zxing.WriterException;
import org.group6.fastservices.dtos.responses.ErrorResponse;
import org.group6.fastservices.utils.QRCodeGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/qrcode")
@CrossOrigin(origins ="*")
public class QRCodeController {
    
    private static final String QR_CODE_BASE_URL = "https://fastservice.com/queue/";
    
    @GetMapping("/organization/{orgId}")
    public ResponseEntity<?> generateOrganizationQRCode(@PathVariable String orgId) {
        try {
            String qrCodeData = QR_CODE_BASE_URL + "organization/" + orgId;
            String qrCodeImage = QRCodeGenerator.generateQRCode(qrCodeData, 200, 200);
            return ResponseEntity.ok(qrCodeImage);
        } catch (WriterException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>("Error generating QR code: " + e.getMessage()));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>("Error generating QR code: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new ErrorResponse<>("Unexpected error: " + e.getMessage()));
        }
    }
    
    @GetMapping("/offering/{offeringId}")
    public ResponseEntity<?> generateOfferingQRCode(@PathVariable String offeringId) {
        try {
            String qrCodeData = QR_CODE_BASE_URL + "offering/" + offeringId;
            String qrCodeImage = QRCodeGenerator.generateQRCode(qrCodeData, 200, 200);
            return ResponseEntity.ok(qrCodeImage);
        } catch (WriterException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>("Error generating QR code: " + e.getMessage()));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>("Error generating QR code: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>("Unexpected error: " + e.getMessage()));
        }
    }
    
    @GetMapping("/queue/{queueId}")
    public ResponseEntity<?> generateQueueQRCode(@PathVariable String queueId) {
        try {
            String qrCodeData = QR_CODE_BASE_URL + "queue/" + queueId;
            String qrCodeImage = QRCodeGenerator.generateQRCode(qrCodeData, 200, 200);
            return ResponseEntity.ok(qrCodeImage);
        } catch (WriterException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>("Error generating QR code: " + e.getMessage()));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>("Error generating QR code: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>("Unexpected error: " + e.getMessage()));
        }

    }
}