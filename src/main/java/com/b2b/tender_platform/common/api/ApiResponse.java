package com.b2b.tender_platform.common.api;
import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;

    public static <T> ApiResponse<T> ok(T data) { return ApiResponse.<T>builder().success(true).data(data).build(); }


}
