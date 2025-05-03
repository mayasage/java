package com.laura.lauraspringboot.repositories;

import com.laura.lauraspringboot.models.PurchaseDetails;
import lombok.Data;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Data
public class PurchaseRepository {
        private final JdbcTemplate jdbcTemplate;

        public void storePurchase(PurchaseDetails purchaseDetails) {
                String sql = "INSERT INTO purchases (product_name, product_price) VALUES (?, ?)";
                jdbcTemplate.update(sql, purchaseDetails.getProductName(), purchaseDetails.getProductPrice());
        }

        public List<PurchaseDetails> findAllPurchases() {
                String sql = "SELECT * FROM purchases";
                return jdbcTemplate.query(sql, (row, index) -> {
                        PurchaseDetails purchaseDetails = new PurchaseDetails();
                        purchaseDetails.setPurchaseId(row.getInt("purchase_id"));
                        purchaseDetails.setProductName(row.getString("product_name"));
                        purchaseDetails.setProductPrice(row.getBigDecimal("product_price"));
                        return purchaseDetails;
                });
        }
}
