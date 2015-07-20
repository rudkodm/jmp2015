package org.shop;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The main Data Initializer util class.
 */
@Component
public class DataInitializer {
    
    private static final Logger LOG = LogManager.getLogger(DataInitializer.class);
    
    /** The seller initializer. */
    @Autowired
    private SellerInitializer sellerInitializer;
    
    /** The product initializer. */
    @Autowired
    private ProductInitializer productInitializer;
    
    /** The proposal initializer. */
    @Autowired
    private ProposalInitializer proposalInitializer;
    
    /** The user initializer. */
    @Autowired
    private UserInitializer userInitializer;

    
    public void setSellerInitializer(SellerInitializer sellerInitializer) {
        this.sellerInitializer = sellerInitializer;
    }


    public void setProductInitializer(ProductInitializer productInitializer) {
        this.productInitializer = productInitializer;
    }


    public void setProposalInitializer(ProposalInitializer proposalInitializer) {
        this.proposalInitializer = proposalInitializer;
    }


    public void setUserInitializer(UserInitializer userInitializer) {
        this.userInitializer = userInitializer;
    }

    /**
     * Inits the data.
     */
    
    // + DataInitializer: инициализировать при помощи init метода
    @PostConstruct
    public void initData() {
        
        LOG.info("--> Init data");
        
        sellerInitializer.initSellers();
        userInitializer.initUsers();
        productInitializer.initProducts();
        proposalInitializer.initProposals();
    }
}
