package org.vn.locationapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vn.locationapi.services.LocationService;

@RestController
@RequestMapping("/api/location")
public class LocationApi {
    @Autowired
    LocationService service;

    @GetMapping("/province")
    public ResponseEntity<?> getProvinces() {
        return ResponseEntity.ok(service.getAllProvince());
    }

    @GetMapping("/province/{provinceId}")
    public ResponseEntity<?> getProvinceById(@PathVariable(name = "provinceId") String provinceId) {
        return ResponseEntity.ok(service.getProvinceById(provinceId));
    }
    @GetMapping("/district")
    public ResponseEntity<?> getDistricts(@RequestParam(name = "provinceId") String provinceId) {
        return ResponseEntity.ok(service.getAllDistrictByProvince(provinceId));
    }
    @GetMapping("/district/{districtId}")
    public ResponseEntity<?> getDistrictById(@PathVariable(name = "districtId") String districtId) {
        return ResponseEntity.ok(service.getDistrictById(districtId));
    }

    @GetMapping("/ward")
    public ResponseEntity<?> getWards(@RequestParam(name = "districtId") String districtId) {
        return ResponseEntity.ok(service.getAllWardByDistrict(districtId));
    }
    @GetMapping("/ward/{wardId}")
    public ResponseEntity<?> getWardById(@PathVariable(name = "wardId") String wardId) {
        return ResponseEntity.ok(service.getWardById(wardId));
    }
}
