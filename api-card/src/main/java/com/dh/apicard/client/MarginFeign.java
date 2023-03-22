package com.dh.apicard.client;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.math.BigDecimal;
import java.util.List;

@FeignClient(name = "api-margins")
public interface MarginFeign {

    @GetMapping("/margins/calculate/{documentType}/{documentValue}")
    CalificationDTO calculateCalification(@PathVariable String documentType, @PathVariable String documentValue);

    @Getter
    @Setter
    class CalificationDTO {
        private String documentType;
        private String documentValue;
        private BigDecimal totalMargin;
        private BigDecimal totalMarginAdditional;
        private List<Sublimit> sublimits;

        @Getter
        @Setter
        public static class Sublimit {
            private Concept concept;
            private BigDecimal totalMargin;
        }

        public enum Concept {
            CARD, LOAN, CHECK
        }
    }
}