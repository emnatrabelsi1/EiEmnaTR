package fr.troisIl.evaluation;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNotNull;
import fr.troisIl.evaluation.ProductService;
import fr.troisIl.evaluation.Product;
import fr.troisIl.evaluation.Exceptions.ExceptionInsertNull;
import fr.troisIl.evaluation.Exceptions.ExceptionInsert;
import fr.troisIl.evaluation.Exceptions.ExceptionUpdate;
import fr.troisIl.evaluation.Exceptions.ExceptionDelete;
import fr.troisIl.evaluation.Exceptions.ExceptionUpdateNull;
import fr.troisIl.evaluation.Exceptions.ExceptionFindById;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
public class ProductServiceTest {

    private Database db = null;
    private ProductService productService;
    private ProductDao productDao;

    private int countBefore = 0;

    @Before
    public void setUp() throws SQLException {
        String testDatabaseFileName = "product.db";

        // reset la BDD avant le test
        File file = new File(testDatabaseFileName);
        file.delete();

        db = new Database(testDatabaseFileName);
        db.createBasicSqlTable();

        productService = new ProductService(db);

        countBefore = count();
    }

    /**
     * Compte les produits en BDD
     *
     * @return le nombre de produit en BDD
     */
    private int count() throws SQLException {
        ResultSet resultSet = db.executeSelect("Select count(*) from Product");
        assertNotNull(resultSet);
        return resultSet.getInt(1);
    }

    @Test
    public void testInsert() throws SQLException {
      try {
        Product product = new Product(1, "label", 3);

            Product createdEmployee = productService.insert(product);
            assertNotNull(createdEmployee);
           Product pr = productDao.findById(1);
           
            assertNotNull(pr);
            assertEquals(product.getLabel(), pr.getLabel());
            assertEquals(product.getQuantity(), product.getQuantity());
  } catch (RuntimeException e) {
        e.printStackTrace();
        throw new ExceptionInsert("Erreur lors de l'insertion ou de la recherche du produit: " + e.getMessage());
    }
    }
    
@Test
    public void testInsertNull() throws SQLException {  
     try {
        productService.insert(null);
        fail("Une exception ExceptionInsertNull aurait dû être déclenchée avec des valeurs nulles.");
    } catch (ExceptionInsertNull e) {
        assertEquals("Product ne peut pas être null.", e.getMessage());
    }
    }
    @Test
    public void testUpdate() throws SQLException { try {
        Product initialProduct = new Product(1, "InitialLabel", 5);
        productService.insert(initialProduct);


            Product updatedProduct = new Product(1, "UpdatedLabel", 8);
        productService.update(updatedProduct);
           
           
             Product retrievedProduct = productDao.findById(1);
        assertNotNull(retrievedProduct);
        assertEquals(updatedProduct.getLabel(), retrievedProduct.getLabel());
        assertEquals(updatedProduct.getQuantity(), retrievedProduct.getQuantity());
  } catch (Exception e) {
        e.printStackTrace();
        throw new ExceptionUpdate("Erreur lors de la modification ou de la recherche du produit: " + e.getMessage());
    }
    }
 @Test
    public void testUpdateNull() throws SQLException {try {
        productService.update(null);
        fail("Une exception ExceptionInsertNull aurait dû être déclenchée avec des valeurs nulles.");
    } catch (ExceptionUpdateNull e) {
        assertEquals("Product ne peut pas être null.", e.getMessage());
    }
    }
    @Test
    public void testFindById() throws SQLException {
        {
    try {
        Product initialProduct = new Product(1, "label", 5);
        Product existingProduct = productDao.findById(1);

        assertNotNull(existingProduct);
          
        assertEquals(Integer.valueOf(1), existingProduct.getId());
        assertEquals("label", existingProduct.getLabel());
        assertEquals(Integer.valueOf(5), existingProduct.getQuantity());

    } catch (Exception e) {
        e.printStackTrace();
        throw new ExceptionFindById("Erreur lors de la recherche du produit par ID : " + e.getMessage());
    }
    }}

    public void testDelete() throws SQLException {
        Product existingProduct = productDao.findById(1);
        assertNotNull(existingProduct);
        productDao.delete(1);
        Product deletedProduct = productDao.findById(1);
        assertNull(deletedProduct);
}
}
