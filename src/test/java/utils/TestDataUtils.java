package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.Product;

import java.io.InputStream;
import java.util.List;

public class TestDataUtils {
    public static List<Product> getProductsFromJson(String fileName) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = TestDataUtils.class.getClassLoader().getResourceAsStream(fileName);
            return mapper.readValue(is, new TypeReference<List<Product>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Failed to load test data from JSON", e);
        }
    }
}
