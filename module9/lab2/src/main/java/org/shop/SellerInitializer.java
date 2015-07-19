package org.shop;

import org.shop.api.SellerService;
import org.shop.data.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * The Seller Initializer util class.
 */
@Component
public class SellerInitializer {

    /** The seller service. */
    private SellerService sellerService;
    
    /** The seller names. */
    private Map<Long, String> sellerNames = Collections.emptyMap();

    @Autowired
    public SellerInitializer(SellerService sellerService, @Value("#{sellerNames}") Map<Long, String> sellerNames) {
        super();
        this.sellerService = sellerService;
        this.sellerNames = sellerNames;
    }

    /**
     * Inits the sellers.
     */
    public void initSellers() {
        List<Seller> sellers = new LinkedList<Seller>();
        
        for (Map.Entry<Long, String> entry : sellerNames.entrySet()) {
            Seller seller = new Seller();
            seller.setId(entry.getKey());
            seller.setName(entry.getValue());
            
            sellers.add(seller);
        }
        
        sellerService.importSellers(sellers);
    }
}
