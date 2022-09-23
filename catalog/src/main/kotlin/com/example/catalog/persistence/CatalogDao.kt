package com.example.catalog.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface CatalogDao : JpaRepository<ProductEntity, String>