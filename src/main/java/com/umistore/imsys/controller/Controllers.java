//package com.umistore.imsys.controller;
//
//import com.umistore.imsys.entity.*;
//import com.umistore.imsys.service.SKUAttributeService;
//import com.umistore.imsys.service.*;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/overall")
//@Api(value = "Controllers", description = "General APIs")
//public class Controllers {
//
//    @RestController
//    @RequestMapping("/products")
////    @Api(tags = "products")
//    @Api(value = "ProductController", description = "Operations pertaining to products")
//    public static class ProductController {
//
//        @Autowired
//        private ProductService productService;
//
//        @PostMapping("/")
////        @ApiOperation(value = "create Product", notes = "create Product")
//        @ApiOperation(value = "Create a new product", response = Product.class)
//        public ResponseEntity<Product> createProduct(@RequestBody Product product) {
//            Product savedProduct = productService.createProduct(product);
//            return ResponseEntity.ok(savedProduct);
//        }
//
//        @DeleteMapping("/{id}")
////        @ApiOperation(value = "delete Product", notes = "delete Product")
//        public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
//            productService.deleteProduct(id);
//            return ResponseEntity.ok().build();
//        }
//
//        @PutMapping("/{id}")
////        @ApiOperation(value = "update Product", notes = "update Product name, description, price")
//        public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
//            return ResponseEntity.ok(productService.updateProduct(id, product));
//        }
//
//        // Search for products by name
//        @GetMapping("/search")
////        @ApiOperation(value = "search Product", notes = "Search for products by name")
//        public ResponseEntity<List<Product>> searchProductsByName(@RequestParam String name) {
//            List<Product> products = productService.searchByName(name);
//            return new ResponseEntity<>(products, HttpStatus.OK);
//        }
//
//        @GetMapping("/all")
////        @ApiOperation(value = "get all Product", notes = "get all Product")
//        public ResponseEntity<List<Product>> getAllProducts() {
//            List<Product> productList = productService.getAllProducts();
//            return new ResponseEntity<>(productList, HttpStatus.OK);
//        }
//
//    }
//
//    @RestController
//    @RequestMapping("/paymentTransactions")
//    @Api(value = "PaymentTransactionController", description = "Operations pertaining to payment transactions")
//    public static class PaymentTransactionController {
//
//        @Autowired
//        private PaymentTransactionService paymentTransactionService;
//
//        @PostMapping("/")
//        @ApiOperation(value = "Create a payment transaction", response = PaymentTransaction.class)
//        public ResponseEntity<PaymentTransaction> createPaymentTransaction(@RequestBody PaymentTransaction paymentTransaction) {
//            PaymentTransaction savedTransaction = paymentTransactionService.createPaymentTransaction(paymentTransaction);
//            return ResponseEntity.ok(savedTransaction);
//        }
//
//        @DeleteMapping("/{id}")
//        public ResponseEntity<?> deletePaymentTransaction(@PathVariable Integer id) {
//            paymentTransactionService.deletePaymentTransaction(id);
//            return ResponseEntity.ok().build();
//        }
//
//        @PutMapping("/{id}")
//        public ResponseEntity<PaymentTransaction> updatePaymentTransaction(@PathVariable Integer id, @RequestBody PaymentTransaction paymentTransaction) {
//            return ResponseEntity.ok(paymentTransactionService.updatePaymentTransaction(id, paymentTransaction));
//        }
//
//        // Search for transactions by status
//        @GetMapping("/search")
//        public ResponseEntity<List<PaymentTransaction>> searchTransactionsByStatus(@RequestParam String status) {
//            List<PaymentTransaction> transactions = paymentTransactionService.searchByStatus(status);
//            return new ResponseEntity<>(transactions, HttpStatus.OK);
//        }
//
//        @GetMapping("/all")
//        public ResponseEntity<List<PaymentTransaction>> getAllPaymentTransactions() {
//            List<PaymentTransaction> transactions = paymentTransactionService.getAllPaymentTransactions();
//            return new ResponseEntity<>(transactions, HttpStatus.OK);
//        }
//
//    }
//
//    @RestController
//    @RequestMapping("/orders")
//    @Api(value = "OrderController", description = "Operations pertaining to orders")
//    public static class OrderController {
//
//        @Autowired
//        private OrderService orderService;
//
//        @PostMapping("/")
//        @ApiOperation(value = "Create an order", response = Order.class)
//        public ResponseEntity<Order> createOrder(@RequestBody Order order) {
//            Order savedOrder = orderService.createOrder(order);
//            return ResponseEntity.ok(savedOrder);
//        }
//
//        @DeleteMapping("/{id}")
//        public ResponseEntity<?> deleteOrder(@PathVariable Integer id) {
//            orderService.deleteOrder(id);
//            return ResponseEntity.ok().build();
//        }
//
//        @PutMapping("/{id}")
//        public ResponseEntity<Order> updateOrder(@PathVariable Integer id, @RequestBody Order order) {
//            return ResponseEntity.ok(orderService.updateOrder(id, order));
//        }
//
//        // Search for orders by customer ID
//        @GetMapping("/search")
//        public ResponseEntity<List<Order>> searchOrdersByCustomer(@RequestParam Integer customerId) {
//            List<Order> orders = orderService.searchByCustomer(customerId);
//            return new ResponseEntity<>(orders, HttpStatus.OK);
//        }
//
//        @GetMapping("/all")
//        public ResponseEntity<List<Order>> getAllOrders() {
//            List<Order> orders = orderService.getAllOrders();
//            return new ResponseEntity<>(orders, HttpStatus.OK);
//        }
//
//    }
//
//    @RestController
//    @RequestMapping("/orderDetails")
//    public static class OrderDetailController {
//
//        @Autowired
//        private OrderDetailService orderDetailService;
//
//        @PostMapping("/")
//        public ResponseEntity<OrderDetail> createOrderDetail(@RequestBody OrderDetail orderDetail) {
//            OrderDetail savedOrderDetail = orderDetailService.createOrderDetail(orderDetail);
//            return ResponseEntity.ok(savedOrderDetail);
//        }
//
//        @DeleteMapping("/{id}")
//        public ResponseEntity<?> deleteOrderDetail(@PathVariable Integer id) {
//            orderDetailService.deleteOrderDetail(id);
//            return ResponseEntity.ok().build();
//        }
//
//        @PutMapping("/{id}")
//        public ResponseEntity<OrderDetail> updateOrderDetail(@PathVariable Integer id, @RequestBody OrderDetail orderDetail) {
//            return ResponseEntity.ok(orderDetailService.updateOrderDetail(id, orderDetail));
//        }
//
//        // Search for order details by order ID
//        @GetMapping("/search")
//        public ResponseEntity<List<OrderDetail>> searchOrderDetailsByOrder(@RequestParam Integer orderId) {
//            List<OrderDetail> orderDetails = orderDetailService.searchByOrder(orderId);
//            return new ResponseEntity<>(orderDetails, HttpStatus.OK);
//        }
//
//        @GetMapping("/all")
//        public ResponseEntity<List<OrderDetail>> getAllOrderDetails() {
//            List<OrderDetail> orderDetails = orderDetailService.getAllOrderDetails();
//            return new ResponseEntity<>(orderDetails, HttpStatus.OK);
//        }
//
//    }
//
//    @RestController
//    @RequestMapping("/transactions")
//    public static class TransactionController {
//
//        @Autowired
//        private TransactionService transactionService;
//
//        @PostMapping("/")
//        public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
//            Transaction savedTransaction = transactionService.createTransaction(transaction);
//            return ResponseEntity.ok(savedTransaction);
//        }
//
//        @DeleteMapping("/{id}")
//        public ResponseEntity<?> deleteTransaction(@PathVariable Integer id) {
//            transactionService.deleteTransaction(id);
//            return ResponseEntity.ok().build();
//        }
//
//        @PutMapping("/{id}")
//        public ResponseEntity<Transaction> updateTransaction(@PathVariable Integer id, @RequestBody Transaction transaction) {
//            return ResponseEntity.ok(transactionService.updateTransactionById(id, transaction));
//        }
//
//        // search by TransactionType and Quantity
//        @GetMapping("/search")
//        public ResponseEntity<List<Transaction>> searchTransactions(
//                @RequestParam(required = false) String transactionType,
//                @RequestParam(required = false) Integer quantity) {
//
//            List<Transaction> transactions = transactionService.searchTransactions(transactionType, quantity);
//            return ResponseEntity.ok(transactions);
//        }
//
//    }
//
//    @RestController
//    @RequestMapping("/inventory")
//    public static class InventoryController {
//
//        @Autowired
//        private InventoryService inventoryService;
//
//        @PostMapping("/")
//        public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory) {
//            Inventory savedInventory = inventoryService.createInventory(inventory);
//            return ResponseEntity.ok(savedInventory);
//        }
//
//        @DeleteMapping("/{id}")
//        public ResponseEntity<?> deleteInventory(@PathVariable Integer id) {
//            inventoryService.deleteInventory(id);
//            return ResponseEntity.ok().build();
//        }
//
//        @PutMapping("/{id}")
//        public ResponseEntity<Inventory> updateInventory(@PathVariable Integer id, @RequestBody Inventory inventory) {
//            return ResponseEntity.ok(inventoryService.updateInventory(id, inventory));
//        }
//
//        // Search for inventory by product ID
//        @GetMapping("/search")
//        public ResponseEntity<Inventory> searchInventoryByProduct(@RequestParam Integer productId) {
//            Inventory inventory = inventoryService.searchByProduct(productId);
//            return new ResponseEntity<>(inventory, HttpStatus.OK);
//        }
//
//        @GetMapping("/all")
//        public ResponseEntity<List<Inventory>> getAllInventoryItems() {
//            List<Inventory> inventoryList = inventoryService.getAllInventoryItems();
//            return new ResponseEntity<>(inventoryList, HttpStatus.OK);
//        }
//
//    }
//
//    @RestController
//    @RequestMapping("/reconciliation")
//    public static class ReconciliationController {
//
//        @Autowired
//        private ReconciliationService reconciliationService;
//
//        @PostMapping("/")
//        public ResponseEntity<Reconciliation> createReconciliation(@RequestBody Reconciliation reconciliation) {
//            Reconciliation savedReconciliation = reconciliationService.createReconciliation(reconciliation);
//            return ResponseEntity.ok(savedReconciliation);
//        }
//
//        @DeleteMapping("/{id}")
//        public ResponseEntity<?> deleteReconciliation(@PathVariable Integer id) {
//            reconciliationService.deleteReconciliation(id);
//            return ResponseEntity.ok().build();
//        }
//
//        @PutMapping("/{id}")
//        public ResponseEntity<Reconciliation> updateReconciliation(@PathVariable Integer id, @RequestBody Reconciliation reconciliation) {
//            return ResponseEntity.ok(reconciliationService.updateReconciliation(id, reconciliation));
//        }
//
//        // Search for reconciliations by inventory ID
//        @GetMapping("/search")
//        public ResponseEntity<List<Reconciliation>> searchReconciliationsByInventory(@RequestParam Integer inventoryId) {
//            List<Reconciliation> reconciliations = reconciliationService.searchByInventory(inventoryId);
//            return new ResponseEntity<>(reconciliations, HttpStatus.OK);
//        }
//
//        @GetMapping("/all")
//        public ResponseEntity<List<Reconciliation>> getAllReconciliations() {
//            List<Reconciliation> reconciliations = reconciliationService.getAllReconciliations();
//            return new ResponseEntity<>(reconciliations, HttpStatus.OK);
//        }
//
//    }
//
//    @RestController
//    @RequestMapping("/attributes")
//    public static class AttributeController {
//
//        @Autowired
//        private AttributeService attributeService;
//
//        @PostMapping
//        public ResponseEntity<Attribute> createAttribute(@RequestBody Attribute attribute) {
//            Attribute createdAttribute = attributeService.createAttribute(attribute);
//            return ResponseEntity.ok(createdAttribute);
//        }
//
//        @GetMapping("/{id}")
//        public ResponseEntity<Attribute> getAttributeById(@PathVariable Integer id) {
//            Optional<Attribute> attribute = attributeService.getAttributeById(id);
//            return attribute.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//        }
//
//        @GetMapping("/search")
//        public ResponseEntity<List<Attribute>> searchAttributesByName(@RequestParam String name) {
//            List<Attribute> attributes = attributeService.searchAttributesByName(name);
//            return ResponseEntity.ok(attributes);
//        }
//
//        @PutMapping("/{id}")
//        public ResponseEntity<Attribute> updateAttribute(@PathVariable Integer id, @RequestBody Attribute attributeDetails) {
//            Attribute updatedAttribute = attributeService.updateAttribute(id, attributeDetails);
//            return ResponseEntity.ok(updatedAttribute);
//        }
//
//        @DeleteMapping("/{id}")
//        public ResponseEntity<?> deleteAttribute(@PathVariable Integer id) {
//            attributeService.deleteAttribute(id);
//            return ResponseEntity.ok().build();
//        }
//    }
//
//    @RestController
//    @RequestMapping("/attributeValues")
//    public class AttributeValueController {
//
//        @Autowired
//        private AttributeValueService attributeValueService;
//
//        @PostMapping
//        public ResponseEntity<AttributeValue> createAttributeValue(@RequestBody AttributeValue attributeValue) {
//            AttributeValue createdAttributeValue = attributeValueService.createAttributeValue(attributeValue);
//            return ResponseEntity.ok(createdAttributeValue);
//        }
//
//        @GetMapping("/{id}")
//        public ResponseEntity<AttributeValue> getAttributeValueById(@PathVariable Integer id) {
//            Optional<AttributeValue> attributeValue = attributeValueService.getAttributeValueById(id);
//            return attributeValue.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//        }
//
//        @DeleteMapping("/{id}")
//        public ResponseEntity<?> deleteAttributeValue(@PathVariable Integer id) {
//            attributeValueService.deleteAttributeValue(id);
//            return ResponseEntity.ok().build();
//        }
//
//        @GetMapping("/search")
//        public ResponseEntity<List<AttributeValue>> searchByAttributeID(@RequestParam Integer attributeID) {
//            List<AttributeValue> attributeValues = attributeValueService.searchByAttributeID(attributeID);
//            return ResponseEntity.ok(attributeValues);
//        }
//
//        @PutMapping("/{id}")
//        public ResponseEntity<AttributeValue> updateAttributeValue(@PathVariable Integer id, @RequestBody AttributeValue attributeValue) {
//            return ResponseEntity.ok(attributeValueService.updateAttributeValue(id, attributeValue));
//        }
//
//    }
//
//    @RestController
//    @RequestMapping("/attributeGroups")
//    public class AttributeGroupController {
//
//        @Autowired
//        private AttributeGroupService attributeGroupService;
//
//        @PostMapping
//        public ResponseEntity<AttributeGroup> createAttributeGroup(@RequestBody AttributeGroup attributeGroup) {
//            AttributeGroup createdAttributeGroup = attributeGroupService.createAttributeGroup(attributeGroup);
//            return ResponseEntity.ok(createdAttributeGroup);
//        }
//
//        @GetMapping("/{id}")
//        public ResponseEntity<AttributeGroup> getAttributeGroupById(@PathVariable Integer id) {
//            AttributeGroup attributeGroup = attributeGroupService.getAttributeGroupById(id);
//            return ResponseEntity.ok(attributeGroup);
//        }
//
//        @PutMapping("/{id}")
//        public ResponseEntity<AttributeGroup> updateAttributeGroup(@PathVariable Integer id, @RequestBody AttributeGroup attributeGroupDetails) {
//            AttributeGroup updatedAttributeGroup = attributeGroupService.updateAttributeGroup(id, attributeGroupDetails);
//            return ResponseEntity.ok(updatedAttributeGroup);
//        }
//
//        @DeleteMapping("/{id}")
//        public ResponseEntity<Void> deleteAttributeGroup(@PathVariable Integer id) {
//            attributeGroupService.deleteAttributeGroup(id);
//            return ResponseEntity.noContent().build();
//        }
//    }
//
//    @RestController
//    @RequestMapping("/attributeGroupMappings")
//    public class AttributeGroupMappingController {
//
//        @Autowired
//        private AttributeGroupMappingService attributeGroupMappingService;
//
//        @PostMapping
//        public ResponseEntity<AttributeGroupMapping> createAttributeGroupMapping(@RequestBody AttributeGroupMapping attributeGroupMapping) {
//            AttributeGroupMapping createdAttributeGroupMapping = attributeGroupMappingService.createAttributeGroupMapping(attributeGroupMapping);
//            return ResponseEntity.ok(createdAttributeGroupMapping);
//        }
//
//        @GetMapping
//        public ResponseEntity<List<AttributeGroupMapping>> getAllAttributeGroupMappings() {
//            List<AttributeGroupMapping> attributeGroupMappings = attributeGroupMappingService.getAllAttributeGroupMappings();
//            return ResponseEntity.ok(attributeGroupMappings);
//        }
//
//        @GetMapping("/byAttributeGroup/{attributeGroupId}")
//        public ResponseEntity<List<AttributeGroupMapping>> findByAttributeGroup(@PathVariable int attributeGroupId) {
//            List<AttributeGroupMapping> attributeGroupMappings = attributeGroupMappingService.findByAttributeGroup(attributeGroupId);
//            return ResponseEntity.ok(attributeGroupMappings);
//        }
//
//        @GetMapping("/byAttribute/{attributeId}")
//        public ResponseEntity<List<AttributeGroupMapping>> findByAttribute(@PathVariable int attributeId) {
//            List<AttributeGroupMapping> attributeGroupMappings = attributeGroupMappingService.findByAttribute(attributeId);
//            return ResponseEntity.ok(attributeGroupMappings);
//        }
//
//        @DeleteMapping("/{attributeGroupId}/{attributeId}")
//        public ResponseEntity<Void> deleteAttributeGroupMapping(
//                @PathVariable int attributeGroupId,
//                @PathVariable int attributeId) {
//            attributeGroupMappingService.deleteAttributeGroupMapping(new AttributeGroupMappingId(attributeGroupId, attributeId));
//            return ResponseEntity.noContent().build();
//        }
//    }
//
//    @RestController
//    @RequestMapping("/productAttributes")
//    public class ProductAttributeController {
//
//        @Autowired
//        private ProductAttributeService productAttributeService;
//
//        @PostMapping
//        public ResponseEntity<ProductAttribute> createProductAttribute(@RequestBody ProductAttribute productAttribute) {
//            ProductAttribute newProductAttribute = productAttributeService.createOrUpdateProductAttribute(productAttribute);
//            return ResponseEntity.ok(newProductAttribute);
//        }
//
//        @GetMapping("/{productId}/{attributeValueId}")
//        public ResponseEntity<ProductAttribute> getProductAttributeById(@PathVariable Integer productId, @PathVariable Integer attributeValueId) {
//            return productAttributeService.findById(new ProductAttributeId(productId, attributeValueId))
//                    .map(ResponseEntity::ok)
//                    .orElse(ResponseEntity.notFound().build());
//        }
//
//        @GetMapping
//        public ResponseEntity<List<ProductAttribute>> getAllProductAttributes() {
//            List<ProductAttribute> list = productAttributeService.findAll();
//            return ResponseEntity.ok(list);
//        }
//
//        @DeleteMapping("/{productId}/{attributeValueId}")
//        public ResponseEntity<Void> deleteProductAttribute(@PathVariable Integer productId, @PathVariable Integer attributeValueId) {
//            productAttributeService.deleteProductAttribute(new ProductAttributeId(productId, attributeValueId));
//            return ResponseEntity.ok().build();
//        }
//
//        // Endpoints for update and search can be added here
//    }
//
//    @RestController
//    @RequestMapping("/skus")
//    public class SKUController {
//
//        @Autowired
//        private SKUService skuService;
//
//        @PostMapping
//        public ResponseEntity<SKU> createSKU(@RequestBody SKU sku) {
//            SKU newSKU = skuService.createOrUpdateSKU(sku);
//            return ResponseEntity.ok(newSKU);
//        }
//
//        @GetMapping("/{id}")
//        public ResponseEntity<SKU> getSKUById(@PathVariable Integer id) {
//            return skuService.findById(id)
//                    .map(ResponseEntity::ok)
//                    .orElse(ResponseEntity.notFound().build());
//        }
//
//        @GetMapping
//        public ResponseEntity<List<SKU>> getAllSKUs() {
//            List<SKU> list = skuService.findAll();
//            return ResponseEntity.ok(list);
//        }
//
//        @DeleteMapping("/{id}")
//        public ResponseEntity<Void> deleteSKU(@PathVariable Integer id) {
//            skuService.deleteSKU(id);
//            return ResponseEntity.ok().build();
//        }
//
//        // Endpoints for update and search
//        @GetMapping("/search")
//        public ResponseEntity<SKU> getSKUBySku(@RequestParam String sku) {
//            SKU foundSKU = skuService.findBySku(sku);
//            if (foundSKU != null) {
//                return ResponseEntity.ok(foundSKU);
//            } else {
//                return ResponseEntity.notFound().build();
//            }
//        }
//
//        @PutMapping("/{id}")
//        public ResponseEntity<SKU> updateSKU(@PathVariable Integer id, @RequestBody SKU skuDetails) {
//            SKU updatedSKU = skuService.updateSKU(id, skuDetails);
//            return ResponseEntity.ok(updatedSKU);
//        }
//
//    }
//
//    @RestController
//    @RequestMapping("/skuattributes")
//    public class SKUAttributeController {
//
//        @Autowired
//        private SKUAttributeService skuAttributeService;
//
//        @PostMapping
//        public ResponseEntity<SKUAttribute> createSKUAttribute(@RequestBody SKUAttribute skuAttribute) {
//            SKUAttribute newSKUAttribute = skuAttributeService.createOrUpdateSKUAttribute(skuAttribute);
//            return ResponseEntity.ok(newSKUAttribute);
//        }
//
//        @GetMapping
//        public ResponseEntity<List<SKUAttribute>> getAllSKUAttributes() {
//            List<SKUAttribute> list = skuAttributeService.findAll();
//            return ResponseEntity.ok(list);
//        }
//
//        @DeleteMapping
//        public ResponseEntity<Void> deleteSKUAttribute(@RequestBody SKUAttributeId id) {
//            skuAttributeService.deleteSKUAttribute(id);
//            return ResponseEntity.ok().build();
//        }
//
//        // Endpoints for update and search
//        @GetMapping("/search")
//        public ResponseEntity<List<SKUAttribute>> findSKUAttributesBySKUId(@RequestParam Integer skuId) {
//            List<SKUAttribute> skuAttributes = skuAttributeService.findBySkuId(skuId);
//            return ResponseEntity.ok(skuAttributes);
//        }
//
//        @PutMapping("/{skuId}/{attributeValueId}")
//        public ResponseEntity<SKUAttribute> updateSKUAttribute(@PathVariable Integer skuId,
//                                                               @PathVariable Integer attributeValueId,
//                                                               @RequestBody SKUAttribute skuAttribute) {
//            Optional<SKUAttribute> updatedSKUAttribute = skuAttributeService.updateSKUAttribute(skuId, attributeValueId, skuAttribute);
//            return updatedSKUAttribute
//                    .map(ResponseEntity::ok)
//                    .orElseGet(() -> ResponseEntity.notFound().build());
//        }
//
//    }
//
//}
