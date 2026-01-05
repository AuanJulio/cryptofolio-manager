package com.cryptofolio.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "portfolio")
public class Portfolio {

    @Id
    private String id;

    private String userId;

    private List<AssetItem> assets;

    private LocalDateTime createdAt;

    private LocalDateTime lastUpdate;

    public void updateTimestamps(){
        this.lastUpdate = LocalDateTime.now();
        if (this.createdAt == null)
            this.createdAt = LocalDateTime.now();
    }

}
