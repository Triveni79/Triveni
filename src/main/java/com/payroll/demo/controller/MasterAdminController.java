package com.payroll.demo.controller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.payroll.demo.entity.MasterAdmin;
import com.payroll.demo.repository.MasterAdminRepo;
import com.payroll.demo.service.MasterAdminService;

@RestController
@RequestMapping("/api/masteradmin")
public class MasterAdminController {

    private AtomicInteger n = new AtomicInteger(1);

    @Autowired
    private MasterAdminService masterAdminService;

    @Autowired
    private MasterAdminRepo repo;

    @PostMapping("/save")
    public ResponseEntity<MasterAdmin> saveBusiness(@RequestParam String organisationName,
                                                    @RequestParam String numberOfEmployees,
                                                    @RequestParam long phoneNo,
                                                    @RequestParam String contactPerson,
                                                    @RequestParam String emailId,
                                                    @RequestParam String password,
                                                    @RequestParam String address,
                                                    @RequestParam(required = false) String about,
                                                    @RequestParam(name = "logoFile", required = false) MultipartFile logoFile,
                                                    @RequestParam Date registrationDate) {

        String login = "man";
        String prefix = organisationName.substring(0, Math.min(4, organisationName.length())).toLowerCase();
        String formattedSequenceNumber = String.format("%03d", n.getAndIncrement());
        String businessId = login + prefix + formattedSequenceNumber + "@aptits.com";

        String url = "http://localhost:4200/" + organisationName;
        int size = 300;
        int borderSize = 1;
        int imageSize = size - (borderSize * 2);
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, 0);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, imageSize, imageSize, hints);
            BufferedImage qrCodeImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = qrCodeImage.createGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, size, size);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < imageSize; i++) {
                for (int j = 0; j < imageSize; j++) {
                    if (bitMatrix.get(i, j)) {
                        graphics.fillRect(borderSize + i, borderSize + j, 1, 1);
                    }
                }
            }

            graphics.setColor(Color.BLACK);
            graphics.setStroke(new BasicStroke(borderSize));
            graphics.drawRoundRect(borderSize, borderSize, imageSize, imageSize, 20, 20);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(qrCodeImage, "png", out);
            byte[] qrCodeBytes = out.toByteArray();

            MasterAdmin business = new MasterAdmin();
            business.setBusinessId(businessId);
            business.setOrganisationName(organisationName);
            business.setNumberOfEmployees(numberOfEmployees);
            business.setPhoneNo(phoneNo);
            business.setContactPerson(contactPerson);
            business.setEmailId(emailId);
            business.setPassword(password);
            business.setAddress(address);
            business.setAbout(about);
            business.setQrCode(qrCodeBytes);
            business.setRegistrationDate(registrationDate);

            if (logoFile != null && !logoFile.isEmpty()) {
                byte[] logoBytes = logoFile.getBytes();
                business.setLogo(logoBytes);
            }

            MasterAdmin savedBusiness = masterAdminService.saveBusiness(business);
            return new ResponseEntity<>(savedBusiness, HttpStatus.CREATED);
        } catch (WriterException | IOException e) {
            throw new RuntimeException("Failed to save business", e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginBusiness(@RequestBody Map<String, String> loginData) {
        String businessId = loginData.get("businessId");
        String password = loginData.get("password");

        MasterAdmin business = masterAdminService.findBusinessById(businessId);

        if (business != null && password.equals(business.getPassword())) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Login successful");
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Invalid business ID or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }

    @GetMapping("/find/{businessId}")
    public ResponseEntity<MasterAdmin> findBusinessById(@PathVariable("businessId") String businessId) {
        MasterAdmin business = masterAdminService.findBusinessById(businessId);
        if (business == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(business, HttpStatus.OK);
        }
    }

    @GetMapping("/findall")
    public ResponseEntity<List<MasterAdmin>> findAllBusinesses() {
        List<MasterAdmin> businesses = masterAdminService.findAllBusinesses();
        return new ResponseEntity<>(businesses, HttpStatus.OK);
    }

    @PutMapping("/update/{businessId}")
    public ResponseEntity<MasterAdmin> updateBusiness(@PathVariable("businessId") String businessId,
                                                      @RequestParam("organisationName") String organisationName,
                                                      @RequestParam("numberOfEmployees") String numberOfEmployees,
                                                      @RequestParam("phoneNo") long phoneNo,
                                                      @RequestParam("contactPerson") String contactPerson,
                                                      @RequestParam("emailId") String emailId,
                                                      @RequestParam("password") String password,
                                                      @RequestParam("address") String address,
                                                      @RequestParam("about") String about,
                                                      @RequestParam("registrationDate") Date registrationDate,
                                                      @RequestParam(required = false) MultipartFile logo) {
        MasterAdmin existingBusiness = masterAdminService.findBusinessById(businessId);
        if (existingBusiness == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        existingBusiness.setOrganisationName(organisationName);
        existingBusiness.setNumberOfEmployees(numberOfEmployees);
        existingBusiness.setPhoneNo(phoneNo);
        existingBusiness.setContactPerson(contactPerson);
        existingBusiness.setEmailId(emailId);
        existingBusiness.setPassword(password);
        existingBusiness.setAddress(address);
        existingBusiness.setAbout(about);
        existingBusiness.setRegistrationDate(registrationDate);

        if (logo != null && !logo.isEmpty()) {
            try {
                byte[] logoBytes = logo.getBytes();
                existingBusiness.setLogo(logoBytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        MasterAdmin savedBusiness = masterAdminService.updateBusiness(existingBusiness);
        return new ResponseEntity<>(savedBusiness, HttpStatus.OK);
    }

    @DeleteMapping("/deletebusiness/{businessId}")
    public ResponseEntity<String> deleteBusiness(@PathVariable("businessId") String businessId) {
        masterAdminService.deleteBusiness(businessId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        long count = repo.count();
        return ResponseEntity.ok().body(count);
    }
}
