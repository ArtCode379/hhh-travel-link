package hhhcosmetics.luggage.hhhtravellink.data.repository

import hhhcosmetics.luggage.hhhtravellink.data.model.Product
import hhhcosmetics.luggage.hhhtravellink.data.model.ProductCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ProductRepository {
    private val products = listOf(
        Product(
            1,
            "Aster Cabin Case",
            "A refined hard-shell cabin case with silent spinner wheels, a TSA lock, and a thoughtfully divided interior for effortless short trips.",
            ProductCategory.SUITCASES,
            129.00,
            "https://images.unsplash.com/photo-1565026057447-bc90a3dceb87?w=1200",
        ),
        Product(
            2, "Nocturne Check-In", "A generous expandable polycarbonate check-in suitcase with smooth spinner wheels.",
            ProductCategory.SUITCASES, 189.00, "https://images.unsplash.com/photo-1581553680321-4fffae59fccd?w=1200",
        ),
        Product(
            3, "Atlas City Backpack", "A structured backpack with a laptop sleeve, quick-access pocket, and breathable back panel.",
            ProductCategory.BACKPACKS, 84.00, "https://images.unsplash.com/photo-1553062407-98eeb64c6a62?w=1200",
        ),
        Product(
            4, "Voyager Rolltop", "A weather-ready rolltop that flexes from daily commuting to weekends away.",
            ProductCategory.BACKPACKS, 96.00, "https://images.unsplash.com/photo-1622560480605-d83c853bc5c3?w=1200",
        ),
        Product(
            5, "Weekender Holdall", "A spacious carry-on holdall with reinforced handles and a separate shoe compartment.",
            ProductCategory.TRAVEL_BAGS, 112.00, "https://images.unsplash.com/photo-1553732435-1f0475d6ba7b?w=1200",
        ),
        Product(
            6, "Foldaway Travel Tote", "A lightweight tote that folds into its own pocket and slides over a suitcase handle.",
            ProductCategory.TRAVEL_BAGS, 42.00, "https://images.unsplash.com/photo-1554342872-034a06541bad?w=1200",
        ),
        Product(
            7, "Packing Cube Set", "Four breathable packing cubes to keep outfits and essentials beautifully ordered.",
            ProductCategory.ORGANIZERS, 36.00, "https://images.unsplash.com/photo-1585916420730-d7f95e942d43?w=1200",
        ),
        Product(
            8, "Passport Folio", "A slim folio with space for passports, cards, boarding passes, and a pen.",
            ProductCategory.ORGANIZERS, 29.00, "https://images.unsplash.com/photo-1544644181-1484b3fdfc62?w=1200",
        ),
        Product(
            9, "Cloud Memory Pillow", "A supportive memory-foam neck pillow with a soft washable cover and travel pouch.",
            ProductCategory.COMFORT, 38.00, "https://images.unsplash.com/photo-1584100936595-c0654b55a2e2?w=1200",
        ),
        Product(
            10, "Silk Journey Set", "A soft eye mask and travel pouch designed for more restful journeys.",
            ProductCategory.COMFORT, 34.00, "https://images.unsplash.com/photo-1515377905703-c4788e51af15?w=1200",
        ),
        Product(
            11, "Heritage Garment Bag", "A streamlined carrier that keeps occasion wear protected in transit.",
            ProductCategory.TRAVEL_BAGS, 118.00, "https://images.unsplash.com/photo-1551028719-00167b16eac5?w=1200",
        ),
        Product(
            12, "Essential Cable Case", "A compact organizer with loops for chargers, adapters, earbuds, and travel tech.",
            ProductCategory.ORGANIZERS, 31.00, "https://images.unsplash.com/photo-1583394838336-acd977736f90?w=1200",
        ),
    )

    fun observeById(id: Int): Flow<Product?> = flowOf(products.find { it.id == id })

    fun getById(id: Int): Product? = products.find { it.id == id }

    fun observeAll(): Flow<List<Product>> = flowOf(products)
}
