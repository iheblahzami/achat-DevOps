//package tn.esprit.rh.achat.service;
//
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//    import tn.esprit.rh.achat.entities.Produit;
//    import tn.esprit.rh.achat.services.IProduitService;
//
//    import java.util.List;
//
//@SpringBootTest
//    @TestMethodOrder(OrderAnnotation.class)
//     class ProduitServiceImpTest {
//        @Autowired
//        IProduitService ps;
//        @Test
//        @Order(1)
//        public void testRetrieveAllProducts() {
//            List<Produit> listofProducts = ps.retrieveAllProduits();
//            Assertions.assertEquals(0, listofProducts.size());
//        }
//
//    }
//
