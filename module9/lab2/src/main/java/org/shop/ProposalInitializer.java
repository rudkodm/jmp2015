package org.shop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.shop.api.ProductService;
import org.shop.api.ProposalService;
import org.shop.api.SellerService;
import org.shop.common.Products;
import org.shop.data.Product;
import org.shop.data.Seller;

/**
 * The Proposal Initializer util class.
 */
public class ProposalInitializer {
    
    private final Logger LOG = LogManager.getLogger(ProposalInitializer.class);
    
    /** The product service. */
    private ProductService productService;
    
    /** The proposal service. */
    private ProposalService proposalService;
    
    /** The seller service. */
    private SellerService sellerService;
    
    public void setProductService(ProductService productService){
        this.productService = productService;
    }
    public void setProposalService(ProposalService proposalService){
        this.proposalService = proposalService;
    }
    public void setSellerService(SellerService sellerService){
        this.sellerService = sellerService;
    }

    /**
     * Inits the proposals.
     */
    public void initProposals() {
        
        LOG.info("--> Init Proposals");
        
        Seller amazon = sellerService.getSellerById((long) 1);
        Seller samsung = sellerService.getSellerById((long) 2);
        
        Product galaxyTab = productService.getProductsByName(Products.SAMSUNG_GALAXY_TAB).get(0);
        Product kindleFire = productService.getProductsByName(Products.KINDLE_FIRE).get(0);
        Product kindleTouch = productService.getProductsByName(Products.KINDLE_TOUCH).get(0);
        Product galaxyAce = productService.getProductsByName(Products.SAMSUNG_GALAXY_ACE).get(0);
        
        //Samsung
        proposalService.createProposal(samsung.getId(), galaxyAce.getId(), 250.0);
        proposalService.createProposal(samsung.getId(), galaxyTab.getId(), 500.0);
        
        //Amazon
        proposalService.createProposal(amazon.getId(), kindleFire.getId(), 199.0);
        proposalService.createProposal(amazon.getId(), kindleTouch.getId(), 99.0);
    }
}
