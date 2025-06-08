package com.mitahudev.blog.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MetaData {
    
    @JsonProperty("total_count")
    private Long totalCount;
    
    @JsonProperty("page")
    private Integer page;
    
    @JsonProperty("per_page")
    private Integer perPage;
    
    @JsonProperty("total_pages")
    private Integer totalPages;
    
    @JsonProperty("has_next")
    private Boolean hasNext;
    
    @JsonProperty("has_previous")
    private Boolean hasPrevious;
    
    @JsonProperty("request_id")
    private String requestId;
    
    @JsonProperty("api_version")
    private String apiVersion;
    
    // Static factory method untuk pagination
    public static MetaData pagination(long totalCount, int page, int perPage) {
        int totalPages = (int) Math.ceil((double) totalCount / perPage);
        
        return MetaData.builder()
                .totalCount(totalCount)
                .page(page)
                .perPage(perPage)
                .totalPages(totalPages)
                .hasNext(page < totalPages)
                .hasPrevious(page > 1)
                .apiVersion("v1")
                .build();
    }
    
    // Static factory method untuk simple meta
    public static MetaData simple(long totalCount) {
        return MetaData.builder()
                .totalCount(totalCount)
                .apiVersion("v1")
                .build();
    }
}