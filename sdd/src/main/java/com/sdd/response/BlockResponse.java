package com.sdd.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class BlockResponse {
    private Integer healthBlockId;

    private Integer healthBlockCode;

    private String healthBlockName;

    private Integer districtCode;

    private Integer talukaId;

    private Integer mddsCode;

   private Integer stateId;
}
