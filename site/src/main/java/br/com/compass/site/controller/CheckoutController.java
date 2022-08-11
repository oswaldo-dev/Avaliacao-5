package br.com.compass.site.controller;

import br.com.compass.site.dto.request.RequestCheckoutDto;
import br.com.compass.site.dto.response.ResponseCheckoutDto;
import br.com.compass.site.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {


    @Autowired
    private CheckoutService service;

    @PostMapping
    public ResponseEntity<ResponseCheckoutDto> post(@RequestBody @Valid RequestCheckoutDto checkout, UriComponentsBuilder componentsBuilder) {
        ResponseCheckoutDto checkoutDto = service.post(checkout);
        return ResponseEntity.ok(checkoutDto);
    }
}
