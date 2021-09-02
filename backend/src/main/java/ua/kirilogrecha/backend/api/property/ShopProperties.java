package ua.kirilogrecha.backend.api.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties("shop")
public class ShopProperties {
    private String storage;
}
