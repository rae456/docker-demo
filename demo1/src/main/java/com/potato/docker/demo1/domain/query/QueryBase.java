package com.potato.docker.demo1.domain.query;

import lombok.Data;

@Data
public class QueryBase {
    private Long pageSize;
    private Long pageIndex;
}
