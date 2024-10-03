package com.example.Models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QrCode {
    private String imgQr;
    private String titQr;
    private String descQr;
}
