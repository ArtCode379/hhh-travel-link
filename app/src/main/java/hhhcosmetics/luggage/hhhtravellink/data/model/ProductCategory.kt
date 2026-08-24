package hhhcosmetics.luggage.hhhtravellink.data.model

import androidx.annotation.StringRes
import hhhcosmetics.luggage.hhhtravellink.R

enum class ProductCategory(@field:StringRes val titleRes: Int) {
    SUITCASES(R.string.qjioo_category_suitcases),
    BACKPACKS(R.string.qjioo_category_backpacks),
    TRAVEL_BAGS(R.string.qjioo_category_travel_bags),
    ORGANIZERS(R.string.qjioo_category_organizers),
    COMFORT(R.string.qjioo_category_comfort),
}
