package com.sdd.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


/**
 * Response:
 * { Status: 1,
 * Patient_id: 123,
 * Message: “success”
 * }
 */

@Data
public class PatientCreateResponse {
  private int status;

  @JsonProperty("patient_id")
  private Integer patientId;


  private String message;
}
