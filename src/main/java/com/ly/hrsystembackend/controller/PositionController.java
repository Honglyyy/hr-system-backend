package com.ly.hrsystembackend.controller;

import com.ly.hrsystembackend.dto.positiondto.PositionRequest;
import com.ly.hrsystembackend.dto.positiondto.PositionResponse;
import com.ly.hrsystembackend.service.PositionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/positions")
public class PositionController {
    private final PositionService positionService;

    @PostMapping
    public ResponseEntity<PositionResponse> createPosition(@Valid @RequestBody PositionRequest positionRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(positionService.createPosition(positionRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PositionResponse> updatePosition(@PathVariable UUID id, @Valid @RequestBody PositionRequest positionRequest){
        return ResponseEntity.status(HttpStatus.OK).body(positionService.updatePosition(id, positionRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePosition(@PathVariable UUID id){
        positionService.deletePosition(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<List<PositionResponse>> getPositions(){
        return ResponseEntity.ok(positionService.getPositions());
    }
}
