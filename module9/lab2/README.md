+ Создать bean типа Map
 ID = 1, Value = org.shop.common.Sellers.AMAZON
 ID = 2, Value = org.shop.common.Sellers.SAMSUNG
 ID = 3, Value = ‘Apple’
+ SellerInitializer: использовать параметр типа Map<sellerId, sellerName> из предыдущего пункта
+ ProposalInitializer: использовать autowiring by name
+ ProductInitializer: передать параметры в конструктор
+ DataInitializer: инициализировать при помощи init метода