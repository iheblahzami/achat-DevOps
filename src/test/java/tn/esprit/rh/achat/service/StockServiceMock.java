package tn.esprit.rh.achat.service;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.StockServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class StockServiceMock {
    @InjectMocks
    private StockServiceImpl stockService;

    @Mock
    private StockRepository stockRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllStocks() {
        // Create a list of mock Stock objects
        List<Stock> mockStocks = new ArrayList<>();
//        mockStocks.add(new Stock(/* initialize with appropriate data */));
//        mockStocks.add(new Stock(/* initialize with appropriate data */));
        mockStocks.add(new Stock("Stock 1", 100, 10)); // Replace with actual data
        mockStocks.add(new Stock("Stock 2", 200, 20)); // Replace with actual data
        Mockito.when(stockRepository.findAll()).thenReturn(mockStocks);

        List<Stock> result = stockService.retrieveAllStocks();


    }

    @Test
    public void testAddStock() {
        // Create a mock Stock object
       // Stock mockStock = new Stock(/* initialize with appropriate data */);
        Stock mockStock = new Stock("Sample Stock", 100, 10); // Replace with actual data
        // Mock the behavior of stockRepository.save() to return the mockStock
        Mockito.when(stockRepository.save(Mockito.any(Stock.class))).thenReturn(mockStock);

        Stock result = stockService.addStock(mockStock);


    }
    @Test
    public void testUpdateStock() {
        // Create a mock Stock object with an ID
        Stock mockStock = new Stock("Sample Stock", 100, 10);
        Long stockId = 1L;
        mockStock.setIdStock(stockId);

        // Mock the behavior of stockRepository.save() to return the mockStock
        Mockito.when(stockRepository.save(Mockito.any(Stock.class))).thenReturn(mockStock);

        // Call the method to be tested
        Stock result = stockService.updateStock(mockStock);

        // Assert that the ID of the returned Stock matches the provided stockId
        Assert.assertEquals(stockId, result.getIdStock());
    }


    @Test
    public void testRetrieveStatusStock() {
        // Create a list of mock Stock objects for the status check
        List<Stock> mockStocksEnRouge = new ArrayList<>();
//        mockStocksEnRouge.add(new Stock(/* initialize with appropriate data */));
//        mockStocksEnRouge.add(new Stock(/* initialize with appropriate data */));
// Initialize the first mock Stock with appropriate data
        Stock stock1 = new Stock("Stock 1", 100, 10); // Replace with actual data
        mockStocksEnRouge.add(stock1);

// Initialize the second mock Stock with appropriate data
        Stock stock2 = new Stock("Stock 2", 200, 20); // Replace with actual data
        mockStocksEnRouge.add(stock2);
        // Mock the behavior of stockRepository.retrieveStatusStock() to return the mockStocksEnRouge list
        Mockito.when(stockRepository.retrieveStatusStock()).thenReturn(mockStocksEnRouge);

        // Call the method to be tested
        String result = stockService.retrieveStatusStock();

        // Assert or verify the expected behavior
        // You can use assertions or Mockito.verify() as needed
    }

}

