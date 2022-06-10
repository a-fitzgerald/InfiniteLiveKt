package com.avonfitzgerald.infinitelive.endpoint.user.model

import kotlinx.serialization.Serializable

/**
 * [Official Documentation](https://infiniteflight.com/guide/developer-reference/live-api/user-flights#paginated-list)
 *
 * @property pageIndex The index of the current page.
 * @property totalPages The total number of pages available.
 * @property totalCount The total amount of entries for this dataset.
 * @property hasPreviousPage Whether there is a page before this one.
 * @property hasNextPage Whether there is a page after this one.
 * @property data Nullable generic.
 */
@Serializable
data class PaginatedList<T>(
    val pageIndex: Int,
    val totalPages: Int,
    val totalCount: Int,
    val hasPreviousPage: Boolean,
    val hasNextPage: Boolean,
    val data: T?
)