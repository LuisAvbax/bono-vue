package com.example.bono_challenge.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "custom_field_values")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomFieldValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String entityType;  // Para identificar a qué tipo de entidad pertenece, e.g. "Customer"
    private Long entityId;      // ID de la entidad destino
    private String fieldName;   // Nombre del campo personalizado (unique con entityType)

    // Se almacena como String. Convertir a numérico u otro tipo según necesidad.
    private String fieldValue;
}
